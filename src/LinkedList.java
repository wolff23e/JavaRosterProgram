/**
 * A linked list implementation
 *
 * @author ponbarry/ edited by @author Emma Wolff
 * @version 1 March 2020
 */

public class LinkedList<T> {

	public LinkedListNode<T> head;

	public LinkedList() {}
	//public void LinkedList(String add_student) {
	//}
	public LinkedList(T d){
		head = new LinkedListNode<T>(d);
	}

	/**
	 * Identifies where student name is in Linked List
	 * Made for implementation of (r)emove
	 * @param obj is the students name
	 */
	public int indexOf(Object obj) {
    int index = 0;
    LinkedListNode<T> current = head;
    while (current != null) {
        if (current.equals(obj)) {
            return index;
        }
        index++;
        current = current.getNext();
    }
    return -1;
}


	/**
	 * Creates a new node, adds data to it, and adds the
	 * node to the end of the list.
	 *
	 * @param d the data
	 */
	public void add(T d){
		LinkedListNode<T> newNode = new LinkedListNode<T>(d);
		if (head == null) {
			head = newNode;
		} else {
			LinkedListNode<T> temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(newNode);
		}
	}

	/**
	 * Creates a new node, adds data to it, and adds the
	 * node to the list at the specified index.
	 *
	 * @param index the desired index for the data in resulting list
	 * @param d the data
	 */
	public void add(int index, T d){
		// throw an exception for arguments that are out of range
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();

		LinkedListNode<T> newNode = new LinkedListNode<T>(d);

		if (index == 0) {
			newNode.setNext(head);
			head = newNode;
		} else {
			LinkedListNode<T> temp = head;
			int currIndex = 0;
			while (currIndex < index - 1) {
				temp = temp.getNext();
				currIndex++;
			}
			newNode.setNext(temp.getNext());
			temp.setNext(newNode);
		}
	}

	public T get(int index){
		// throw an exception for arguments that are out of range
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();

		LinkedListNode<T> temp = head;
		int count = 0;
		while (count < index) {
			temp = temp.getNext();
			count++;
		}
		return temp.getData();
	}

	public T delete(int index){
		// throw an exception for arguments that are out of range
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();

		T deleted;
		if (index == 0) {
			deleted = head.getData();
			head = head.getNext();
		} else {
			LinkedListNode<T> temp = head;
			int currIndex = 0;
			while (currIndex < index - 1) {
				temp = temp.getNext();
				currIndex++;
			}
			deleted = temp.getNext().getData();
			temp.setNext(temp.getNext().getNext());
		}
		return deleted;
	}

	public int size() {
		int n = 0;
		LinkedListNode<T> temp = head;
		while (temp != null){
			n++;
			temp = temp.getNext();
		}
		return n;
	}

	public boolean isEmpty(){
		return (head == null);
	}

	public String toString(){
		String str = "";
		if (head != null) {
			LinkedListNode<T> temp = head;
			while (temp.getNext() != null){
				str = str + temp.toString() + " -> ";
				temp = temp.getNext();
			}
			str = str + temp.toString();
		}
		return str;
	}
}