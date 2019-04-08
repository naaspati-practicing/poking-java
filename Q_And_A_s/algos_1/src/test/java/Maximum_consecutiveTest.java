import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.params.provider.Arguments.*;

class Maximum_consecutiveTest {

	@ParameterizedTest
	@MethodSource("arrays")
	void test(int find_value, int[] array, int expected_result) {
		Maximum_consecutive m = new Maximum_consecutive(find_value);
		
		int actual = m.find(array);
		assertEquals(expected_result, actual);
	}
	
	public static Stream<Arguments> arrays() {
		return Stream.of(
				arguments(1, new int[]{1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1}, 4),
				arguments(1, new int[]{0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, 1),
				arguments(1, new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1}, 3),
				arguments(1, new int[]{0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, 5)
				);
	}

}
