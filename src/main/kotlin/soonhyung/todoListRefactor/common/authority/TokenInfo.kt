package soonhyung.todoListRefactor.common.authority

data class TokenInfo (
    val grantType : String,
    val accessToken : String,
)