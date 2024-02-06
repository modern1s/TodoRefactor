package soonhyung.todoListRefactor.domain.comment.model

import jakarta.persistence.*
import org.apache.catalina.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import soonhyung.todoListRefactor.common.model.BaseTime

@Entity
@Table (name = "comment")

class Comment (
    @Column(name = "content")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var user: User,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var todo: Todo,
):BaseTime(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
