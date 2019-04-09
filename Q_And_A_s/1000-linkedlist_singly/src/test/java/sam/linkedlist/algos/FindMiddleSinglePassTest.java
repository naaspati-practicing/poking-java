package sam.linkedlist.algos;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sam.linkedlist.algos.Find_Middle_SinglePass.middle;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.Test;

import sam.collections.SinglyLinkedList;

class FindMiddleSinglePassTest {

	@Test
	void test() {
		assertThrows(NoSuchElementException.class, () -> middle(new SinglyLinkedList<>()));
		
		Random r = new Random();
		ArrayList<Integer> list = new ArrayList<>(10010);
		SinglyLinkedList<Integer> linkedlist = new SinglyLinkedList<>();
		
		int odd = 0, even = 0;
		
		for (int i = 0; i < 10000; i++) {
			int size = 10 + r.nextInt(200);
			list.clear();
			linkedlist.clear();
			
			for (int j = 0; j < size; j++) {
				Integer n = r.nextInt();
				list.add(n);
				linkedlist.add(n);
			}
			
			if(size%2 == 0){
				even++;
				System.out.println("size: "+size+", throw_error");
				assertThrows(NoSuchElementException.class, () -> middle(linkedlist));
			} else {
				odd++;
				Integer middle = middle(linkedlist);
				System.out.println("size: "+size+", middle: "+middle);
				assertSame(middle, list.get(list.size()/2));
			}
		}
		
		System.out.println("\n\neven: "+even+", odd: "+odd);
	}
}
