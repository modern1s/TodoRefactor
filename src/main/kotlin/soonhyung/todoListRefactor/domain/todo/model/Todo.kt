package soonhyung.todoListRefactor.domain.todo.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "todo")
class Todo (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user : User,

    @Column(name = "content")
    var conent : String,

    @Column(name = "date")
    var date : LocalDate


)