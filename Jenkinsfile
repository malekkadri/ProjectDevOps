pipeline {
    agent any

    environment {
        IMAGE_TAG = '1.0'
        dockerCredentials = 'DOCKER_CREDS'
        registry = 'rayenbakali/achat'
        dockerImage = ''
        SONAR_TOKEN = credentials("SONAR_TOKEN")
    }

    stages {
        stage('Clean') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Cleaning...'
                    sh "mvn clean"
                }
            }
        }
        stage('Build') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Validating...'
                    sh "mvn validate"
                    echo 'Compiling...'
                    sh "mvn compile"
                }
            }
        }
        stage('Docker Image') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Building the docker image...'
                    script {
                        dockerImage = docker.build registry + ":$IMAGE_TAG"
                    }
                }
            }
        }
        stage('Docker Push') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Pushing the docker image to docker hub...'
                    script {
                        docker.withRegistry('', dockerCredentials) {
                            dockerImage.push()
                        }
                    }
                }
            }
        }
        stage("Docker compose") {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Running in compose!'
                    sh "docker compose up -d"
                }
            }
        }
        stage('Testing my Java code') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Testing..'
                    sh "mvn test -Dtest=ProductServiceImplTest -Dspring.profiles.active=test"
                }
            }
        }
        stage('Code Coverage') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Generating code coverage files..'
                    sh "mvn jacoco:report"
                }
            }
        }
        stage('SONAR ANALYZER') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'SonarQube running...'
                    sh "mvn sonar:sonar -Dsonar.token=$SONAR_TOKEN"
                }
            }
        }
        stage('Nexus') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Deploying to nexus...'
                    sh "mvn clean deploy -DskipTests"
                }
            }
        }
    }
}