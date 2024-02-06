package soonhyung.todoListRefactor.common.status

enum  class Gender(val desc: String) {
    MAN("남"),
    WOMAN("여")
}

enum class ResultCode(val msg: String){
    SUCCESS ("정상처리가 되었습니다."),
    ERROR ("에러가 발생하였습니다.")
}

enum class Role{
    USER
}