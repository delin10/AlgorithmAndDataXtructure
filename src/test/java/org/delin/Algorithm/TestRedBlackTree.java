package org.delin.Algorithm;

import java.util.Random;

import org.delin.Algorithm.datastructure.tree.RedBlackTree;
import org.junit.Test;

public class TestRedBlackTree {
	@Test
	public void testInsert() {
		RedBlackTree<Integer, Void> tree = new RedBlackTree<>((a, b) -> a - b);
		Random random = new Random();
		tree.insert(97, null);
		tree.insert(30, null);

		tree.inOrderTraverse(tree.getRoot());
		tree.insert(33, null);

		tree.inOrderTraverse(tree.getRoot());
		System.out.println();
		tree.levelTranverse(tree.getRoot());

		tree.inOrderTraverse(tree.getRoot());
		for (int i = 0; i < 50; ++i) {
			int key = random.nextInt(100);
			tree.insert(key, null);
		}
		tree.levelTranverse(tree.getRoot());
		tree.remove(33);
		tree.levelTranverse(tree.getRoot());
		tree.inOrderTraverse(tree.getRoot());
		for (int i = 0; i < 50; ++i) {
			int key = random.nextInt(100);
			System.out.println();
			System.out.println("删除----key=" + key);
			//Node<Integer, Void> node = tree.remove(key);
			tree.levelTranverse(tree.getRoot());
		}

		tree.inOrderTraverse(tree.getRoot());
	}
}
