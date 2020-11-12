package cn.codemao.research.express.engine

import cn.codemao.common.spring.core.parseJSON
import com.googlecode.aviator.runtime.function.AbstractFunction
import com.googlecode.aviator.runtime.function.FunctionUtils
import com.googlecode.aviator.runtime.type.AviatorNil
import com.googlecode.aviator.runtime.type.AviatorObject
import com.googlecode.aviator.runtime.type.AviatorString
import com.ql.util.express.Operator
import java.math.BigDecimal

/**
 *
 *
 * @author switch
 * @since 2020/6/22
 */
class UserAviatorFunction : AbstractFunction() {

    override fun call(env: MutableMap<String, Any>?, arg1: AviatorObject?): AviatorObject {
        val string = FunctionUtils.getStringValue(arg1, env)
        println(string)
        println(string.parseJSON<User>())
        return AviatorString("")
    }

    override fun getName(): String {
        return "user"
    }
}

class UserQLExpressFunction : Operator() {
    override fun executeInner(list: Array<out Any>?): Any {
        val string = list?.get(0).toString()
        println(string)
        println(string.parseJSON<User>())
        return ""
    }
}

data class User(var name: String)