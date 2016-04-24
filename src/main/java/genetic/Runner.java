package genetic;

import function.interfaces.FunctionNode;
import function.interfaces.Node;
import function.simplifier.Simplifier;
import function.supplier.FunctionSetSupplier;
import function.supplier.TerminalSetSupplier;
import org.apache.commons.math4.genetics.*;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by tirnak on 21.04.2016.
 */
public class Runner {

    public static final int    POPULATION_SIZE   = 500;
    public static final double CROSSOVER_RATE    = 0.9;
    public static final double MUTATION_RATE     = 0.03;
    public static final double ELITISM_RATE      = 0.1;
    public static final int    TOURNAMENT_ARITY  = 2;
    public static final int    TREE_DEPTH = 4;


    public static FunctionChromosome run(TerminalSetSupplier terminalSetSupplier, FunctionSetSupplier functionSetSupplier,
                                            List<Map<Character, Double>> data, PrintStream out) {

        long startTime = System.currentTimeMillis();

        // initialize a new genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(
                new ExchangeNodesCrossover(), CROSSOVER_RATE,
                new RandomNodeMutation(terminalSetSupplier, functionSetSupplier), MUTATION_RATE,
                new TournamentSelection(TOURNAMENT_ARITY));

        // initial population
        Population initial = getInitialPopulation(terminalSetSupplier, functionSetSupplier, data);

        // stopping condition
        StoppingCondition stoppingCondition = new FixedGenerationCount(100);

        out.println("Starting evolution ...");

        // run the algorithm
        Population finalPopulation = ga.evolve(initial, stoppingCondition);

        // Get the end time for the simulation.
        long endTime = System.currentTimeMillis();

        // best chromosome from the final population
        FunctionChromosome best = (FunctionChromosome) finalPopulation.getFittestChromosome();
        FunctionChromosome copy = best.deepCopyExceptData();
        List<FunctionNode> allNodes = copy.getAllFunctionNodes();
        Simplifier.simplify(allNodes);
        out.println("Simplified " + copy);
        out.println("Total execution time: " + (endTime - startTime) + "ms");
        return best;
    }

    private static Population getInitialPopulation(TerminalSetSupplier terminalSetSupplier, FunctionSetSupplier functionSetSupplier, List<Map<Character, Double>> data) {
        List<Chromosome> popList = new LinkedList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            popList.add(FunctionChromosome.getFunctionChromosome(TREE_DEPTH, functionSetSupplier, terminalSetSupplier, data));
        }
        return new ElitisticListPopulation(popList, 2 * popList.size(), ELITISM_RATE);
    }


}
