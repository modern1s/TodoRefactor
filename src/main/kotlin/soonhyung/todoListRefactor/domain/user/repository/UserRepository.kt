package soonhyung.todoListRefactor.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import soonhyung.todoListRefactor.domain.user.model.User
import soonhyung.todoListRefactor.domain.user.model.UserRole

interface UserRepository : JpaRepository<User, Long>{
    fun findByLoginId(loginId :String): User?
}

interface UserRoleRepository : JpaRepository<UserRole, Long>