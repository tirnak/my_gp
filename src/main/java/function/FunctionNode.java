package function;

import java.util.Map;

/**
 * Created by kise0116 on 21.04.2016.
 */
public interface FunctionNode {
    double eval();
    void setChild(int index, FunctionNode node);
    void setVariableValues(Map<Character, Double> varsMap);
}

