package dev.maruffirdaus.w2_androidstudygroup.data

data class User(
    val username: String,
    val email: String,
    val password: String
)

object Data {
    var users: Map<String, User> = mapOf(
        Pair(
            "maruffirdaus",
            User(
                "maruffirdaus",
                "maruffirdaus@outlook.com",
                "admin"
            )
        )
    )
}