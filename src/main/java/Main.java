import function.BiFunctionNode;
import function.interfaces.FunctionNode;
import function.interfaces.Leaf;
import function.interfaces.Node;
import function.UniFunctionNode;
import function.supplier.FunctionSetSupplier;
import function.supplier.TerminalSetSupplier;
import genetic.FunctionChromosome;
import org.apache.commons.math4.exception.MathIllegalArgumentException;
import org.apache.commons.math4.genetics.*;

import java.util.*;

/**
 * Created by tirnak on 21.04.2016.
 */
public class Main {

    public static final int    POPULATION_SIZE   = 1000;
    public static final double CROSSOVER_RATE    = 0.9;
    public static final double MUTATION_RATE     = 0.03;
    public static final double ELITISM_RATE      = 0.1;
    public static final int    TOURNAMENT_ARITY  = 2;
    public static final int    TREE_DEPTH = 4;

    public static final char[] varNames = {'x'};

    public static void main(String[] args) {

            long startTime = System.currentTimeMillis();

            TerminalSetSupplier terminalSetSupplier = new TerminalSetSupplier(varNames, 0, 20, .25);
            FunctionSetSupplier functionSetSupplier = new FunctionSetSupplier(new FunctionNode[]{
                new BiFunctionNode((a,b) -> a * b),
                new BiFunctionNode((a,b) -> a + b),
                new UniFunctionNode(a -> a)
            });

            // initialize a new genetic algorithm
            GeneticAlgorithm ga = new GeneticAlgorithm(
                    new OnePointCrossover<Character>(), CROSSOVER_RATE,
                    new RandomNodeMutation(terminalSetSupplier, functionSetSupplier), MUTATION_RATE,
                    new TournamentSelection(TOURNAMENT_ARITY));

            // initial population
            Population initial = getInitialPopulation(terminalSetSupplier, functionSetSupplier);

            // stopping condition
            StoppingCondition stoppingCondition = new FixedGenerationCount(100);

            System.out.println("Starting evolution ...");

            // run the algorithm
            Population finalPopulation = ga.evolve(initial, stoppingCondition);

            // Get the end time for the simulation.
            long endTime = System.currentTimeMillis();

            // best chromosome from the final population
            Chromosome best = finalPopulation.getFittestChromosome();
            System.out.println("Generation " + ga.getGenerationsEvolved() + ": " + best.toString());
            System.out.println("Total execution time: " + (endTime - startTime) + "ms");

    }

    private static Population getInitialPopulation(TerminalSetSupplier terminalSetSupplier, FunctionSetSupplier functionSetSupplier) {
        List<Chromosome> popList = new LinkedList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            popList.add(FunctionChromosome.getFunctionChromosome(TREE_DEPTH, functionSetSupplier, terminalSetSupplier));
        }
        return new ElitisticListPopulation(popList, 2 * popList.size(), ELITISM_RATE);
    }



    private static class RandomNodeMutation implements MutationPolicy {
        private Random random = new Random();
        private TerminalSetSupplier terminalSetSupplier;
        private FunctionSetSupplier functionSetSupplier;

        private RandomNodeMutation(TerminalSetSupplier terminalSetSupplier, FunctionSetSupplier functionSetSupplier) {
            this.terminalSetSupplier = terminalSetSupplier;
            this.functionSetSupplier = functionSetSupplier;
        }

        public Chromosome mutate(Chromosome original) {
            if (!(original instanceof FunctionChromosome)) {
                throw new IllegalArgumentException("valid only for tree-based chromosomes");
            }

            FunctionNode parentNode = ((FunctionChromosome) original).getRandomFunctionNode();
            Node[] someNodes = parentNode.getChildren();
            int index = random.nextInt(someNodes.length);
            Node toChange = someNodes[index];

            if (toChange instanceof Leaf) {

            }
            return
        }
    }

    private static class ExchangeNodesCrossover implements CrossoverPolicy{

        @Override
        public ChromosomePair crossover(Chromosome chromosomeA, Chromosome chromosomeB) throws MathIllegalArgumentException {
            if (!(chromosomeA instanceof FunctionChromosome && chromosomeB instanceof FunctionChromosome)) {
                throw new IllegalArgumentException("valid only for tree-based chromosomes");
            }
            FunctionChromosome a = (FunctionChromosome) chromosomeA, b = (FunctionChromosome) chromosomeB;
            for (int i = 0; i < 50; i++) {
                FunctionNode aNode = a.getRandomFunctionNode(), bNode = b.getRandomFunctionNode();
                if(aNode.getArity() == bNode.getArity()) {
                    aNode.swapChildren(bNode);
                    break;
                }
            }
            return new ChromosomePair(a,b);
        }
    }
}
