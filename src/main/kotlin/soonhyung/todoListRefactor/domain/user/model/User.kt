package soonhyung.todoListRefactor.domain.user.model

import jakarta.persistence.*
import soonhyung.todoListRefactor.common.model.BaseTime
import soonhyung.todoListRefactor.common.status.Gender
import soonhyung.todoListRefactor.common.status.Role
import soonhyung.todoListRefactor.domain.user.dto.UserResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["loginId"])]
)
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long? = null,

    @Column(nullable = false, length = 30, updatable = false)
    val loginId : String,

    @Column(nullable = false, length = 100)
    var password: String,

    @Column(nullable = false, length = 10)
    var name: String,

    @Column
    @Temporal(TemporalType.DATE)
    var birthDate: LocalDate,

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    val gender : Gender,

    @Column(nullable = false, length = 50)
    val email : String,

){
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    val userRole : List<UserRole>? = null

    private fun LocalDate.formatDate():String =
        this.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    fun toDto(): UserResponse =
        UserResponse(id!!, loginId, name, birthDate.formatDate(), gender.desc, email)
}
@Entity
class UserRole(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    val role: Role,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_user_role_member_id"))
    val user: User,
)
