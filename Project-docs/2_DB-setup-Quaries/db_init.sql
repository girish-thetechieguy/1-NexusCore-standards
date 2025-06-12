1. Initial Setup (Run as nexus-admin superuser)
sql
-- Connect to default postgres database as nexus-admin
\c postgres

-- Create the application database owned by nexus-admin
CREATE DATABASE "nexus-app"
    WITH 
    OWNER = "nexus-admin"
    ENCODING = 'UTF8'
    CONNECTION LIMIT = 100;
2. Schema and User Creation
sql
-- Connect to the new database as nexus-admin
\c "nexus-app"

-- Create application schema
CREATE SCHEMA "nexus_schema"
    AUTHORIZATION "nexus-admin";

-- Create application user with limited privileges
CREATE USER "nexus-app" WITH PASSWORD 'nexus-app'
    CONNECTION LIMIT 50;
3. Table and Index Creation
sql
-- Create USERS table with audit columns
CREATE TABLE "nexus_schema"."USERS" (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

   CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Create performance indexes
CREATE INDEX idx_users_email ON "nexus_schema"."USERS" (email);
CREATE INDEX idx_users_active ON "nexus_schema"."USERS" (is_active);
CREATE INDEX idx_users_name ON "nexus_schema"."USERS" (last_name, first_name);
4. Permission Configuration
sql
-- Grant schema usage to application user
GRANT USAGE ON SCHEMA "nexus_schema" TO "nexus-app";

-- Grant limited privileges (no DELETE)
GRANT SELECT, INSERT, UPDATE ON ALL TABLES
    IN SCHEMA "nexus_schema" TO "nexus-app";

-- Grant sequence permission for auto-increment
GRANT USAGE, SELECT ON ALL SEQUENCES
    IN SCHEMA "nexus_schema" TO "nexus-app";

-- Set default privileges for future tables
ALTER DEFAULT PRIVILEGES
    IN SCHEMA "nexus_schema"
    GRANT SELECT, INSERT, UPDATE ON TABLES
    TO "nexus-app";

ALTER DEFAULT PRIVILEGES
    IN SCHEMA "nexus_schema"
    GRANT USAGE, SELECT ON SEQUENCES
    TO "nexus-app";
5. Sample Data Insertion
sql
-- Insert initial admin user
INSERT INTO "nexus_schema"."USERS" (
    username,
    email,
    password_hash,
    first_name,
    last_name,
    is_active,
    created_by,
    updated_by
) VALUES (
    'admin',
    'admin@nexus.example',
    crypt('securepassword', gen_salt('bf')),
    'System',
    'Administrator',
    TRUE,
    'system',
    'system'
);

-- Insert sample regular user
INSERT INTO "nexus_schema"."USERS" (
    username,
    email,
    password_hash,
    first_name,
    last_name
) VALUES (
    'jdoe1',
    'john.doe1@example.com',
    crypt('userpassword', gen_salt('bf')),
    'John',
    'Doe'
);
6. Verification Queries
sql
-- Verify user privileges

select *from "nexus_schema"."USERS";

SELECT grantee, privilege_type, table_name
FROM information_schema.role_table_grants
WHERE grantee = 'nexus-app';

-- Test connection as application user
\c "nexus-app" "nexus-app"
SET search_path TO "nexus_schema";

-- Test valid operations (should work)
INSERT INTO "nexus_schema"."USERS" (username, email, password_hash)
VALUES ('test', 'test@example.com', crypt('testpass', gen_salt('bf')));

UPDATE "nexus_schema"."USERS" SET first_name = 'Test' WHERE username = 'test';

SELECT * FROM "nexus_schema"."USERS" WHERE username = 'test';

-- Test invalid operation (should fail)
DELETE FROM "nexus_schema"."USERS" WHERE username = 'test';
-- ERROR:  permission denied for table USERS