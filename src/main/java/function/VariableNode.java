package function;

import java.util.Map;

/**
 * Created by kise0116 on 21.04.2016.
 */
public class VariableNode implements FunctionNode {
    private char name;
    private Double value;

    public double getValue() {
        return value;
    }

    public void setVariableValues(Map<Character, Double> varsMap) {
        if (!varsMap.containsKey(name)) {
            throw new IllegalArgumentException("No such value names given");
        }
        this.value = varsMap.get(name);
    }


    public VariableNode(char name) {
        this.name = name;
    }

    public double eval() {
        if (value == null) {
            throw new UnsupportedOperationException("variable wasn't substituted");
        }
        return value;
    }

    public void setChild(int index, FunctionNode node) {
        throw new UnsupportedOperationException("variable is a constant");
    }
}
