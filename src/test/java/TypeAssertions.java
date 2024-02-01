import static org.junit.jupiter.api.Assertions.assertTrue;

public class TypeAssertions {

    public static <T> void assertInstanceOf(Class<T> expectedType, Object actualObject) {
        assertTrue(expectedType.isInstance(actualObject),
                "Expected type: " + expectedType.getSimpleName() +
                        ", but found type: " + actualObject.getClass().getSimpleName());
    }
}

