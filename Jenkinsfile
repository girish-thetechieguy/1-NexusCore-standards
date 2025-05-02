pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir('/back-end') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}