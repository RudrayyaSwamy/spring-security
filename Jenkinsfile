pipeline {
    agent any
    tools{
        maven 'MAVEN_HOME'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/RudrayyaSwamy/spring-security']]])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t rudrayyaswamy/spring-security .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'Rudrayya@123')]) {
                   sh 'docker login -u rudrayyaswamy -p Rudrayya@123'

}
                   sh 'docker push rudrayyaswamy/devops-integration'
                }
            }
        }
    }
}