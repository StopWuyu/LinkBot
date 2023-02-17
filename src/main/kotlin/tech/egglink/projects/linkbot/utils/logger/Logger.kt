package tech.egglink.projects.linkbot.utils.logger

import org.slf4j.LoggerFactory

class Logger {
    private val logger = LoggerFactory.getLogger("LinkBot")!!
    /**
     * 信息输出
     *
     * 信息等级: INFO
     * @param message 输出信息
     * */
    fun info(message: String) {
        logger.info(message)
    }

    /**
     * 信息输出
     *
     * 信息等级: WARN
     * @param message 输出信息
     * */
    fun warn(message: String) {
        logger.warn(message)
    }

    /**
     * 信息输出
     *
     * 信息等级: ERROR
     * @param message 输出信息
     * */
    fun error(message: String) {
        logger.error(message)
    }

    /**
     * 信息输出
     *
     * 信息等级: DEBUG
     * @param message 输出信息
     * */
    fun debug(message: String) {
        logger.debug(message)
    }
}
