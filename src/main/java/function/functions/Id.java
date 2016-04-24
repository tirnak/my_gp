package function.functions;

import java.util.function.Function;

/**
 * Created by tirnak on 24.04.16.
 */
public class Id implements Function {
    @Override
    public Object apply(Object o) {
        return o;
    }

    @Override
    public String toString() {
        return "Id";
    }
}
