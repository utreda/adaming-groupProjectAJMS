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
  steps{
    withSonarQubeEnv(credentialsId: 'd7b48e79637d8cff40831818bac81b40d3bc5b8b', installationName: 'My SonarQube Server') { // You can override the credential to be used
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
    }
    }
  }
}
}
