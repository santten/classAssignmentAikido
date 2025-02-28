pipeline {
  agent any
    stages {
      stage('Checking'){
        steps {
          git branch: 'main', url: 'https://github.com/santten/classAssignmentAikido.git'
        }
      }

      stage('Build'){
        steps {
          bat 'mvn clean install'
        }
      }

       stage('Tests') {
        steps {
          bat "mvn test jacoco:report"
        }
        post {
          always {
            junit '**/target/surefire-reports/TEST-*.xml'
            jacoco execPattern: '**/target/jacoco.exec',
                   classPattern: '**/target/classes',
                   sourcePattern: '**/src/main/java',
                   exclusionPattern: '**/test/**'
          }
        }
      }
    }
}