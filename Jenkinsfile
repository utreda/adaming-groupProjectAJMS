pipeline {
  agent any
  stages {
    stage('Test0') {
      parallel {
        stage('Test0') {
          steps {
            echo 'Test message'
          }
        }

        stage('') {
          steps {
            sh 'echo \'test shell\''
          }
        }

      }
    }

    stage('Test') {
      environment {
        CI = 'true'
      }
      steps {
        sh 'mvn --version'
      }
    }

  }
}