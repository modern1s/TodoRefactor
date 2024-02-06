package soonhyung.todoListRefactor.domain.user.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import soonhyung.todoListRefactor.common.authority.TokenInfo
import soonhyung.todoListRefactor.common.model.BaseResponse
import soonhyung.todoListRefactor.domain.user.dto.CreateUserRequest
import soonhyung.todoListRefactor.domain.user.dto.LoginRequest
import soonhyung.todoListRefactor.domain.user.dto.UserResponse
import soonhyung.todoListRefactor.domain.user.service.UserService

@RequestMapping("api/user")
@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid createUserRequest: CreateUserRequest): BaseResponse<Unit> {
        val resultMsg: String = userService.signUp(createUserRequest)
        return BaseResponse(message = resultMsg)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): BaseResponse<TokenInfo> {
        val tokenInfo = userService.login(loginRequest)
        return BaseResponse(data = tokenInfo)
    }
    @GetMapping("/info/{id}")
    fun searchMyInfo(@PathVariable id: Long): BaseResponse<UserResponse>{
        val response = userService.searchMyInfo(id)
        return BaseResponse(data = response)
    }
}
