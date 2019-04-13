package org.delin.Algorithm;

import java.util.Random;

import org.delin.Algorithm.datastructure.tree.SplayTree;
import org.junit.Test;

public class TestSplayTree {

	@Test
	public void testOrder() {
		SplayTree<Integer, Integer> tree = new SplayTree<>((a, b) -> a - b);
		Random random = new Random();
		for (int i = 0; i < 50; ++i) {
			tree.insert(random.nextInt(100), random.nextInt(100));
		}

		tree.preorder(tree.getRoot());
		System.out.println();
		//删除无法删除 未解决
		//OK 2019-03-06 14:47
		tree.remove(tree.getRoot().getLeft().getRight().getKey());
		System.out.println();
		tree.preorder(tree.getRoot());
		System.out.println();
		//OK 2019-03-06 14:47
		for (int i = 0; i < 50; ++i) {
			System.out.println("find:"+i+",result:"+tree.find(i));
		}
	}

}
