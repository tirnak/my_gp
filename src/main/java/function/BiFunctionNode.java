package function;

import function.interfaces.FunctionNode;
import function.interfaces.Node;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class BiFunctionNode implements FunctionNode {
    public Node[] children = new Node[2];
    public BiFunction<Double, Double, Double> function;

    public BiFunctionNode(BiFunction<Double, Double, Double> function) {
        this.function = function;
    }

    public double eval() {
        return function.apply(children[0].eval(), children[1].eval());
    }

    @Override
    public void fillChildren(Supplier<? extends Node> supplier) {
       children[0] = supplier.get();
       children[1] = supplier.get();
    }

    @Override
    public void setChildren(int index, Node node) {
        if (!(index == 0 || index == 1)) {
            throw new IllegalArgumentException("only zero and one as index argument is possible");
        }
        children[index] = node;
    }

    /**
     * defensive, but shallow copy of field
     * @return
     */
    @Override
    public Node[] getChildren() {
        return children.clone();
    }

    @Override
    public void swapChildren(FunctionNode node) {
        if (!(node instanceof BiFunctionNode)) {
            throw new IllegalArgumentException("Must swap with anothre Uni");
        }
        Node[] tmp = this.children;
        this.children = ((BiFunctionNode)node).children;
        ((BiFunctionNode)node).children = tmp;
    }


    @Override
    public void setVariableValues(Map<Character, Double> varsMap) {
        for (Node child : children) {
            child.setVariableValues(varsMap);
        }
    }

    @Override
    public int getArity() {
        return 2;
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
