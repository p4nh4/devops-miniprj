// def call(REPO_URL, CREDENTIAL_GIT, BRANCH, TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID) {
//     if (BRANCH_CHOICE == "master") {
//         echo "Clone from master"
//         try {
//             // Fetch the code from the Git repository
//             git credentialsId: CREDENTIAL_GIT, url: REPO_URL
//             sendTelegramMessage("Pull succeeded!", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)
//             //sendGmailMessage("Pull succeeded!")
//         } catch (Exception e) {
//             sendTelegramMessage("Pull failed!", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)
//             throw e
//         }
//     }else if (BRANCH_CHOICE == "main") {
//         echo "Clone from main"
//         try {
//             // Fetch the code from the Git repository
//             git branch: BRANCH, credentialsId: CREDENTIAL_GIT, url: REPO_URL
//             sendTelegramMessage("Pull succeeded!", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)
//             //sendGmailMessage("Pull succeeded!")
//         } catch (Exception e) {
//             sendTelegramMessage("Pull failed!", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)
//             throw e
//         }
//     }
// }
// def sendTelegramMessage(message, TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID) {
//     sh "curl -s -X POST https://api.telegram.org/bot${TELEGRAM_BOT_TOKEN}/sendMessage -d chat_id=${TELEGRAM_CHAT_ID} -d text='${message}'"
// }

def call(String REPO_URL, String CREDENTIAL_GIT, String BRANCH, String TELEGRAM_BOT_TOKEN, String TELEGRAM_CHAT_ID) {
    if (BRANCH == "master") {
        echo "Clone from master"
        try {
            // Fetch the code from the Git repository
            git branch: BRANCH, credentialsId: CREDENTIAL_GIT, url: REPO_URL
            // git credentialsId: CREDENTIAL_GIT, url: REPO_URL
            sendTelegramMessage("Pull succeeded!", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)
            //sendGmailMessage("Pull succeeded!")
        } catch (Exception e) {
            sendTelegramMessage("Pull failed!", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)
            throw e
        }
    }
    else if (BRANCH == "main") {
        echo "Clone from main"
        try {
            // Fetch the code from the Git repository
            git branch: BRANCH, credentialsId: CREDENTIAL_GIT, url: REPO_URL
            sendTelegramMessage("Pull succeeded!", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)
            //sendGmailMessage("Pull succeeded!")
        } catch (Exception e) {
            sendTelegramMessage("Pull failed!", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)
            throw e
        }
    }
}

def sendTelegramMessage(String message, String TELEGRAM_BOT_TOKEN, String TELEGRAM_CHAT_ID) {
    sh "curl -s -X POST https://api.telegram.org/bot${TELEGRAM_BOT_TOKEN}/sendMessage -d chat_id=${TELEGRAM_CHAT_ID} -d text='${message}'"
}