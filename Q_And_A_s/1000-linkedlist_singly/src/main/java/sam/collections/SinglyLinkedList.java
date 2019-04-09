package sam.collections;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements Iterable<E> {
	private int mod;
	
	private Node<E> head;
	private Node<E> tail;
	private int size = 0;
	
	public SinglyLinkedList() { }
	public SinglyLinkedList(Collection<E> from) {
		addAll(from);
	}
	
	public void clear() {
		head = null;
		tail = null;
	}
	
	public void addAll(Collection<E> from) {
		from.forEach(this::add);
	}
	public void add(E e) {
		if(head == null) {
			head = new Node<>(e);
			tail = head;
		} else {
			tail.next = new Node<>(e);
			tail = tail.next;
		}
		
		size++;
		mod++;
	}
	
	public int size() {
		return size;
	}
	@Override
	public Iterator<E> iterator() {
		if(head == null)
			return Collections.emptyIterator();
		
		int m = mod;
		return new Iterator<E>() {
			private Node<E> current = head;

			@Override
			public boolean hasNext() {
				if(mod != m)
					throw new ConcurrentModificationException();
				
				return current != null;
			}

			@Override
			public E next() {
				if(!hasNext())
					throw new NoSuchElementException();
				E e = current.value;
				current = current.next;
				return e;
			}
		};
	}
	public Node<E> head() {
		return head;
	}
	public boolean isEmpty() {
		return head == null;
	}
	
	public static class Node<F> {
		private final F value;
		private Node<F> next;
		
		public Node(F value) {
			this.value = value;
		}
		
		public F value() {
			return value;
		}
		public Node<F> next() {
			return next;
		}
		
		@Override
		public String toString() {
			return "Node["+value+"]";
		}
	}
}
