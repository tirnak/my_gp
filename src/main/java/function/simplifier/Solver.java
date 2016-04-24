package function.simplifier;

import function.interfaces.FunctionNode;
import function.interfaces.Node;

/**
 * Created by kirill on 24.04.16.
 */
public interface Solver {
    Node solve(FunctionNode node, Boolean[] changed);
    boolean correctConditions(FunctionNode node);
}
