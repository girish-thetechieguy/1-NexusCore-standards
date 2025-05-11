# Stage 1: Build
FROM node:18-alpine AS builder

# Install dependencies (including build tools for native modules)
RUN apk add --no-cache python3 make g++

# Set working directory
WORKDIR /app

# Copy dependency files first (optimizes layer caching)
COPY package*.json ./
COPY yarn.lock ./

# Install production dependencies (no devDependencies)
RUN yarn install --frozen-lockfile --production=true

# Copy all files
COPY . .

# Build the app (if needed)
RUN yarn build

# Stage 2: Runtime
FROM node:18-alpine

# Install security updates and required runtime deps
RUN apk --no-cache upgrade && \
    apk add --no-cache tini curl

# Set environment variables
ENV NODE_ENV=dev
ENV PORT=3000

# Use tini as init process (better signal handling)
ENTRYPOINT ["/sbin/tini", "--"]

# Create non-root user
RUN addgroup -g 1001 -S nodejs && \
    adduser -u 1001 -S nodejs -G nodejs

# Set working directory
WORKDIR /app

# Copy from builder
COPY --from=builder --chown=nodejs:nodejs /app/node_modules ./node_modules
COPY --from=builder --chown=nodejs:nodejs /app/package.json ./
COPY --from=builder --chown=nodejs:nodejs /app/dist ./dist  # or your build output dir

# Switch to non-root user
USER nodejs

# Health check
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:$PORT/health || exit 1

# Expose port
EXPOSE $PORT

# Run the app
CMD ["node", "dist/index.js"]  # Adjust to your entry point