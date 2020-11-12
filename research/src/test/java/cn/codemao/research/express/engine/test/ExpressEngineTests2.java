package cn.codemao.research.express.engine.test;


import cn.codemao.research.express.engine.Args;
import cn.codemao.research.express.engine.RangeAviatorFunction;
import cn.codemao.research.express.engine.RangeQLExpressFunction;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author switch
 * @since 2020/6/19
 */
@BenchmarkMode({Mode.Throughput/*, Mode.AverageTime*/})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 60)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class ExpressEngineTests2 {
    // 0.5 * 0.02 + 0.5 * 0.1 = 0.01 + 0.05 = 0.06
    private static final String EXPRESS = "0.5*rate+0.5*range(rate)";
    private static final BigDecimal RATE = new Args().getMinLearningRate();
    private AviatorEvaluatorInstance aviatorEvaluator;
    private Expression cacheExpression;
    private ExpressRunner expressRunner;
    private ExpressRunner expressCacheRunner;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ExpressEngineTests2.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(opt).run();
    }

    @Setup
    public void prepare() {
        aviatorEvaluator = AviatorEvaluator.newInstance();
        aviatorEvaluator.addFunction(new RangeAviatorFunction());
        AviatorEvaluatorInstance aviatorCacheEvaluator = AviatorEvaluator.newInstance();
        aviatorCacheEvaluator.addFunction(new RangeAviatorFunction());
        cacheExpression = aviatorEvaluator.compile(EXPRESS, true);
        expressRunner = new ExpressRunner(true, false);
        expressRunner.addFunction("range", new RangeQLExpressFunction());
        expressCacheRunner = new ExpressRunner(true, false);
        expressCacheRunner.addFunction("range", new RangeQLExpressFunction());
    }

    @Benchmark
    public void measureAviatorCache() {
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("rate", RATE);
        cacheExpression.execute(mapping);
    }

    @Benchmark
    public void measureAviator() {
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("rate", RATE);
        aviatorEvaluator.execute(EXPRESS, mapping);
    }

    @Benchmark
    public void measureQLExpressCache() throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("rate", RATE);
        expressCacheRunner.execute(EXPRESS, context, null, true, false);
    }

    @Benchmark
    public void measureQLExpress() throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("rate", RATE);
        expressRunner.execute(EXPRESS, context, null, false, false);
    }


    private void printResult(String result) {
        System.out.println(new BigDecimal(result).setScale(2, RoundingMode.HALF_UP));
    }
}
