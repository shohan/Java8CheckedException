import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author shohan
 * @since 11/6/18
 */
public class FunctionExceptionTest {

    public static void main(String[] args) {
        FunctionExceptionTest exceptionTest = new FunctionExceptionTest();
        System.out.println(exceptionTest.numberExceptionWrapper(0, 5));
    }

    public List<Integer> numberExceptionWrapper(int divisor, Integer... numbbers) {
        return Arrays.stream(numbbers)
                .map(wrapper(s -> s / divisor, this::divideByZeroException))
                .collect(Collectors.toList());
    }

    public static <T, R, E extends Supplier<RuntimeException>> Function<T, R> wrapper(Function<T, R> fe, E e) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e1) {
                throw e.get();
            }
        };
    }

    private RuntimeException divideByZeroException() {
        return  new ArithmeticException("Divide by zero ");
    }
}
