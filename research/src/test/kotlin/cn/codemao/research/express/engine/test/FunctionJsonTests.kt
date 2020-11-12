package cn.codemao.research.express.engine.test

import cn.codemao.common.spring.core.toJSON
import cn.codemao.research.express.engine.User
import cn.codemao.research.express.engine.UserAviatorFunction
import cn.codemao.research.express.engine.UserQLExpressFunction
import com.googlecode.aviator.AviatorEvaluator
import com.ql.util.express.DefaultContext
import com.ql.util.express.ExpressRunner
import org.junit.Before
import org.junit.Test

/**
 *
 *
 * @author switch
 * @since 2020/6/22
 */
class FunctionJsonTests {
    private lateinit var express: String

    @Before
    fun init() {
        val user = User(name = "switch")
        println(user.toJSON())
        println(user.toJSON().toJSON())
        express = "user(${user.toJSON().toJSON()})"
        println(express)
    }

    @Test
    fun testAviatorFunctionJson() {
        val aviatorEvaluator = AviatorEvaluator.newInstance()
        aviatorEvaluator.addFunction(UserAviatorFunction())
        aviatorEvaluator.execute(express, null, true)
    }

    @Test
    fun testQLExpressFunctionJson() {
        val expressRunner = ExpressRunner(true, false)
        expressRunner.addFunction("user", UserQLExpressFunction())
        expressRunner.execute(express, null, null, true, false)
    }
}