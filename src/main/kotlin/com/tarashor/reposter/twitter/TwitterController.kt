package com.tarashor.reposter.twitter

import io.github.redouane59.twitter.TwitterClient
import io.github.redouane59.twitter.dto.tweet.MediaCategory
import io.github.redouane59.twitter.dto.tweet.TweetParameters
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
class TwitterController(
    private val twitterClient: TwitterClient
) {

    @GetMapping("/post")
    fun tweet(): ResponseEntity<String> {
        val media = twitterClient.uploadMedia(File("test.jpg"), MediaCategory.TWEET_IMAGE)
        val tweet = TweetParameters.builder()
            .text("Hello, Twitter!")
            .media(TweetParameters.Media.builder().mediaIds(listOf(media.mediaId)).build())
            .build()
        val result = twitterClient.postTweet(tweet)
        return ResponseEntity.ok(result.id)
    }
}
