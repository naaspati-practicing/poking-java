package sam.linkedlist.algos;
import java.util.NoSuchElementException;

import sam.collections.SinglyLinkedList;
import sam.collections.SinglyLinkedList.Node;

/**
* <a href="https://javarevisited.blogspot.com/2012/12/how-to-find-middle-element-of-linked-list-one-pass.html">https://javarevisited.blogspot.com/2012/12/how-to-find-middle-element-of-linked-list-one-pass.html</a>
*
* <pre>
* How to Find Middle Element of Linked List in Java in Single PassHow to Find Middle Element of Linked List in Java in Single Pass
* </pre>
*/

public class Find_Middle_SinglePass {
	public static <E> E middle(SinglyLinkedList<E> list) {
		if(list.isEmpty())
			throw new NoSuchElementException("empty list");
		
		Node<E> current = list.head();
		Node<E>  middle = current;
		int len = 1;
		int middle_index = 0;
		
		while(current.next() != null) {
			len++;
			
			if(len/2 > middle_index) {
				middle = middle.next();
				middle_index++;
			}
			current = current.next();
		}
		
		if(len%2 == 0)
			throw new NoSuchElementException("no middle found: list.size is a even number.");
		
		return middle.value();
	}
}
