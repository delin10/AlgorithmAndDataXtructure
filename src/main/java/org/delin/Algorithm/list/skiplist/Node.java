package org.delin.Algorithm.list.skiplist;

public class Node<T extends Comparable<T>> {
	private Node<T> down;
	private Node<T> right;
	private Node<T> prev;
	private T value;

	public Node() {
		// TODO Auto-generated constructor stub
	}

	public Node(T value) {
		// TODO Auto-generated constructor stub
		setValue(value);
	}

	public Node<T> getDown() {
		return down;
	}

	public void setDown(Node<T> down) {
		this.down = down;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return String.format("{prev:%s,down:%s,right:%s,value:%s]", prev==null?"prev==null":prev.getValue(),down==null?"down=null":down.getValue(),right==null?"right==null":right.getValue(),value);
	}

}
