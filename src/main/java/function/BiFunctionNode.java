package function;

import java.util.Map;
import java.util.function.BiFunction;

public class BiFunctionNode implements FunctionNode {
    public FunctionNode[] children = new FunctionNode[2];
    public BiFunction<Double, Double, Double> function;

    public BiFunctionNode(BiFunction<Double, Double, Double> function) {
        this.function = function;
    }

    public double eval() {
        return function.apply(children[0].eval(), children[1].eval());
    }

    @Override
    public void setChild(int index, FunctionNode node) {
        if (index < 0 || index > 1) {
            throw new IllegalArgumentException("only two arguments with indices {0,1} are allowed");
        }
        children[index] = node;
    }

    @Override
    public void setVariableValues(Map<Character, Double> varsMap) {
        for (FunctionNode child : children) {
            child.setVariableValues(varsMap);
        }
    }
}
