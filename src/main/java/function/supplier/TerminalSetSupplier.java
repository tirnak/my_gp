package function.supplier;

import function.ConstantNode;
import function.VariableNode;
import function.interfaces.Leaf;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Created by tirnak on 22.04.2016.
 */
public class TerminalSetSupplier implements Supplier {
    private final char[] varNames;
    private final double constantMin;
    private final double constantMax;
    private final double constantProbability;
    private final Random random = new Random();

    public TerminalSetSupplier(char[] varNames, double constantMin, double constantMax, double constantProbability) {
        if (constantMin > constantMax) {
            throw new IllegalArgumentException("min is more then max");
        }
        this.varNames = varNames;
        this.constantMin = constantMin;
        this.constantMax = constantMax;
        this.constantProbability = constantProbability;
    }

    @Override
    public Leaf get() {
        if (Math.random() < constantProbability) {
            double newRandomConstant = (Math.random() * (constantMax - constantMin)) + constantMin;
            return new ConstantNode(newRandomConstant);
        } else {
            return new VariableNode(varNames[random.nextInt(varNames.length)]);
        }
    }
}
