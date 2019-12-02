pipeline {
  agent any
  stages {
    stage('Test Jenkins') {
      steps {
        echo 'Jenkins test message'
      }
    }

    stage('maven test') {
      environment {
        CI = 'true'
      }
      steps {
        sh 'mvn test'
        echo 'Mvn test DONE'
      }
    }

    stage('Sonar') {
      steps {
        sh 'mvn sonar:sonar'
      }
    }

  }
}