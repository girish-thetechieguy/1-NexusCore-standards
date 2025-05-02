pipeline {
    agent any
    stages {
        stage('Provide - Access') {
                    steps {
                            sh 'cd back-end'
                    }
                }
        stage('Build') {
            steps {
                dir('/back-end') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}