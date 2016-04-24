package genetic;

import function.BiFunctionNode;
import function.UniFunctionNode;
import function.interfaces.FunctionNode;
import function.interfaces.Leaf;
import function.interfaces.Node;
import function.supplier.FunctionSetSupplier;
import function.supplier.TerminalSetSupplier;
import org.apache.commons.math4.genetics.Chromosome;
import org.apache.commons.math4.genetics.MutationPolicy;

import java.util.Random;

/**
 * Created by kirill on 24.04.16.
 */
public class RandomNodeMutation implements MutationPolicy {
    private Random random = new Random();
    private TerminalSetSupplier terminalSetSupplier;
    private FunctionSetSupplier functionSetSupplier;

    public RandomNodeMutation(TerminalSetSupplier terminalSetSupplier, FunctionSetSupplier functionSetSupplier) {
        this.terminalSetSupplier = terminalSetSupplier;
        this.functionSetSupplier = functionSetSupplier;
    }

    public Chromosome mutate(Chromosome original) {
        if (!(original instanceof FunctionChromosome)) {
            throw new IllegalArgumentException("valid only for tree-based chromosomes");
        }
        FunctionChromosome copyChromosome = ((FunctionChromosome) original).deepCopyExceptData();
        FunctionNode parentNode = copyChromosome.getRandomFunctionNode();
        Node[] someNodes = parentNode.getChildren();
        int index = random.nextInt(someNodes.length);
        Node toChange = someNodes[index];

        if (toChange instanceof Leaf) {
            parentNode.setChildren(index, terminalSetSupplier.get());
        }
        if (toChange instanceof UniFunctionNode) {
            FunctionNode newNode;
            do {
                newNode = functionSetSupplier.get();
            } while (!(newNode instanceof UniFunctionNode));
            newNode.swapChildren((FunctionNode) toChange);
            parentNode.setChildren(index, newNode);
        }
        if (toChange instanceof BiFunctionNode) {
            ((BiFunctionNode) toChange).swapArguments();
        }
        return copyChromosome;
    }
}
