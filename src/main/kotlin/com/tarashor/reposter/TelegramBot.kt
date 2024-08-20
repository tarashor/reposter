package com.tarashor.reposter

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.channel
import com.github.kotlintelegrambot.entities.TelegramFile
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import twitter4j.StatusUpdate
import twitter4j.Twitter
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream

@Component
class TelegramBot(
    private val twitter: Twitter
) : CommandLineRunner{
    override fun run(vararg args: String?) {
        val bot = bot {
            token = "7229516327:AAG8FoDMXutlYwhih-lHThp_hd92bPDS8qY"
            dispatch {
                channel {

                    channelPost.photo?.last()?.fileId?.let { fileId ->
                        val caption = channelPost.caption ?: ""

                        val photoFile = ByteArrayInputStream(bot.downloadFileBytes(fileId))

                        println("uploaded - $fileId")
                        // Post to Twitter
                        postToTwitter(photoFile, caption)
                    }
                }
            }
        }
        bot.startPolling()
    }


    fun downloadPhoto(fileId: String): File {
        // Implement photo download logic here
        // Return the downloaded file
        return File(".")
    }

    fun postToTwitter(photo: InputStream, caption: String) {


        val statueUpdate = StatusUpdate(caption).apply {  setMedia("photo", photo) }
        val status = twitter.updateStatus(statueUpdate)

        println("Successfully updated Twitter status: ${status.text}")
    }

    fun postToInstagram(photo: File, caption: String) {
        // Note: Instagram doesn't have an official API for posting.
        // You might need to use a third-party library or service.
        // This is a placeholder function to represent the concept.
    }

}
