package org.delin.Algorithm;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.Scanner;

import com.sun.javafx.sg.prism.NGShape.Mode;

/**
 * n个人围坐在圆桌中 
 * 从编号k的人开始报数 数到m的那个人出列 
 * 下一个人继续报数 数到m的人继续出列 重
 * 复上述过程 直到全部人出列 
 * 计算最后一个出列的人
 * @author delin
 *
 */
public class Joseph {
	public static class Node {
		int val;
		Node next;

		public Node(int val) {
			this.val = val;
		}
	}

	public static void delNode(Node prev, Node n) {
		prev.next = n.next;
	}

	// * 采用循环链表解决
	public static void joseph(int n, int m) {
		if (n <= 0) {
			return;
		}
		Node head = new Node(1), next = head;
		for (int i = 2; i <= n; ++i) {
			next = next.next = new Node(i);
		}

		next.next = head;
		int countM = 1;
		Node node = head, parent = next;
		while (node.next != node) {
			if (countM++ % m == 0) {
				System.out.print(node.val + " ");
				delNode(parent, node);
			}
			parent = node;
			node = node.next;
		}

		System.out.print(node.val);
	}

	// 采用循环数组解决
	public static void josephArr(int n, int m) {
		if (n <= 0) {
			return;
		}
		int[] circle = new int[n];
		for (int i = 0; i < n; ++i) {
			circle[i] = i + 1;
			if (i == n - 1) {
				circle[i] = 0;
			}
		}

		int countN = 0, countM = 1, rest = n, prev = n - 1;
		while (rest > 0) {
			if (countM++ % m == 0) {
				rest--;
				System.out.print((countN + 1) + " ");
				circle[prev] = circle[countN];
			} else {
				prev = countN;
			}
			countN = circle[countN];
		}
		System.out.println();
	}

	public static int mod(int n, int v) {
		int x = v < 0 ? -v : v;
		return x % n;
	}

	public static int joseph2(int k) {
		int n = 2 * k;
		outer: for (int i = 1;; ++i) {
			int m = k + i;
			for (int j = 0, counter = m - 1, rest = n; j < k; ++j, --rest) {

				counter = mod(rest, counter);
				if (counter <= k - 1) {
					continue outer;
				}
				if (counter + m < rest) {
					counter += m - 1;
				} else {
					int gap = m - 1 - (rest - counter - 1);
					counter = 0;
					counter += gap;
				}
			}
			return m;
		}
	}

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		ArrayList<Integer> ins=new ArrayList<Integer>();
		int t;
		while ((t=in.nextInt())!=0) {
			ins.add(t);
		}
		for (int i=0;i<ins.size();++i) {
			System.out.println(joseph2(ins.get(i)));
		}
		
	}
}
