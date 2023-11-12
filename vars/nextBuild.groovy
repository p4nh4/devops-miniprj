def call(DOCKER_REGISTRY, CONTAINER_BUILD_NAME, DOCKER_TAG, TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID) {
    cleanDockerImages(DOCKER_REGISTRY, CONTAINER_BUILD_NAME, DOCKER_TAG)
    
    try {
        buildDockerImage(DOCKER_REGISTRY, CONTAINER_BUILD_NAME, DOCKER_TAG)
        sendTelegramMessage("Docker build Successfully!")
    } catch (Exception e) {
        echo "Build failed, retrying..."
        cleanDockerImages(DOCKER_REGISTRY, CONTAINER_BUILD_NAME, DOCKER_TAG)
        buildDockerImage(DOCKER_REGISTRY, CONTAINER_BUILD_NAME, DOCKER_TAG)
        sendTelegramMessage("Docker build failed!")
        throw e
    }
}

def cleanDockerImages(DOCKER_REGISTRY, CONTAINER_BUILD_NAME, DOCKER_TAG) {
    sh """
        docker rmi -f ${CONTAINER_BUILD_NAME}:${DOCKER_TAG}
        docker rmi -f ${DOCKER_REGISTRY}/${CONTAINER_BUILD_NAME}:${DOCKER_TAG}
    """
}

def buildDockerImage(DOCKER_REGISTRY, CONTAINER_BUILD_NAME, DOCKER_TAG) {
    sh "docker build -t ${CONTAINER_BUILD_NAME}:${DOCKER_TAG} -t ${DOCKER_REGISTRY}/${CONTAINER_BUILD_NAME}:${DOCKER_TAG} ."
}

def sendTelegramMessage(message) {
    sh "curl -s -X POST https://api.telegram.org/bot${TELEGRAM_BOT_TOKEN}/sendMessage -d chat_id=${TELEGRAM_CHAT_ID} -d text='${message}'"
}