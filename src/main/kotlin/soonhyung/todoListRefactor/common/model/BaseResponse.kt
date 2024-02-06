package soonhyung.todoListRefactor.common.model

import org.aspectj.bridge.Message
import soonhyung.todoListRefactor.common.status.ResultCode

data class BaseResponse <T>(
    val resultCode:String = ResultCode.SUCCESS.name,
    val data: T? = null,
    val message: String = ResultCode.SUCCESS.msg

)