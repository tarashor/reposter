package com.tarashor.reposter

import com.tarashor.reposter.telegram.TelegramBot
import io.github.redouane59.twitter.TwitterClient
import io.github.redouane59.twitter.dto.tweet.MediaCategory
import io.github.redouane59.twitter.dto.tweet.TweetParameters
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.File

@Component
class Reposter(
    private val twitterClient: TwitterClient,
    private val telegramBot: TelegramBot
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        telegramBot.start { fileBytes, fileName, caption ->
            postToTwitter(fileBytes, fileName, caption)
        }
    }

    private fun postToTwitter(fileBytes: ByteArray?, fileName:String, caption: String) {
        if (fileBytes == null) return

        val media = twitterClient.uploadMedia(fileName, fileBytes, MediaCategory.TWEET_IMAGE)
        val mediaParameters = TweetParameters.Media.builder()
            .mediaIds(listOf(media.mediaId))
            .build()
        val tweet = TweetParameters.builder()
            .text("$caption \n #meme")
            .media(mediaParameters)
            .build()
        val result = twitterClient.postTweet(tweet)
    }

    fun postToInstagram(photo: File, caption: String) {
        // Note: Instagram doesn't have an official API for posting.
        // You might need to use a third-party library or service.
        // This is a placeholder function to represent the concept.
    }

}
