package soonhyung.todoListRefactor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class TodoListRefactorApplication

fun main(args: Array<String>) {
	runApplication<TodoListRefactorApplication>(*args)
}
