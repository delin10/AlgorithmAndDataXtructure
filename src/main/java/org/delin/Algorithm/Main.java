package org.delin.Algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> ins = new ArrayList<Integer>();
		int[] arr=new int[14];
		int t;
		while ((t = in.nextInt()) != 0) {
			ins.add(t);
		}
		for (int i = 0; i < ins.size(); ++i) {
			int v=ins.get(i);
			if (arr[v]==0) {
				arr[v]=joseph2(v);
			}
			System.out.println(arr[v]);
		}

	}
}
