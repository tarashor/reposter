package com.tarashor.reposter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

@Configuration
class TwitterConfiguration {

    val API_KEY = "nq7XNSq3gRmTaLqs6FRXeJJfT"
    val API_SECRET = "5XLHSTl2nOsfRq7YgHQbms6336VMOuLUHBrR3J5TyXF9Gisl07"

    val ACCESS_TOKEN = "1819471608425467910-Qsz2EfVhqb8OgfqOGF1RQaXSnynU2T"
    val ACCESS_TOKEN_SECRET = "m8MQUV2jhXT4TufTWGfjuU2GOLHGcIZefaYNEthf3Y1z3"

    val CLIENT_ID = "MmRsUlRiV0RzS25TT1NqS0JkZ246MTpjaQ"
    val CLIENT_SECRET = "e4tUkA9wo0ZAficz0Jsd7mPaW5B8ey0R5pKOPuZlWBiwFBPGJL"


    @Bean
    fun twitter(): Twitter{
        val cb = ConfigurationBuilder()
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(API_KEY)
            .setOAuthConsumerSecret(API_SECRET)
            .setOAuthAccessToken(ACCESS_TOKEN)
            .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)

        return TwitterFactory(cb.build()).instance
    }
}