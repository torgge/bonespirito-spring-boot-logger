package com.bonespirito.logtracing.domain

interface MainProperties {
    val token: String
    val prefix: String
    val method: String
    val uri: String
}
