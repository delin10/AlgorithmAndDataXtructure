package org.delin.Algorithm.datastructure.tree;

import java.util.Comparator;

/**
 * 平衡树 整个平衡树是以结点的键值进行排序 键值可以重复
 * 
 * @author delin
 * @begin-time 2019-03-05 19:50
 * @finished-time 2019-03-06 14:50 test finished
 * @finished-time 2019-03-06 15:16 test modified 键值不重复
 * @param <K>
 *            键值的泛型参数
 * @param <V>
 *            值的泛型参数
 */
public class SplayTree<K, V> {
	private Node<K, V> root;
	private Comparator<K> cmp;

	public SplayTree(Comparator<K> cmp) {
		this.cmp = cmp;
	}

	public SplayTree(SplayTree<K, V> splayTree) {

	}

	/**
	 * 插入结点
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Node<K, V> insert(K key, V value) {
		Node<K, V> newNode = new Node<K, V>(key, value);
		if (root == null) {
			root = newNode;
			return root;
		}

		Node<K, V> cur = root, parent = null;
		while (cur != null) {
			parent = cur;
			int cmpp = cmp.compare(cur.getKey(), key);
			if (cmpp > 0) {
				cur = cur.getLeft();
			} else if (cmpp < 0) {
				cur = cur.getRight();
			} else {
				cur.setValue(value);
				return cur;
			}
		}

		if (cmp.compare(parent.getKey(), key) > 0) {
			parent.setLeft(newNode);
		} else {
			parent.setRight(newNode);
		}
		return newNode;
	}

	/**
	 * 移除键值为key的结点
	 * 
	 * @param key
	 * @return
	 */
	public Node<K, V> remove(K key) {
		Node<K, V> parent = findParent(key);
		System.out.println("key=" + key + ",parent=" + (parent == null ? "null" : parent.getKey()));
		return unlink(parent, key);
	}

	/**
	 * 寻找键值为key的结点
	 * 
	 * @param key
	 * @return
	 */
	public Node<K, V> find(K key) {
		if (root == null) {
			return null;
		}
		Node<K, V> cur = root;
		while (cur != null) {
			if (cmp.compare(cur.getKey(), key) > 0) {
				cur = cur.getLeft();
			} else if (cmp.compare(cur.getKey(), key) < 0) {
				cur = cur.getRight();
			} else {
				break;
			}
		}
		return cur;
	}

	/**
	 * 寻找键值为key的父节点
	 * 
	 * @param key
	 * @return
	 */
	private Node<K, V> findParent(K key) {
		if (root == null) {
			return null;
		}
		Node<K, V> cur = root, parent = null;
		while (cur != null) {
			int cmpp = cmp.compare(cur.getKey(), key);
			if (cmpp == 0) {
				break;
			}
			parent = cur;
			if (cmpp > 0) {
				cur = cur.getLeft();
			} else if (cmpp < 0) {
				cur = cur.getRight();
			}
		}
		return cur == null ? null : parent;
	}

	/**
	 * 取消k键值的结点关联
	 * 
	 * @param parent
	 * @param k
	 * @return
	 */
	private Node<K, V> unlink(Node<K, V> parent, K k) {
		if (parent != null) {
			Node<K, V> left = parent.getLeft(), right = parent.getRight();
			Node<K, V> newChildP = null, newChild = null;
			if (left != null && cmp.compare(left.getKey(), k) == 0) {
				if (left.getLeft() == null) {
					parent.setLeft(left.getRight());
				} else if (left.getRight() == null) {
					parent.setLeft(left.getLeft());
				} else {
					newChildP = findMinimunParent(left);
					newChild = newChildP.getLeft();
					newChildP.setLeft(null);
					parent.setLeft(newChild);
					newChild.setRight(left.getRight());
					newChild.setLeft(left.getLeft());
				}
				return left;

			}

			if (right != null && cmp.compare(right.getKey(), k) == 0) {
				if (right.getLeft() == null) {
					parent.setRight(right.getRight());
				} else if (right.getRight() == null) {
					parent.setRight(right.getLeft());
				} else {
					newChildP = findMinimunParent(right);
					newChild = newChildP.getLeft();
					newChildP.setLeft(null);
					parent.setRight(newChild);
					newChild.setRight(right.getRight());
					newChild.setLeft(right.getLeft());
				}
				return right;
			}
		}
		return null;
	}

	/**
	 * 寻找以root为根的树的最小结点的父节点
	 * 
	 * @param root
	 * @return
	 */
	private Node<K, V> findMinimunParent(Node<K, V> root) {
		if (root == null) {
			return null;
		}
		Node<K, V> cur = root, parent;
		do {
			parent = cur;
			cur = cur.getLeft();
			// 当root本身为最小结点
			if (cur==null) {
				return null;
			}
		} while (cur.getLeft() != null);

		return parent;
	}

	public Node<K, V> getRoot() {
		return root;
	}

	public void preorder(Node<K, V> root) {
		if (root == null) {
			return;
		}

		preorder(root.getLeft());
		System.out.print(root.getKey() + "  ");
		preorder(root.getRight());
	}
}
