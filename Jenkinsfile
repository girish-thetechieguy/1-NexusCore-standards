pipeline {
    agent any

    tools {
        maven 'maven-3.8.6'
        jdk 'jdk-17'
    }

    stages {
        stage('Build') {
            steps {
                dir('back-end') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}