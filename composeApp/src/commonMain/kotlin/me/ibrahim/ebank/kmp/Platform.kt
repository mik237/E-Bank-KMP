package me.ibrahim.ebank.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform