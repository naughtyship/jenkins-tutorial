pipeline{
    agent{
        docker{
            image 'maven:3.8.5-openjdk-11' //Docker image for Maven Build
        }
    }

    stages{
        // checking out the git code
        stage('Checkout'){
            echo "***** Checking out the source code *****"
            checkout scm
        }
        // build the jar file
        stage('Build'){
            steps{
                echo "***** Building the Application *****"
                sh 'mvn clean package'
            }
        }
    }
}