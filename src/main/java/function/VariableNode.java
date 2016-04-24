package function;

import function.interfaces.Leaf;
import function.interfaces.Node;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by tirnak on 21.04.2016.
 */
public class VariableNode implements Leaf {
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

    public void fillChildren(Supplier<? extends Node> supplier) {
        throw new UnsupportedOperationException("variable is a constant");
    }

    @Override
    public Node[] getChildren() {
        return new Node[0];
    }

    @Override
    public String toString() {
        return name + "";
    }
}
