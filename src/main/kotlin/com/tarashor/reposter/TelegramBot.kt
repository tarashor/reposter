package com.tarashor.reposter

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.channel
import com.github.kotlintelegrambot.dispatcher.text
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import twitter4j.StatusUpdate
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder
import java.io.File

@Component
class TelegramBot : CommandLineRunner{
    override fun run(vararg args: String?) {
        val bot = bot {
            token = "7229516327:AAG8FoDMXutlYwhih-lHThp_hd92bPDS8qY"
            dispatch {
                channel {
                    val photo = channelPost.photo?.last()
                    val caption = channelPost.caption ?: ""

                    println(photo)

                    bot.downloadFileBytes(photo!!.fileId)
                    // Download the photo
                    val photoFile = downloadPhoto(photo!!.fileId)

                    // Post to Twitter
                    postToTwitter(photoFile, caption)

                    // Post to Instagram
                    postToInstagram(photoFile, caption)

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

    fun postToTwitter(photo: File, caption: String) {
//        val cb = ConfigurationBuilder()
//        cb.setDebugEnabled(true)
//            .setOAuthConsumerKey("YOUR_CONSUMER_KEY")
//            .setOAuthConsumerSecret("YOUR_CONSUMER_SECRET")
//            .setOAuthAccessToken("YOUR_ACCESS_TOKEN")
//            .setOAuthAccessTokenSecret("YOUR_ACCESS_TOKEN_SECRET")
//
//        val tf = TwitterFactory(cb.build())
//        val twitter = tf.instance
//
//        val status = twitter.updateStatus(StatusUpdate(caption).media(photo))
//        println("Successfully updated Twitter status: ${status.text}")
    }

    fun postToInstagram(photo: File, caption: String) {
        // Note: Instagram doesn't have an official API for posting.
        // You might need to use a third-party library or service.
        // This is a placeholder function to represent the concept.


    }

}
