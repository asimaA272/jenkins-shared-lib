def call(String status = null) {
    def result = status ?: (currentBuild.currentResult ?: 'SUCCESS')
    def target = env.TAG_NAME ? 'PROD' : (env.BRANCH_NAME == 'main' ? 'PRE-PROD' : 'DEV')
    try {
        emailext(
            to: env.EMAIL_TO,
            subject: "[${target}] ${env.JOB_NAME} #${env.BUILD_NUMBER} - ${result}",
            body: """Pipeline : ${target}
Status   : ${result}
Branch/Tag: ${env.TAG_NAME ?: env.BRANCH_NAME}
Build    : ${env.BUILD_URL}
Console  : ${env.BUILD_URL}console
""",
            attachLog: result != 'SUCCESS'
        )
        echo "Email bhej di gayi: ${env.EMAIL_TO}"
    } catch (err) {
        echo "WARNING: email nahi ja saki: ${err}"
    }
}
