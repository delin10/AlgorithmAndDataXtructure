package org.delin.Algorithm.datastructure.tree;

import java.util.Objects;

public class Node<K, V> {
	private K key;
	private V value;
	private Node<K, V> right, left;

	public Node(K k, V v) {
		this.key = k;
		this.value = v;
	}
	
	public Node(K key, V value, Node<K, V> right, Node<K, V> left) {
		super();
		this.key = key;
		this.value = value;
		this.right = right;
		this.left = left;
	}



	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> right) {
		this.right = right;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> left) {
		this.left = left;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		Node<K, V> obj0 = (Node<K, V>) obj;
		return Objects.equals(key, obj0.key) && Objects.equals(value, obj0.value);
	}
	
	@Override
	public String toString() {
		return String.format("[key=%s,value=%s]", key,value);
	}
}
