package com.bonespirito.logtracing.configuration

import com.bonespirito.logtracing.domain.MainProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@ComponentScan(
    basePackages = ["com.bonespirito.logtracing.configuration", "com.bonespirito.logtracing.service"]
)
@EnableConfigurationProperties
@ConfigurationProperties("bonespirito.logger-configuration")
@Primary
class ConfigurationUUIDProperties : MainProperties {
    override lateinit var token: String
    override lateinit var prefix: String
    override lateinit var method: String
    override lateinit var uri: String
}
