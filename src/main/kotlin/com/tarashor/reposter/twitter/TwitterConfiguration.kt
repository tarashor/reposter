package com.tarashor.reposter.twitter

import io.github.redouane59.twitter.TwitterClient
import io.github.redouane59.twitter.signature.TwitterCredentials
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TwitterConfiguration(
    @Value("\${twitter_api_key}")
    val apiKey: String,
    @Value("\${twitter_api_secret}")
    val apiSecret: String,
    @Value("\${twitter_access_token}")
    val accessToken: String,
    @Value("\${twitter_access_token_secret}")
    val accessTokenSecret: String
) {


    @Bean
    fun twitterClient(): TwitterClient {

        return TwitterClient(
            TwitterCredentials.builder()
                .accessToken(accessToken)
                .accessTokenSecret(accessTokenSecret)
                .apiKey(apiKey)
                .apiSecretKey(apiSecret)
                .build()
        )
    }
}
