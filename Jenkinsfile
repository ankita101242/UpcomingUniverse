pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "ankitaagrawal12/frontend:latest"
        BACKEND_IMAGE = "ankitaagrawal12/backend:latest"
        KUBECONFIG_CREDENTIALS_ID = "kubeconfig-credentials-id" 
        GITHUB_REPO_URL = 'https://github.com/ankita101242/UpcomingUniverse.git'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Build Docker Images') {
            steps {
                echo 'Building Docker images...'
                script {
                    sh 'docker build -t $DOCKER_IMAGE ./frontend'
                    sh 'docker build -t $BACKEND_IMAGE ./backend'
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        docker.image("ankitaagrawal12/backend:latest").push()
                        docker.image("ankitaagrawal12/frontend:latest").push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh 'kubectl apply -f ./kubernetes/frontend-deployment.yaml'
                    sh 'kubectl apply -f ./kubernetes/backend-deployment.yaml'
                }
            }
        }

        stage('Open Minikube Dashboard') {
            steps {
                echo 'Opening Minikube dashboard...'
                script {
                    sh 'minikube dashboard &'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed! Please check the logs.'
        }
    }
}
