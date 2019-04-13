package org.delin.Algorithm;

import java.util.Arrays;
import java.util.Scanner;
/**
 * 拼多多2019内推后端笔试
 * @author delin
 */
public class PDD {
	public static void findMinNonRepeat(String input, int limit) {

	}

	/**
	 * 
	 * 
	6 3
	1 1
	3 5
	4 8
	6 4
	10 3
	11 2
	 * @param args
	 */

	public static void test2() {
		Scanner in = new Scanner(System.in);
		int n, d;
		n = in.nextInt();
		d = in.nextInt();
		int[][] input = new int[n][2];
		for (int i = 0; i < n; i++) {
			input[i][0] = in.nextInt();
			input[i][1] = in.nextInt();
		}

		Arrays.sort(input, (a, b) -> a[1] - b[1]);
		boolean flag = false;
		for (int i = n - 1; i >= 1; --i) {
			for (int j = i - 1; j >= 0; --j) {
				if (Math.abs(input[i][0] - input[j][0]) >= d) {
					System.out.println(input[i][1] + input[j][1]);
					flag = true;
					break;
				}
			}
		}
		if (!flag) {
			System.out.println(0);
		}
	}

	public static void main(String[] args) {
		System.out.println(-7%5);
		Scanner in = new Scanner(System.in);
		String a = in.nextLine();
		String b = in.nextLine();
		int countL = 0, countR = 0;
		for (int i = 0; i < a.length(); ++i) {
			if (a.charAt(i) == '(') {
				countL++;
			}
			if (a.charAt(i) == ')') {
				countR++;
			}
		}
		for (int i = 0; i < b.length(); ++i) {
			if (b.charAt(i) == '(') {
				countL++;
			}
			if (b.charAt(i) == ')') {
				countR++;
			}
		}
		if (countL != countR) {
			System.out.println(0);
			return;
		}

	}

	// public static void main(String[] args) {
	// Scanner in = new Scanner(System.in);
	// String input = in.nextLine();
	// int minIndex = 26;
	// int[] repeat = new int[26];
	// for (int ii = 0; ii < 26; ++ii) {
	// repeat[ii] = 26;
	// }
	// for (int ik = 0; ik < input.length(); ++ik) {
	// int index = Character.toLowerCase(input.charAt(ik)) - 'a';
	// repeat[index] = ik;
	// }
	//
	// for (int ii = 0; ii < 26; ++ii) {
	// if (repeat[ii] < minIndex) {
	// minIndex = repeat[ii];
	// }
	// }
	// char chmin = Character.toLowerCase(input.charAt(minIndex));
	// for (int k = 0; k < minIndex; ++k) {
	// char ch = Character.toLowerCase(input.charAt(k));
	// if (ch < chmin) {
	// chmin = ch;
	// }
	// }
	// System.out.println(chmin);
	// }
	// public static void main(String[] args) {
	// Scanner in = new Scanner(System.in);
	// String input = in.nextLine();
	// char min = 'z';
	// for (int i = 0; i < input.length(); ++i) {
	// char ch = Character.toLowerCase(input.charAt(i));
	// ;
	// if (ch < min) {
	// min = ch;
	// }
	// }
	// System.out.println(min);
	//
	// }
}
