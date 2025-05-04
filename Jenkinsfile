pipeline {
    agent any

    environment {
        BACKEND_DIR = 'back-end'
    }

    tools {
        maven 'maven'
    }

    stages {
        stage('Clone') {
            steps {
                // Get some code from a GitHub repository
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/girish-thetechieguy/1-NexusCore-standards.git']])
            }
        }
        stage('Build') {
            steps {
                dir('back-end') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Docker Image') {
            steps {
                sh 'docker build -t girishtechieguy/back-end .'
            }
        }
        stage('Push to DockerHub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerpwd')]) {
                        sh 'docker login -u girishtechieguy -p ${dockerpwd}'
                    }
                    sh 'docker push girishtechieguy/back-end'
                }
            }
        }
        stage('Deploy to K8S'){
            steps{
                script{
                    kubernetesDeploy configs: 'deployment.yaml', kubeConfig: [path: ''], kubeconfigId: 'kubeConfig-pwd', secretName: '', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
                }
            }
        }
    }
}