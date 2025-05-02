pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir('cd back-end') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}