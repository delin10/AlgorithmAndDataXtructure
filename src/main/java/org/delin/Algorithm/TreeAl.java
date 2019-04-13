package org.delin.Algorithm;

import sun.tools.jar.resources.jar;

public class TreeAl {
	static class Node {
		Node left;
		Node right;
		char v;

		public Node(char v) {
			this.v = v;
		}
	}

	public static Node postCreate(char[] seq, int i) {
		if (i < 0) {
			return null;
		}
		Node root = new Node(seq[i]);
		root.left = postCreate(seq, i - 1);
		root.right = postCreate(seq, i - 2);
		return root;
	}

	public static Node fix(Node root, String mid) {
	}
	static int index=0;
	public static void fix(Node ggrand,Node grand, Node parent, Node cur, char[] mids) {
		fix(parent, cur, cur.left, mids);
		index++;
		if (cur.v != mids[index]) {
			
		}
		fix(parent, cur, cur.right, mids);
	}

	public static Node tempCreate(char[] mid, int i) {
		Node root = new Node(mid[i]);
		Node cur = root;
		for (int l = i - 1; l >= 0; l--) {
			cur.left = new Node(mid[l]);
			cur = cur.left;
		}
		cur = root;
		for (int r = i - 1; r >= 0; r--) {
			cur.right = new Node(mid[r]);
			cur = cur.right;
		}
		return root;
	}
	
	
	public static void crt(String mid,String post) {
		int i=1;
		Node uk=null,cur=null;
		while( true) {
			char ch=mid.charAt(i);
			//说明没有左子树 只有右子树
			if (ch==post.charAt(i-1)) {
				
			}//说明没有右子树
			else if(ch==post.charAt(i)){
				
			}
		}
	}
	
	public static Node crtL(Node l,char[] seq,int i) {
		
	}
}
