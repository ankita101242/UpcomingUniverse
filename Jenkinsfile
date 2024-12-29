pipeline {
    agent any
    environment {
        GITHUB_REPO_URL = 'https://github.com/ankita101242/UpcomingUniverse.git'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'main', url: GITHUB_REPO_URL
                }
            }
        }
        
        stage('Build and Deploy') {
            steps {
                script {
                    sh 'kubectl apply -f frontend/frontend-deployment.yaml'
                    sh 'kubectl apply -f backend/backend-deployment.yaml'
                }
            }
        }
        
        stage('Test Deployment') {
            steps {
                script {
                    sh 'kubectl get pods'
                    sh 'kubectl get services'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

