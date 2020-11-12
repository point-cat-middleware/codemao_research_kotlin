package cn.codemao.research.express.engine.test

import cn.codemao.research.express.engine.Args
import cn.codemao.research.express.engine.RangeAviatorFunction
import cn.codemao.research.express.engine.RangeQLExpressFunction
import com.googlecode.aviator.AviatorEvaluator
import com.googlecode.aviator.AviatorEvaluatorInstance
import com.ql.util.express.DefaultContext
import com.ql.util.express.ExpressRunner
import org.openjdk.jmh.annotations.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

/**
 *
 *
 * @author switch
 * @since 2020/6/19
 */
@BenchmarkMode(Mode.All)
@Warmup(iterations = 1)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
class ExpressEngineTests {
    // 0.5 * 0.02 + 0.5 * 0.1 = 0.01 + 0.05 = 0.06
    private val express = "0.5*rate+0.5*range(rate)"
    private val rate = Args().getMinLearningRate()
    private lateinit var aviatorEvaluator: AviatorEvaluatorInstance
    private lateinit var expressRunner: ExpressRunner

    @Setup
    fun prepare() {
        aviatorEvaluator = AviatorEvaluator.newInstance()
        aviatorEvaluator.addFunction(RangeAviatorFunction())
        val expressRunner = ExpressRunner(true, false)
        expressRunner.addFunction("range", RangeQLExpressFunction())
    }

    @Benchmark
    fun testAviator() {
        val result = aviatorEvaluator.execute(express, mapOf(Pair("rate", rate)))
        // printResult(result.toString())
    }

    @Benchmark
    fun testQLExpress() {
        val context = DefaultContext<String, Any>()
        context["rate"] = rate
        val result = expressRunner.execute(express, context, null, true, false)
        // printResult(result.toString())
    }

    private fun printResult(result: String) {
        println(BigDecimal(result).setScale(2, RoundingMode.HALF_UP))
    }
}

