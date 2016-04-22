package function.supplier;

import function.interfaces.FunctionNode;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Created by tirnak on 22.04.2016.
 */
public class FunctionSetSupplier implements Supplier {
    private final Random random = new Random();
    private FunctionNode[] nodes;

    public FunctionSetSupplier(FunctionNode[] nodes) {
        this.nodes = nodes;
    }

    @Override
    public FunctionNode get() {
        return nodes[random.nextInt(nodes.length)].getClone();
    }
}
