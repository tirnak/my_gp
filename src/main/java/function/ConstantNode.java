package function;

import java.util.Map;

/**
 * Created by kise0116 on 21.04.2016.
 */
public class ConstantNode implements FunctionNode {
    private double value;
    public double eval() {
        return value;
    }
    public void setChild(int index, FunctionNode node) {throw new UnsupportedOperationException("");}
    public void setVariableValues(Map<Character, Double> varsMap) {}

    public ConstantNode(double value) {
        this.value = value;
    }
}
