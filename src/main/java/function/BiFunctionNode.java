package function;

import function.interfaces.FunctionNode;
import function.interfaces.Node;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class BiFunctionNode implements FunctionNode {
    public Node[] children = new Node[2];
    public BiFunction<Double, Double, Double> function;

    public BiFunctionNode(BiFunction<Double, Double, Double> function) {
        this.function = function;
    }

    public BiFunctionNode(BiFunctionNode nodeToCopy) {
        children = nodeToCopy.getChildren();
        function = nodeToCopy.function;
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
    public String toString() {
        return "(" + children[0] + " " + function + " " + children[1] + ")";
    }

    @Override
    public FunctionNode getClone() {
        FunctionNode toReturn = new BiFunctionNode(this);
        if (children[0] instanceof FunctionNode) {
            toReturn.setChildren(0, ((FunctionNode)children[0]).getClone());
        }
        if (children[1] instanceof FunctionNode) {
            toReturn.setChildren(1, ((FunctionNode) children[1]).getClone());
        }
        return toReturn;
    }

    public void swapArguments() {
        Node tmp = children[0];
        children[0] = children[1];
        children[1] = tmp;
    }


    @Override
    public FunctionNode[] getFunctionChildren() {
        if (children[0] instanceof FunctionNode && children[1] instanceof FunctionNode) {
            return (FunctionNode[]) children;
        }
        if (children[0] instanceof FunctionNode) {
            return new FunctionNode[]{(FunctionNode) children[0]};
        }
        if (children[1] instanceof FunctionNode) {
            return new FunctionNode[]{(FunctionNode) children[1]};
        }
        return new FunctionNode[0];
    }

}
