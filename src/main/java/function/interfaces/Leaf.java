package function.interfaces;

/**
 * Created by tirnak on 22.04.2016.
 */
public interface Leaf extends Node {
    @Override
    default void setChildren(int index, Node node) {
        // ignored
    }
}
