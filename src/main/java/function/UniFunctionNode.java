package function;

import function.functions.Id;
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
    public FunctionNode[] getFunctionChildren() {
        if (child instanceof FunctionNode) {
            return new FunctionNode[]{(FunctionNode) child};
        }
        return new FunctionNode[0];
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

    public UniFunctionNode(UniFunctionNode nodeToCopy) {
        child = nodeToCopy.child;
        function = nodeToCopy.function;
    }

    @Override
    public String toString() {
        if (function instanceof Id) {
            return child.toString();
        }
        return "(" + function + " " + child + ")";
    }

    @Override
    public FunctionNode getClone() {
        FunctionNode toReturn = new UniFunctionNode(this);
        if (child instanceof FunctionNode) {
            toReturn.setChildren(0, ((FunctionNode) child).getClone());
        }
        return toReturn;
    }
}
