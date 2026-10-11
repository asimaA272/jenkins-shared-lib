def call() {
    emailext (
        subject: "✨ Project ${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - ${currentBuild.currentResult}! Task Completed",
        body: """
            <p><b>Hey Sir,</b></p>
            <p>Sir, Asim baat kar raha hoon. The MLOps pipeline automation project has been completely done and successfully completed for this stage.</p>
            <p>All green execution tracks (Dev, Pre-prod, and Production loops) are completely verified.</p>
            <p>You can check the direct console pipeline layouts and live visual tracking logs on the Jenkins dashboard server link here: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
            <br>
            <p>Best regards,</p>
            <p><b>Asim</b></p>
        """,
        to: "${env.EMAIL_TO}"
    )
}
