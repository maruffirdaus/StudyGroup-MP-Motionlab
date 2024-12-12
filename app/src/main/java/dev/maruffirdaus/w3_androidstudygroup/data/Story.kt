package dev.maruffirdaus.w3_androidstudygroup.data

import androidx.annotation.DrawableRes

data class Story(
    val username: String,
    @DrawableRes val profilePicture: Int?,
    val content: String
)
