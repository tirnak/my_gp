package function;

import function.interfaces.Leaf;
import function.interfaces.Node;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by tirnak on 21.04.2016.
 */
public class ConstantNode implements Leaf {
    private double value;
    public double eval() {
        return value;
    }
    public void fillChildren(Supplier<? extends Node> supplier) {throw new UnsupportedOperationException("");}

    @Override
    public Node[] getChildren() {
        return new Node[0];
    }

    public void setVariableValues(Map<Character, Double> varsMap) {}

    public ConstantNode(double value) {
        this.value = value;
    }
}
