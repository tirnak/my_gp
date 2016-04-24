package function.simplifier;

import function.ConstantNode;
import function.VariableNode;
import function.interfaces.FunctionNode;
import function.interfaces.Node;

/**
 * Created by kirill on 24.04.16.
 */
public class ConstantSubtreeSolver implements Solver {
    @Override
    public Node solve(FunctionNode node, Boolean[] changed) {
        if (!endsWithConst(node)) {
            return node;
        }
        changed[0] = true;
        return new ConstantNode(node.eval());
    }

    @Override
    public boolean correctConditions(FunctionNode node) {
        return endsWithConst(node);
    }

    boolean endsWithConst(FunctionNode Node) {
        boolean result = true;
        for (Node child : Node.getChildren()) {
            if (child instanceof VariableNode) {
                return false;
            }
            if (child instanceof FunctionNode) {
                result = result && endsWithConst((FunctionNode) child);
            }
        }
        return result;
    }
}
