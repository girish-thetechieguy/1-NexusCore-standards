pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/girish-thetechieguy/1-NexusCore-standards.git'

                // Run Maven on a Unix agent.
                sh 'mvn clean install'
                // dir('back-end') {
                //     sh 'mvn clean install'
                // }
            }
        }
    }
}
