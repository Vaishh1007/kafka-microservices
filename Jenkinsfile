pipeline{
    
    agent { label "dev"};
    
    tools {
        dockerTool 'docker'
        maven 'Maven3'
    }
    
    stages{
        stage("Code Clone"){
            steps{
               script{
                   git url: "https://github.com/Vaishh1007/kafka-microservices", branch: "main"
               }
            }
        }
        
        stage("Build"){
            steps {
                script {
                    dir('Kafka-Common-Module') {
                        sh 'mvn clean install -DskipTests'
                    }
                    // Build Order Service
                    dir('Order-Service') {
                        sh 'mvn clean package -DskipTests'
                    }
                    sh 'docker build -t order-service ./Order-Service'
        
                    // Build Payment Service
                    dir('Payment-Service') {
                        sh 'mvn clean package -DskipTests'
                    }
                    sh 'docker build -t payment-service ./Payment-Service'
        
                    // Build Notification Service
                    dir('Notification-Service') {
                        sh 'mvn clean package -DskipTests'
                    }
                    sh 'docker build -t notification-service ./Notification-Service'
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                // Use withCredentials to securely log into Docker Hub
                withCredentials([usernamePassword(credentialsId: 'dockerHubCreds', 
                                 usernameVariable: 'DOCKER_USER', 
                                 passwordVariable: 'DOCKER_PASS')]) {
                    
                    sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
                    
                    // Tag and Push Order Service
                    sh "docker tag order-service \$DOCKER_USER/order-service:latest"
                    sh "docker push \$DOCKER_USER/order-service:latest"
                    
                    // Tag and Push Payment Service
                    sh "docker tag payment-service \$DOCKER_USER/payment-service:latest"
                    sh "docker push \$DOCKER_USER/payment-service:latest"
                    
                    // Tag and Push Notification Service
                    sh "docker tag notification-service \$DOCKER_USER/notification-service:latest"
                    sh "docker push \$DOCKER_USER/notification-service:latest"
                }
            }
        }

        stage("Deploy"){
            steps{
                sh "docker compose up -d"
            }
        }
    }
}
