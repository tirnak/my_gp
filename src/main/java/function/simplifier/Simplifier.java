package function.simplifier;

import function.interfaces.FunctionNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirill on 24.04.16.
 */
public class Simplifier {

    static List<Solver> solvers = new ArrayList<>();
    static {
        solvers.add(new ConstantSubtreeSolver());
    }

    public static void simplify(List<FunctionNode> nodes) {
        Boolean[] changed = {false};
        while (changed[0]) {
            for (Solver solver : solvers) {
                for (FunctionNode node : nodes) {
                    iterate(node, solver, changed);
                }
            }
        }
    }

    private static void iterate(FunctionNode parent, Solver solver, Boolean[] changed) {
        FunctionNode[] children = parent.getFunctionChildren();
        for (int i = 0; i < children.length; i++) {
            if (solver.correctConditions(children[i])) {
                parent.setChildren(i, solver.solve(children[i], changed));
            }
        }
    }

}
