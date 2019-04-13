package org.delin.Algorithm.list.skiplist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SkipList<T extends Comparable<T>> {
	private int list_Level;
	private ArrayList<Node<T>> heads = new ArrayList<>();

	public SkipList() {
		// TODO Auto-generated constructor stub
		createSkipList();
	}

	public void createSkipList() {
		list_Level = 0;
	}

	private int produceLevel() {
		int level = 1;
		while (Math.random() < 0.5D) {
			level++;
		}
		return level;
	}

	/**
	 * lastTop top=head[level] if head top.getRight==null ||
	 * (top.getRight()!=null&&top.getRight>value) new_Node.setRight(top.getRight)
	 * top.setRight(new_Node) lastTop=new_Node; top=head[level] else if not head
	 * top=top.getRight
	 * 
	 * @Bug1:001 ������ͬԪ��ʱ����
	 * @param value
	 */

	public void insert(T value) {
		// �����������
		int level = produceLevel();
		Node<T> lastTop = null;
		// ����һ�������� ��Ϊ��������ı�ͷ
		if (level > list_Level) {
			level = ++list_Level;
			Node<T> newHead = new Node<>(null);
			heads.add(newHead);
			if (list_Level > 1) {
				newHead.setDown(heads.get(list_Level - 2));
			}
		}
		Node<T> preNode = heads.get(level - 1);
		// find ��the place where this element is inserted
		for (int i = level - 1; i >= 0; --i) {
			while (preNode.getRight() != null && preNode.getRight().getValue() != null
					&& preNode.getRight().getValue().compareTo(value) < 0) {
				preNode = preNode.getRight();
			}
			lastTop = addNewNode(preNode, lastTop, value);
			preNode = preNode.getDown();
		}
	}

	/**
	 * 
	 * @param preNode
	 *            ǰ�����
	 * @param topNode
	 *            ��һ��ڵ�
	 * @param value
	 * @return
	 */
	private Node<T> addNewNode(Node<T> preNode, Node<T> topNode, T value) {
		Node<T> newNode = new Node<>(value), rNode = preNode.getRight();
		newNode.setRight(rNode);
		newNode.setPrev(preNode);
		preNode.setRight(newNode);
		if (rNode != null) {
			rNode.setPrev(newNode);
		}
		if (topNode != null) {
			topNode.setDown(newNode);
		}
		return newNode;
	}

	public Node<T> search(T value) {
		int level = list_Level;
		Node<T> top = heads.get(level - 1);
		while (top != null) {
			while (top.getRight() != null && top.getRight().getValue() != null
					&& top.getRight().getValue().compareTo(value) < 0) {
				top = top.getRight();
			}
			if (top.getRight() != null && top.getRight().getValue().equals(value)) {
				return top.getRight();
			}
			// skip
			top = top.getDown();
		}
		return null;
	}

	public void delete(T value) {
		Node<T> top = search(value);
		while (top != null) {
			Node<T> rNode = top.getRight();
			top.getPrev().setRight(rNode);
			if (rNode != null) {
				rNode.setPrev(top.getPrev());
			}
			top = top.getDown();
		}
		for (int i = list_Level - 1; i >= 0; --i) {
			if (heads.get(i).getRight() == null) {
				heads.remove(i);
				--list_Level;
			}
		}
	}

	public List<Node<T>> findAll(T value) {
		List<Node<T>> ls = new LinkedList<>();
		Node<T> top = search(value);
		if (top != null) {
			while (top.getDown() != null) {
				top = top.getDown();
			}
			Node<T> baseR = top.getRight(), baseL = top.getPrev();
			ls.add(top);
			while (baseR != null && baseR.getValue().equals(value)) {
				ls.add(baseR);
				baseR = baseR.getRight();
			}

			while (baseL != null && baseL.getValue().equals(value)) {
				ls.add(baseL);
				baseL = baseL.getPrev();
			}
		}
		return ls;
	}

	public void print(Node<T> head) {
		Node<T> h = head;

		while (h != null) {
			System.out.print(h.getValue() + "==>");
			h = h.getRight();
		}
		System.out.println("");
	}

	public void printAll() {
		for (int i = 0; i < list_Level; ++i) {
			System.out.println("level" + i);
			print(heads.get(i));
		}
		System.out.println("____________________________________________");
	}

	/**
	 * if the head is a linkedList
	 * 
	 * @param level
	 *            index from 0
	 * @return
	 * @throws InterruptedException
	 *//*
		 * private Node<T> getHeadAt(int level){ Node<T> h_1=head; for (int
		 * i=0;i<level;++i) { h_1=h_1.getDown(); } return h_1; }
		 * 
		 * private Node<T> incHead() { Node<T> h_1=head; while(h_1.getDown()!=null) {
		 * h_1=h_1.getDown(); }
		 * 
		 * Node<T> new_Head=new Node<T>(null); new_Head.setDown(null);
		 * new_Head.setRight(null); h_1.setDown(new_Head); return new_Head; }
		 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		SkipList<Integer> sl = new SkipList<>();
		LinkedList<Integer> ls = new LinkedList<>();
		int testNum = 100;
		long start, end;
		System.out.println("��ʼ����SkipList");
		start = System.currentTimeMillis();
		for (int i = 0; i < testNum; ++i) {
			sl.insert(i);
		}
		end = System.currentTimeMillis();
		System.out.println("__________________" + (end - start) + "ms");
		System.out.println("��ʼ����LinkedList");
		start = System.currentTimeMillis();
		for (int i = 0; i < testNum; ++i) {
			ls.add(i);
		}
		for (int i = 0; i < testNum; ++i) {
			sl.insert(i);
		}
		sl.printAll();
		System.out.println("=================================");
		System.out.println(sl.findAll(5));
		end = System.currentTimeMillis();
		System.out.println("__________________" + (end - start) + "ms");

		// sl.printAll();
		System.out.println("��ʼ����SkipList");
		start = System.currentTimeMillis();
		for (int i = 0; i < testNum; ++i) {
			sl.search(i);
		}
		end = System.currentTimeMillis();
		System.out.println("__________________" + (end - start) + "ms");
		System.out.println("��ʼ����LinkedList");
		start = System.currentTimeMillis();
		for (int i = 0; i < testNum; ++i) {
			ls.indexOf(i);
		}
		end = System.currentTimeMillis();
		System.out.println("__________________" + (end - start) + "ms");

		System.out.println("��ʼɾ��SkipList");
		start = System.currentTimeMillis();
		for (int i = 0; i < testNum; ++i) {
			sl.delete(i);
			// sl.printAll();
		}
		Thread.sleep(1000000);
		end = System.currentTimeMillis();
		System.out.println("__________________" + (end - start) + "ms");
		sl.printAll();

		System.out.println("��ʼɾ��LinkedList");
		start = System.currentTimeMillis();
		for (int i = 0; i < testNum; ++i) {
			ls.remove(0);
		}
		end = System.currentTimeMillis();
		System.out.println("__________________" + (end - start) + "ms");

		for (int i = 0; i < testNum; ++i) {
			sl.insert(i);
		}
		sl.printAll();
		for (int i = 0; i < 333; ++i) {
			sl.delete(i);
		}
		sl.printAll();
		for (int i = 0; i < testNum; ++i) {
			sl.search(i);
		}
	}
}
