package function.functions;

import java.util.function.BiFunction;

/**
 * Created by tirnak on 24.04.16.
 */
public class Mult implements BiFunction {
    @Override
    public String toString() {
        return "*";
    }

    @Override
    public Object apply(Object o, Object o2) {
        if (!(o instanceof Double && o2 instanceof Double)) {
            throw new IllegalArgumentException("sum double");
        }
        return ((Double) o) * ((Double) o2);
    }
}
