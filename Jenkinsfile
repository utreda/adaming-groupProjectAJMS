pipeline {
  agent any
  stages {
    stage('Test0') {
      steps {
        echo 'Test message'
      }
    }

    stage('Test') {
      environment {
        CI = 'true'
      }
      steps {
        sh './jenkins/scripts/test.sh'
      }
    }

  }
}