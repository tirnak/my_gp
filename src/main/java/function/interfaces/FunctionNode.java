package function.interfaces;

/**
 * Created by tirnak on 22.04.2016.
 */
public interface FunctionNode extends Node, Cloneable {
    int getArity();
    FunctionNode[] getFunctionChildren();
    void swapChildren(FunctionNode node);
    FunctionNode getClone();
}
