pipeline {
    agent any

    environment {
<<<<<<< HEAD
=======
        DOCKER_IMAGE = "ankitaagrawal12/frontend:latest"
        BACKEND_IMAGE = "ankitaagrawal12/backend:latest"
        KUBECONFIG_CREDENTIALS_ID = "kubeconfig-credentials-id" 
>>>>>>> 94bf960210736f79ec44be7fec288e0179553b6b
        GITHUB_REPO_URL = 'https://github.com/ankita101242/UpcomingUniverse.git'
    }

    stages {
        stage('Checkout Code') {
            steps {
<<<<<<< HEAD
                script {
                    git branch: 'main', url: "${GITHUB_REPO_URL}"
=======
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
>>>>>>> 94bf960210736f79ec44be7fec288e0179553b6b
                }
            }
        }

<<<<<<< HEAD
=======
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
>>>>>>> 94bf960210736f79ec44be7fec288e0179553b6b

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed! Please check the logs.'
        }
    }
}
