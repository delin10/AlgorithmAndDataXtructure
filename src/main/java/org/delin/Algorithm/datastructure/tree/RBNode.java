package org.delin.Algorithm.datastructure.tree;

/**
 * 红黑树的结点
 * 
 * @author delin
 * @begin-time 2019-03-05 15:25
 * @param <K>
 * @param <V>
 */
public class RBNode<K, V> extends Node<K, V> {
	// true is red, or black
	private boolean color = true;
	private RBNode<K, V> parent = null;

	public RBNode(K k, V v) {
		super(k, v);
	}

	public RBNode(K k, V v, boolean color) {
		super(k, v);
		this.color = color;
	}

	public RBNode(K key, V value, RBNode<K, V> right, RBNode<K, V> left) {
		super(key, value, right, left);
	}

	public RBNode(K key, V value, RBNode<K, V> right, RBNode<K, V> left, boolean color) {
		super(key, value, right, left);
		this.color = color;
	}

	public void setRed() {
		color = true;
	}

	public void setBlack() {
		color = false;
	}

	public boolean getColor() {
		return color;
	}

	public boolean flip() {
		return color = !color;
	}

	public RBNode<K, V> getParent() {
		return parent;
	}

	public void setParent(RBNode<K, V> parent) {
		this.parent = parent;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public boolean isRed() {
		return color;
	}

	public boolean isBlack() {
		return !color;
	}

	@Override
	public RBNode<K, V> getLeft() {
		// TODO Auto-generated method stub
		return (RBNode<K, V>) super.getLeft();
	}

	@Override
	public RBNode<K, V> getRight() {
		// TODO Auto-generated method stub
		return (RBNode<K, V>) super.getRight();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "color>" + color + "<";
	}
}
