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
        sh 'mvn clean verify sonar:sonar'
      }
    }

    stage('') {
      steps {
        slackSend(message: 'Jenkins build done !', teamDomain: 'groupegestionects', token: '8Sshcn5B6gpUo4r9ZoJbGsLi')
      }
    }

  }
}