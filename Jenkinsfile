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
                sh './gradlew clean test'
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
           echo 'Tests finished'
       }
       success {
           sh """
               curl -s -X POST https://api.telegram.org/bot8834154040:AAEGVavg673aqizg9VcYCXfHwmCDMmbkCcg
/sendMessage \
               -d chat_id=<478526897> \
               -d text="✅ Build #${BUILD_NUMBER} PASSED%0AJob: ${JOB_NAME}%0AURL: ${BUILD_URL}"
           """
       }
       failure {
           sh """
               curl -s -X POST https://api.telegram.org/bot8834154040:AAEGVavg673aqizg9VcYCXfHwmCDMmbkCcg
/sendMessage \
               -d chat_id=<478526897> \
               -d text="❌ Build #${BUILD_NUMBER} FAILED%0AJob: ${JOB_NAME}%0AURL: ${BUILD_URL}"
           """
       }
   }