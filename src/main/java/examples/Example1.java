package examples;

import function.BiFunctionNode;
import function.UniFunctionNode;
import function.functions.*;
import function.interfaces.FunctionNode;
import function.supplier.FunctionSetSupplier;
import function.supplier.TerminalSetSupplier;
import genetic.Runner;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kirill on 24.04.16.
 */
public class Example1 {

    public static final char[] varNames = {'x'};

    public static void main(String[] args) {
        run(System.out);
    }

    public static void run(PrintStream out) {

        TerminalSetSupplier terminalSetSupplier = new TerminalSetSupplier(varNames, 0, 20, .25);
        FunctionSetSupplier functionSetSupplier = new FunctionSetSupplier(new FunctionNode[]{
                new BiFunctionNode(new Sum()),
                new BiFunctionNode(new Mult()),
                new BiFunctionNode(new Substract()),
                new UniFunctionNode(new Negate()),
                new UniFunctionNode(new Id())
        });

        /**
         *      5 * x + 20
         */
        double values[][] = {
                {(-3), 5},
                {(-1), 15},
                {1, 25},
                {2, 30},
                {5, 45},
                {67, 355},
                {300, 1520},
        };
        List<Map<Character, Double>> data = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Map<Character, Double> tmp = new HashMap<>();
            tmp.put(varNames[0], values[i][0]);
            tmp.put('\0', values[i][1]);
            data.add(tmp);
        }
        Runner.run(terminalSetSupplier, functionSetSupplier, data, out);
    }

}
