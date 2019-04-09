package sam.collections;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import sam.collections.SinglyLinkedList;

class SinglyLinkedListTest {

	@Test
	void test() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		IntStream.range(1, 100).forEach(list::add);
		
		assertIterableEquals(list, new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return IntStream.range(1, 100).iterator();
			}
		});
		
	}

}
