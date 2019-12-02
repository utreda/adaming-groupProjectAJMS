pipeline {
  agent any
  stages {
    stage('Test0') {
      parallel {
        stage('Test jenkins') {
          steps {
            echo 'Jenkins message works'
          }
        }

        stage('Test shell') {
          steps {
            sh 'echo \'Shell echo works.\''
          }
        }

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

    stage('Sonnar') {
      steps {
        sh '''mvn sonar:sonar \\
  -Dsonar.host.url=http://localhost:9000 \\
  -Dsonar.login=d7b48e79637d8cff40831818bac81b40d3bc5b8b'''
      }
    }

    stage('') {
      steps {
        emailext(subject: 'Sonar report', body: 'http://localhost:9000/dashboard?id=com.adaming.groupprojectajms%3Agestion-ects', to: 'gautiem@gmail.com')
      }
    }

  }
}