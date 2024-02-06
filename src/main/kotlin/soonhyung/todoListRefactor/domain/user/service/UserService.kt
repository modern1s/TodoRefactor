package soonhyung.todoListRefactor.domain.user.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import soonhyung.todoListRefactor.common.authority.JwtTokenProvider
import soonhyung.todoListRefactor.common.authority.TokenInfo
import soonhyung.todoListRefactor.common.exception.InvalidInputException
import soonhyung.todoListRefactor.common.status.Role
import soonhyung.todoListRefactor.domain.user.dto.CreateUserRequest
import soonhyung.todoListRefactor.domain.user.dto.LoginRequest
import soonhyung.todoListRefactor.domain.user.dto.UserResponse
import soonhyung.todoListRefactor.domain.user.model.User
import soonhyung.todoListRefactor.domain.user.model.UserRole
import soonhyung.todoListRefactor.domain.user.repository.UserRepository
import soonhyung.todoListRefactor.domain.user.repository.UserRoleRepository

@Transactional
@Service
class UserService (
    private val userRepository: UserRepository,
    private val userRoleRepository: UserRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    fun signUp(createUserRequest: CreateUserRequest): String {
        var user: User? = userRepository.findByLoginId(createUserRequest.loginId)
        if (user != null) {
            throw InvalidInputException("loginId", "이미등록된 ID입니다.")
        }

        user = createUserRequest.toEntity()

        userRepository.save(user)

        val userRole: UserRole = UserRole(null, Role.USER, user)
        userRoleRepository.save(userRole)

        return "회원가입이 완료되었습니다."
    }

    fun login(loginRequest: LoginRequest): TokenInfo {
        val authenticationToken =
            UsernamePasswordAuthenticationToken(loginRequest.loginId, loginRequest.password)
        val authentication =
            authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return jwtTokenProvider.createToken(authentication)
    }

    fun searchMyInfo(id: Long): UserResponse {
        val user = userRepository.findByIdOrNull(id)?: throw InvalidInputException("id", "회원번호(${id})가 존재하지 않는 유저입니다.")
        return user.toDto()
    }
}