pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git  url: 'https://github.com/Djatourou/Jenkins.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                bat "./mvnw clean package"
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
