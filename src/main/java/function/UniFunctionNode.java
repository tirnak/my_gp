package function;

import java.util.Map;
import java.util.function.Function;

public class UniFunctionNode implements FunctionNode{
    public FunctionNode child;
    public Function<Double, Double> function;

    public double eval() {
        return function.apply(child.eval());
    }

    @Override
    public void setChild(int index, FunctionNode node) {
        if (index != 0) {
            throw new IllegalArgumentException("only one argument with index zero is allowed");
        }
        child = node;
    }

    @Override
    public void setVariableValues(Map<Character, Double> varsMap) {
        child.setVariableValues(varsMap);
    }
}
