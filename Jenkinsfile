pipeline {
    agent { label 'agent1' }

    stages {
        stage('build') {
            steps {
                script {
                    sh("ls -la ./")
                    sh("whoami")
                    sh("./gradlew clean build")
                    sh("ls -la ./build/reobfJar")
                }
            }
        }
    }
}
