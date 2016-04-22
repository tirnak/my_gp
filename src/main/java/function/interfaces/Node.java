package function.interfaces;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by tirnak on 21.04.2016.
 */
public interface Node {
    double eval();
    void fillChildren(Supplier<? extends Node> supplier);
    void setChildren(int index, Node node);
    Node[] getChildren();
    void setVariableValues(Map<Character, Double> varsMap);
}

