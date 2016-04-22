package function;

import function.interfaces.FunctionNode;
import function.interfaces.Leaf;
import function.interfaces.Node;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class UniFunctionNode implements FunctionNode {
    public Node child;
    public Function<Double, Double> function;

    public double eval() {
        return function.apply(child.eval());
    }

    public UniFunctionNode(Function<Double, Double> function) {
        this.function = function;
    }

    @Override
    public void fillChildren(Supplier<? extends Node> supplier) {
        child = supplier.get();
    }

    @Override
    public void setChildren(int index, Node node) {
        if (index != 0) {
            throw new IllegalArgumentException("only zero as index argument is possible");
        }
        child = node;
    }

    @Override
    public Node[] getChildren() {
        return new Node[]{child};
    }

    @Override
    public void setVariableValues(Map<Character, Double> varsMap) {
        child.setVariableValues(varsMap);
    }

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public void swapChildren(FunctionNode node) {
        if (!(node instanceof UniFunctionNode)) {
            throw new IllegalArgumentException("Must swap with anothre Uni");
        }
        Node tmp = this.child;
        this.child = ((UniFunctionNode)node).child;
        ((UniFunctionNode)node).child = tmp;
    }

    @Override
    public FunctionNode getClone() {
        try {
            return (FunctionNode) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
