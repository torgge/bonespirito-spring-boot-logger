package com.bonespirito.logtracing.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("bonespirito")
open class LoggerProperties(
    var token: String = "",
    var prefix: String = "",
    var uri: String = "https://www.uuidtools.com/api/generate/v4"
)
