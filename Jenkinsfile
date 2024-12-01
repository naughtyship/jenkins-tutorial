pipeline{
    agent{
        docker{
            image 'maven:3.8.5-openjdk-11' //Docker image for Maven Build
        }
    }
    environment {
        DOCKERHUB_REPO = 'naughtyship/simple-calculator'
        APP_VERSION = "1.0-SNAPSHOT"
        DOCKERHUB_USERNAME = 'naughtyship' // Your Docker Hub username
        DOCKERHUB_TOKEN = credentials('dockerhub-token') // The ID of the Docker Hub token in Jenkins credentials
    }

    stages{
        // checking out the git code
        stage('Checkout'){
            steps{
                echo "***** Checking out the source code *****"
                checkout scm
            }
            
        }
        // build the jar file
        stage('Build'){
            steps{
                echo "***** Building the Application *****"
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building the Docker image..."
                sh """
                    docker build -t ${DOCKERHUB_REPO}:${APP_VERSION} .
                """
            }
        }

        stage('Push Docker Image') {
            steps {
                echo "Authenticating with Docker Hub using token..."
                sh """
                    echo "...... Pushing the image to the docker hub ....."
                    echo ${DOCKERHUB_TOKEN} | docker login -u ${DOCKERHUB_USERNAME} --password-stdin
                    docker push ${DOCKERHUB_REPO}:${APP_VERSION}
                """
            }
        }
    }
}