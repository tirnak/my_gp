package genetic;

import function.interfaces.FunctionNode;
import org.apache.commons.math4.exception.MathIllegalArgumentException;
import org.apache.commons.math4.genetics.Chromosome;
import org.apache.commons.math4.genetics.ChromosomePair;
import org.apache.commons.math4.genetics.CrossoverPolicy;

/**
 * Created by kirill on 24.04.16.
 */
public class ExchangeNodesCrossover implements CrossoverPolicy {

    @Override
    public ChromosomePair crossover(Chromosome chromosomeA, Chromosome chromosomeB) throws MathIllegalArgumentException {
        if (!(chromosomeA instanceof FunctionChromosome && chromosomeB instanceof FunctionChromosome)) {
            throw new IllegalArgumentException("valid only for tree-based chromosomes");
        }
        FunctionChromosome a = ((FunctionChromosome) chromosomeA).deepCopyExceptData(), b = ((FunctionChromosome) chromosomeB).deepCopyExceptData();
        for (int i = 0; i < 50; i++) {
            FunctionNode aNode = a.getRandomFunctionNode(), bNode = b.getRandomFunctionNode();
            if (aNode.getArity() == bNode.getArity()) {
                aNode.swapChildren(bNode);
                break;
            }
        }
        return new ChromosomePair(a, b);
    }
}
