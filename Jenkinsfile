pipeline {
    environment {
        DOCKERHUB_REPO = 'naughtyship/simple-calculator'
        APP_VERSION = "1.0-SNAPSHOT"
        DOCKERHUB_USERNAME = 'naughtyship' // Your Docker Hub username
        DOCKERHUB_TOKEN = credentials('dockerhub-token') // The ID of the Docker Hub token in Jenkins credentials
    }
    stages {
        stage('Checkout') {
            agent {
                docker { image 'maven:3.8.5-openjdk-11' }
            }
            steps {
                echo "***** Checking out the source code *****"
                checkout scm
            }
        }
        stage('Build') {
            agent {
                docker { image 'maven:3.8.5-openjdk-11' }
            }
            steps {
                echo "***** Building the Application *****"
                sh 'mvn clean package'
                stash includes: 'target/*.jar', name: 'built-jar' // Stash the JAR file
            }
        }
        stage('Build Docker Image') {
            agent {
                docker {
                    image 'docker:20.10.17'
                    args '--privileged -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                unstash 'built-jar' // Retrieve the JAR file from the stash
                echo "***** Building Docker Image *****"
                sh """
                docker build -t ${DOCKERHUB_REPO}:${APP_VERSION} .
                """
            }
        }
        stage('Push Docker Image') {
            agent {
                docker {
                    image 'docker:20.10.17'
                    args '--privileged -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                echo "***** Pushing Docker Image to Docker Hub *****"
                sh """
                echo ${DOCKERHUB_TOKEN} | docker login -u ${DOCKERHUB_USERNAME} --password-stdin
                docker push ${DOCKERHUB_REPO}:${APP_VERSION}
                """
            }
        }
    }
}
