package ifpb.gpes.graph;

import java.math.BigDecimal;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 18/11/2017, 15:36:16
 */
public class Metric {

    private final String source;
    private final String target;
    private final int callIn;
    private final int callOut;
    private final StrategyMetric strategyMetric;

    public Metric(String source, String target, int callIn, int callOut, StrategyMetric strategy) {
        this.source = source;
        this.target = target;
        this.callIn = callIn;
        this.callOut = callOut;
        this.strategyMetric = strategy;
    }

    public Metric(String source, String target, int callIn, int callOut) {
        this(source, target, callIn, callOut, new DefaultStrategyMetric());
    }

    public Metric() {
        this("", "", 0, 1);
    }

    public BigDecimal value() {
        return this.strategyMetric.compute(callIn, callOut);
    }

    @Override
    public String toString() {
//        10 -> 28 in:1 out:3 metric:0.3333333
        return new StringBuilder(source).append(" -> ").append(target)
                .append(" in").append(":").append(callIn)
                .append(" out").append(":").append(callOut)
                .append(" metric").append(":").append(value())
                .toString();
    }

}
