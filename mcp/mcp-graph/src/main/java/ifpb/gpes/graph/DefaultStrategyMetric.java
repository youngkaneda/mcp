package ifpb.gpes.graph;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 18/11/2017, 15:20:49
 */
public class DefaultStrategyMetric implements StrategyMetric {

    @Override
    public BigDecimal compute(int callIn, int callOut) {
        return new BigDecimal(callIn)
                .divide(new BigDecimal(callOut),
                        MathContext.DECIMAL32);
    }
}
