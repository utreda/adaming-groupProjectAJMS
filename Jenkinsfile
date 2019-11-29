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
        sh 'mvn clean verify sonar:sonar'
      }
    }

    stage('') {
      steps {
        withSonarQubeEnv(installationName: 'SonarQube', credentialsId: '81224667-0c88-42ec-84f9-03e0e859535e')
      }
    }

  }
}