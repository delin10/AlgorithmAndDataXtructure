package org.delin.Algorithm;

public class SingleLinkedListCircle {
	static class Node {
		int v;
		Node next;

		public Node(int vv) {
			this.v = vv;
		}
	}
	//判断成环
	public static boolean isCycle(Node head) {
		if (head == null) {
			return false;
		}
		Node cur = head, next = head;
		while (true) {
			cur = cur.next;
			if (next != null && next.next != null && next.next.next != null) {
				next = next.next.next;
			} else {
				return false;
			}
			if (cur == next) {
				return true;
			}
		}
	}
	//获得环入口
	public static Node getMeetNode(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head, next = head;
		while (true) {
			cur = cur.next;
			if (next != null && next.next != null && next.next.next != null) {
				next = next.next.next;
			} else {
				return null;
			}
			if (cur == next) {
				break;
			}
		}
		cur = head;
		while (cur != next) {
			cur = cur.next;
			next = next.next;
		}

		return cur;
	}
	//获得环长度
	public static int getCircleLen(Node head) {
		if (head == null) {
			return -1;
		}
		Node cur = head, next = head;
		while (true) {
			cur = cur.next;
			if (next != null && next.next != null && next.next.next != null) {
				next = next.next.next;
			} else {
				return -1;
			}
			if (cur == next) {
				break;
			}
		}

		int len = 0;
		while (true) {
			cur = cur.next;
			next = next.next.next;
			len++;
			if (cur == next) {
				return len;
			}
		}
	}
	//获得链表长度
	public static int getListLen(Node head) {
		if (head == null) {
			return -1;
		}
		Node meet=getMeetNode(head),cur = meet, next = meet;
		//注意meet
		int len = 0;
		Node n = head;
		while (n != meet) {
			n = n.next;
			len++;
		}
		while (true) {
			cur = cur.next;
			len++;
			if (cur == meet) {
				return len;
			}
		}

	}

	public static void main(String[] args) {
		Node head = new Node(-1);
		head.next = head;
		Node next0 = new Node(0);
		head.next = next0;
		Node next1 = new Node(1);
		next0.next = next1;
		Node next2 = new Node(2);
		next1.next = next2;
		Node next3 = new Node(3);
		next2.next = next3;
		Node next4 = new Node(4);
		next3.next = next4;
		Node next5 = new Node(5);
		next4.next = next5;
		Node next6 = new Node(6);
		next5.next = next6;
		Node next7 = new Node(7);
		next6.next = next7;
		Node next8 = new Node(8);
		next7.next = next8;
		next8.next = next0;
		System.out.println(getListLen(head));

	}
}
