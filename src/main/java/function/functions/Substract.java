package function.functions;

import java.util.function.BiFunction;

/**
 * Created by kirill on 24.04.16.
 */
public class Substract implements BiFunction{
    @Override
    public Object apply(Object o, Object o2) {
        if (!(o instanceof Double && o2 instanceof Double)) {
            throw new IllegalArgumentException("sum double");
        }
        return ((Double) o) - ((Double) o2);
    }

    @Override
    public String toString() {
        return "-";
    }
}
