package com.tarashor.reposter.telegram

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.channel
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TelegramBot(
    @Value("\${telegram_token}")
    private val telegramToken: String
) {
    private val bot = bot {
        token = telegramToken
        dispatch {
            channel {
                channelPost.photo?.last()?.let { photo ->
                    val caption = channelPost.caption ?: ""

                    val fileBytes = bot.downloadFileBytes(photo.fileId)
                    val fileName = photo.fileUniqueId

                    callback?.invoke(fileBytes, fileName, caption)
                }
            }
        }
    }

    private var callback: TelegramCallback? = null

    fun start(callback: TelegramCallback) {
        this.callback = callback
        bot.startPolling()
    }
}

typealias TelegramCallback = (imageStream: ByteArray?, fileName: String, caption: String) -> Unit
