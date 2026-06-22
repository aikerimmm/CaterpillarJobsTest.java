pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                sh './gradlew clean test || true'
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'build/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            sh 'java -DconfigFile=notifications/config.json -jar notifications/allure-notifications-4.11.0.jar'
        }
    }
}