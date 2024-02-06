package soonhyung.todoListRefactor.domain.user.dto

data class UserResponse (
    val id : Long,
    val loginId : String,
    val name : String,
    val birthDate : String,
    val gender: String,
    val email : String,
)