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

        stage('test shell') {
          steps {
            sh '''echo \'test shell\'
'''
          }
        }

      }
    }

    stage('maven test') {
      environment {
        CI = 'true'
      }
      steps {
        sh 'maven test'
      }
    }

  }
}