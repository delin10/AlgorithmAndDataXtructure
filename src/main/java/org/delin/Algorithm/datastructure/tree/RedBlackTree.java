package org.delin.Algorithm.datastructure.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 实现红黑树
 * 
 * @author delin
 * @begin-time 2019-03-05 15:25
 * @param <K>
 * @param <V>
 */
public class RedBlackTree<K, V> {
	private RBNode<K, V> root = null;
	private Comparator<K> cmp = null;

	public RedBlackTree(Comparator<K> cmpp) {
		this.cmp = cmpp;
	}

	public Node<K, V> insert(K k, V v) {
		if (root == null) {
			// 根节点必须为黑色
			root = new RBNode<>(k, v, false);
			return root;
		}

		RBNode<K, V> cur = root, parent = null;
		while (cur != null) {
			parent = cur;
			int cmpp = cmp.compare(cur.getKey(), k);
			if (cmpp > 0) {
				cur = cur.getLeft();
			} else if (cmpp < 0) {
				cur = cur.getRight();
			} else {
				cur.setValue(v);
				return cur;
			}
		}
		RBNode<K, V> newNode = new RBNode<K, V>(k, v);
		insert(parent, newNode);
		return newNode;
	}

	public Node<K, V> remove(K k) {
		RBNode<K, V> cur = find(k);

		if (cur == null) {
			return null;
		}

		unlink(cur);
		return cur;

	}

	/**
	 * 寻找键值为key的父节点
	 * 
	 * @param key
	 * @return
	 */
	public RBNode<K, V> find(K key) {
		RBNode<K, V> cur = root;
		while (cur != null) {
			int cmpp = cmp.compare(cur.getKey(), key);
			if (cmpp == 0) {
				break;
			}
			if (cmpp > 0) {
				cur = cur.getLeft();
			} else if (cmpp < 0) {
				cur = cur.getRight();
			}
		}

		return cur;
	}

	/**
	 * <p>
	 * np np / / / / n p / \ / \ / \ / \ p ----> n / \ / / \ / pr pr
	 * </p>
	 * 
	 * @param n
	 * @param p
	 */
	public void rotateR(RBNode<K, V> n, RBNode<K, V> p) {
		if (n == null || p == null) {
			throw new NullPointerException();
		}

		RBNode<K, V> np = n.getParent(), pr = p.getRight();
		if (np != null) {
			if (np.getLeft() == n) {
				np.setLeft(p);
			} else {
				np.setRight(p);
			}
		} else {
			root = p;
		}
		p.setParent(np);
		p.setRight(n);
		n.setParent(p);
		n.setLeft(pr);
		if (pr != null) {
			pr.setParent(n);
		}
	}

	/**
	 * np np / / / / n p / \ / \ / \ / \ p ----> n / \ / \ / \ / \ pl pl
	 * 
	 * @param n
	 * @param p
	 */
	public void rotateL(RBNode<K, V> n, RBNode<K, V> p) {
		if (n == null || p == null) {
			throw new NullPointerException();
		}

		RBNode<K, V> np = n.getParent(), pl = p.getLeft();
		;
		if (np != null) {
			if (np.getLeft() == n) {
				np.setLeft(p);
			} else {
				np.setRight(p);
			}
		} else {
			root = p;
		}
		p.setParent(np);
		p.setLeft(n);
		n.setParent(p);
		n.setRight(pl);
		if (pl != null) {
			pl.setParent(n);
		}
		print(p);
	}

	public RBNode<K, V> getRoot() {
		return root;
	}

	public void inOrderTraverse(RBNode<K, V> root) {
		if (root == null) {
			return;
		}

		inOrderTraverse(root.getLeft());
		System.out.print(root.getKey() + "  ");
		inOrderTraverse(root.getRight());
	}

	private void insert(RBNode<K, V> parent, RBNode<K, V> newNode) {
		RBNode<K, V> grandParent = parent.getParent();
		boolean pColor = parent.getColor();
		int cmpp = cmp.compare(parent.getKey(), newNode.getKey());
		if (!pColor) {
			if (cmpp > 0) {
				parent.setLeft(newNode);
				newNode.setParent(parent);
			} else {
				parent.setRight(newNode);
				newNode.setParent(parent);
			}
		} else {
			grandParent.flip();
			if (grandParent.getLeft() == parent) {
				if (cmpp > 0) {
					parent.setLeft(newNode);
					newNode.setParent(parent);
					parent.flip();
					rotateR(grandParent, parent);
				} else {
					parent.setRight(newNode);
					newNode.setParent(parent);
					newNode.flip();
					print(parent);
					rotateL(parent, newNode);
					rotateR(grandParent, newNode);
				}
			} else {
				if (cmpp < 0) {
					parent.setRight(newNode);
					newNode.setParent(parent);
					parent.flip();
					rotateL(grandParent, parent);
				} else {
					parent.setLeft(newNode);
					newNode.setParent(parent);
					newNode.flip();
					rotateR(parent, newNode);
					rotateL(grandParent, newNode);
				}
			}
		}
	}

	/**
	 * 取消k键值的结点关联
	 * 
	 * @param parent
	 * @param k
	 * @return
	 */
	private Node<K, V> unlink(RBNode<K, V> n) {
		System.out.println("unlink "+n);
		if (n == null) {
			return null;
		}
		RBNode<K, V> parent = n.getParent();
		boolean originC = n.getColor();
		RBNode<K, V> newChildP = null, newChild = null, fixNode = null;
		if (n.getLeft() == null) {
			fixNode = newChild = n.getRight();
			reLink(parent, n, newChild);
		} else if (n.getRight() == null) {
			fixNode = newChild = n.getLeft();
			reLink(parent, n, newChild);
		} else {
			System.out.println("第三情况");
			newChildP = findMinimunParent(n);
			newChild = newChildP.getLeft();
			originC = newChild.getColor();
			RBNode<K, V> childRight = fixNode = newChild.getRight();
			reLink(parent, n, newChild);
			reLink(newChildP, newChild, childRight);
			if (newChildP==n) {
				newChild.setRight(childRight);
			}
			newChild.setColor(n.getColor());
		}
		if (newChild != null)
			print(newChild);
		

		if (!originC) {
			fixup(fixNode);
		}
		// print(newChild);
		return newChild;
	}

	/**
	 * 寻找以root为根的树的最小结点的父节点
	 * 
	 * @param root
	 * @return
	 */
	private RBNode<K, V> findMinimunParent(RBNode<K, V> root) {
		if (root == null) {
			return null;
		}
		RBNode<K, V> cur = root, parent;
		do {
			parent = cur;
			cur = cur.getLeft();
			// 当root本身为最小结点
			if (cur == null) {
				return null;
			}
		} while (cur.getLeft() != null);

		return parent;
	}

	private void reLink(RBNode<K, V> parent, RBNode<K, V> oldChild, RBNode<K, V> newChild) {
		if (parent == null) {
			this.root = newChild;
		} else if (parent.getLeft() == oldChild) {
			parent.setLeft(newChild);
		} else {
			parent.setRight(newChild);
		}
		if (newChild != null) {
			newChild.setParent(parent);
		}
	}

	private void fixup(RBNode<K, V> fixNode) {
		RBNode<K, V> x = fixNode;
		if (x==null) {
			return;
		}
		while (x != root && x.isBlack()) {
			// x的黑色兄弟结点
			// x's black sibling node
			RBNode<K, V> w = null, xParent = x.getParent();
			if (x == xParent.getLeft()) {
				w = xParent.getRight();
				// 如果w是红色，说明w的两个子结点为黑色，x可以通过左旋xParent来获得一个黑色的兄弟结点
				if (w.isRed()) {
					w.setBlack();
					xParent.setRed();
					rotateL(xParent, w);
					w = xParent.getRight();
				}
				// 为什么不用检查null
				/*                  
				 * 
				 *     					 w'
				 *     				   /   \
				 *                    xp    wr
				 *                  /    \
				 *                 x      w
				 *   		     /   \  /   \
				 *                    
				 *   在原来的树中,xp的左子树存在的黑结点个数假设为n
				 *   则w左旋过来,w为黑色,说明w的子树中每一个分叉含有一个黑结点
				 */
				if (w.getLeft().isBlack() && w.getRight().isBlack()) {
					w.setRed();
					// 这里xParent为红色 会跳出循环
					x = xParent;
					continue;
				} else if (w.getRight().isBlack()) {
					// 通过右旋减少是w的左右结点为黑结点
					w.getLeft().setBlack();
					w.setRed();
					rotateR(w, w.getLeft());
					w = xParent.getRight();
				}
				w.setRed();
				xParent.setBlack();
				w.getRight().setBlack();
				rotateL(xParent, w);
				x = root;
			} else {
				w = xParent.getLeft();
				// 如果w是红色，说明w的两个子结点为黑色，x可以通过左旋xParent来获得一个黑色的兄弟结点
				if (w.isRed()) {
					w.setBlack();
					xParent.setRed();
					rotateR(xParent, w);
					w = xParent.getLeft();
				}
				// 为什么不用检查null
				/*                  
				 * 
				 *     					 w'
				 *     				   /   \
				 *                    xp    wr
				 *                  /    \
				 *                 x      w
				 *   		     /   \  /   \
				 *                    
				 *   在原来的树中,xp的左子树存在的黑结点个数假设为n
				 *   则w左旋过来,w为黑色,说明w的子树中每一个分叉含有一个黑结点
				 */
				if (w.getLeft().isBlack() && w.getRight().isBlack()) {
					w.setRed();
					// 这里xParent为红色 会跳出循环
					x = xParent;
					continue;
				} else if (w.getRight().isBlack()) {
					// 通过右旋减少是w的左右结点为黑结点
					w.getLeft().setBlack();
					w.setRed();
					rotateR(w, w.getLeft());
					w = xParent.getRight();
				}
				w.setRed();
				xParent.setBlack();
				w.getRight().setBlack();
				rotateL(xParent, w);
				x = root;
			}
		}
		x.setBlack();
	}

	private void print(RBNode<K, V> node) {
		System.out.println("当前结点" + node);
		RBNode<K, V> p = node.getParent();
		System.out.println("父结点" + p);
		System.out.println("祖父结点" + (p == null ? "null" : node.getParent().getParent()));
		System.out.println("子结点:" + node.getLeft() + "==" + node.getRight());

	}

	public void levelTranverse(RBNode<K, V> root) {
		LinkedList<RBNode<K, V>> ls0 = new LinkedList<>();
		LinkedList<RBNode<K, V>> ls1 = new LinkedList<>();
		ls0.push(root);
		ArrayList<LinkedList<RBNode<K, V>>> all = new ArrayList<>();
		while (!ls0.isEmpty()) {
			all.add(new LinkedList<>(ls0));
			while (!ls0.isEmpty()) {
				RBNode<K, V> n = ls0.removeFirst();
				if (n == null) {
					continue;
				}
				ls1.addLast(n.getLeft());
				ls1.addLast(n.getRight());
			}
			ls0 = ls1;
			ls1 = new LinkedList<>();
		}

		int size = all.stream().max((la, lb) -> la.size() - lb.size()).orElse(new LinkedList<>()).size();

		all.stream().forEach(l -> {
			l.stream().forEach(n -> {
				System.out.print(n == null ? n : n.getKey() + "[" + (n.getColor() ? "r" : "b") + "]");
				System.out.print("---");
			});
			System.out.println();
		});
	}
}
