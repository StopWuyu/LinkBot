package tech.egglink.projects.linkbot.plugins.exception

class InvalidPluginException(message: String, track: Exception? = null) : Exception(message, track)