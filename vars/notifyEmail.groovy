def call(String status = null) {
    def result = status ?: (currentBuild.currentResult ?: 'SUCCESS')
    def target = env.TAG_NAME ? 'PROD' : (env.BRANCH_NAME == 'main' ? 'PRE-PROD' : 'DEV')
    def subject = "[${target}] ${env.JOB_NAME} #${env.BUILD_NUMBER} - ${result}"
    def body = """Pipeline  : ${target}
Status    : ${result}
Branch/Tag: ${env.TAG_NAME ?: env.BRANCH_NAME}
Build     : ${env.BUILD_URL}
Console   : ${env.BUILD_URL}console
"""
    try {
        writeFile file: '.notify_mail.py', text: libraryResource('notify_mail.py')
        writeFile file: '.mail_subject.txt', text: subject
        writeFile file: '.mail_body.txt', text: body
        withCredentials([usernamePassword(credentialsId: 'smtp-gmail',
                                          usernameVariable: 'SMTP_USER',
                                          passwordVariable: 'SMTP_PASS')]) {
            sh 'python3 .notify_mail.py'
        }
    } catch (err) {
        echo "WARNING: email nahi ja saki: ${err}"
    }
}
