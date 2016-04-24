package function.functions;

import java.util.function.Function;

/**
 * Created by tirnak on 24.04.16.
 */
public class Negate implements Function {
    @Override
    public Object apply(Object o) {
        if (!(o instanceof Double)) {
            throw new IllegalArgumentException("sum double");
        }
        return - (Double) o;
    }

    @Override
    public String toString() {
        return "-";
    }
}
