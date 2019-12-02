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
        sh 'echo "Test message"'
      }
    }

    stage('maven test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('SonarQube analysis') {
      steps {
        sh 'mvn sonar:sonar'
      }
    }

  }
}