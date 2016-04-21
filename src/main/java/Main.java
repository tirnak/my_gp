import function.BiFunctionNode;
import function.ConstantNode;
import function.FunctionNode;
import function.VariableNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kise0116 on 21.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        FunctionNode head = new BiFunctionNode((x, y) -> x - y);
        head.setChild(0, new VariableNode('x'));
        head.setChild(1, new ConstantNode(10));
        Map<Character, Double> varsMap = new HashMap<>();
        varsMap.put('x', 20.0);
        head.setVariableValues(varsMap);
        System.out.println(head.eval());
    }
}
