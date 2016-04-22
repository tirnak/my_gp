package function.interfaces;

/**
 * Created by tirnak on 22.04.2016.
 */
public interface FunctionNode extends Node, Cloneable {
    public int getArity();
    public void swapChildren(FunctionNode node);
    public FunctionNode getClone();
}
