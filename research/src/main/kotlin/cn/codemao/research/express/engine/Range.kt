package cn.codemao.research.express.engine

import com.googlecode.aviator.runtime.function.AbstractFunction
import com.googlecode.aviator.runtime.function.FunctionUtils
import com.googlecode.aviator.runtime.type.AviatorDecimal
import com.googlecode.aviator.runtime.type.AviatorObject
import com.ql.util.express.Operator
import java.math.BigDecimal

/**
 *
 *
 * @author switch
 * @since 2020/6/19
 */
class RangeAviatorFunction : AbstractFunction() {
    override fun getName(): String = "range"

    override fun call(env: MutableMap<String, Any>?, arg1: AviatorObject?): AviatorObject {
        val number = FunctionUtils.getNumberValue(arg1, env)
        val decimal = AviatorDecimal(number).toDecimal(env)
        return AviatorDecimal(Range.range(decimal))
    }
}

class RangeQLExpressFunction : Operator() {
    override fun executeInner(list: Array<out Any>?): Any {
        val number = list?.get(0)
        return Range.range(number as BigDecimal)
    }

}

object Range {
    private val ZERO = BigDecimal.ZERO
    private val HALF = BigDecimal("0.5")
    private val ONE = BigDecimal.ONE
    private val RANGES = listOf(
            R(ZERO, HALF, BigDecimal("0.1"), leftClose = true, rightClose = false),
            R(HALF, ONE, BigDecimal("0.2"), true, rightClose = true)
    )

    fun range(rate: BigDecimal): BigDecimal = RANGES.first {
        if (it.left < rate && it.right > rate) {
            return@first true
        }
        if (it.leftClose && it.left.compareTo(rate) == 0) {
            return@first true
        }
        if (it.rightClose && it.right.compareTo(rate) == 0) {
            return@first true
        }
        return@first false
    }.value
}

class R(
        val left: BigDecimal,
        val right: BigDecimal,
        val value: BigDecimal,
        val leftClose: Boolean,
        val rightClose: Boolean
)