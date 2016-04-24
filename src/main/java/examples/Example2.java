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
public class Example2 {

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
         *      2 * x * x - 4 * x + 30
         */
        double values[][] = {
                {-300, 181230},
                {-60, 6990},
                {-10, 270},
                {-5, 100},
                {-1, 36},
                {1, 28},
                {5, 60},
                {67, 8740},
                {300, 178830},
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
