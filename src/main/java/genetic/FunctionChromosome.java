package genetic;

import function.interfaces.FunctionNode;
import function.interfaces.Node;
import function.supplier.FunctionSetSupplier;
import function.supplier.TerminalSetSupplier;
import org.apache.commons.math4.genetics.Chromosome;
import org.apache.commons.math4.util.FastMath;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * Created by tirnak on 21.04.2016.
 */
public class FunctionChromosome extends Chromosome {

    private static final Character RESULT = '\0';

    private FunctionNode head;
    private List<Map<Character, Double>> data;

    private FunctionChromosome(FunctionNode head) {
        this.head = head;
    }

    public double fitness() {
        if (null == data) {
            throw new IllegalStateException("data is not set");
        }
        double sumSqrdErr = data.stream().mapToDouble(map -> {
            head.setVariableValues(map);
            return map.get(RESULT) - head.eval();
        }).map(num -> FastMath.pow(num, 2))
          .sum();

        return sumSqrdErr == 0 ? 0 : 1 / sumSqrdErr;
    }


    public static FunctionChromosome getFunctionChromosome(int depth, FunctionSetSupplier functionSetSupplier, TerminalSetSupplier terminalSetSupplier) {
        List<List<Node>> nodesHolder = new ArrayList<>(depth);
        FunctionNode head = functionSetSupplier.get();
        nodesHolder.add(0, Collections.singletonList(head));
        for (int i = 1; i < depth; ++i) {
            List<Node> newLevel = new ArrayList<>();
            List<Node> previousLevel = nodesHolder.get(i - 1);
            if (i == depth - 1) {
                fillLeaves(previousLevel, terminalSetSupplier);
            } else {
                fillNodes(previousLevel, newLevel, functionSetSupplier);
            }
            nodesHolder.add(i, newLevel);
        }
        return new FunctionChromosome(head);
    }

    private static void fillLeaves(List<Node> previousLevel, TerminalSetSupplier terminalSetSupplier) {
        previousLevel.stream()
                .forEach(functionNode -> functionNode.fillChildren(terminalSetSupplier));
    }

    private static void fillNodes(List<Node> previousLevel, List<Node> newLevel, FunctionSetSupplier functionSetSupplier) {
        previousLevel.stream()
                .forEach(functionNode -> {
                    functionNode.fillChildren(functionSetSupplier);
                    newLevel.addAll(asList(functionNode.getChildren()));
                });
    }

    public void setData(List<Map<Character, Double>> data) {
        this.data = data;
    }

    public FunctionChromosome DeepCopyExceptData() {

    }

    public FunctionNode getRandomFunctionNode() {
        Random random = new Random();
        List<FunctionNode> nodes = getAllFunctionNodes();
        return nodes.get(random.nextInt(nodes.size()));
    }

    public List<FunctionNode> getAllFunctionNodes() {
//        if (cache == null) {
            List<FunctionNode> toReturn = new ArrayList<>();
            addNodes(head, toReturn);
         return toReturn;
    }

    private void addNodes(FunctionNode currentNode, List<FunctionNode> collector) {
        collector.add(currentNode);
        for (Node node : currentNode.getChildren()) {
            if (node instanceof FunctionNode) {
                addNodes((FunctionNode)node, collector);
            }
        }
    }
}
