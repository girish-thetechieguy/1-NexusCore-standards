pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Clone') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/girish-thetechieguy/1-NexusCore-standards.git'
            }
        }
        stage('Build') {
            steps {
                // Run Maven on a Unix agent.
                sh "mvn clean install"
            }
        }
        stage('Docker Image') {
            steps {
                sh 'docker build -t girishtechieguy/back-end-0.0.1 .'
            }
        }
    }
}