package ifpb.gpes.graph;

import java.math.BigDecimal;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 18/11/2017, 15:12:44
 */
public interface StrategyMetric {

    public BigDecimal compute(int callIn, int callOut);
}
