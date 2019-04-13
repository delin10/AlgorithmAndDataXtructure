package org.delin.Algorithm.util;

public class Utils {
	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static String reverseString(String str) {
		char[] strr = new char[str.length()];
		int k = 0;
		for (int i = str.length() - 1; i >= 0; --i) {
			strr[k++] = str.charAt(i);
		}
		return new String(strr);
	}
	/**
	 * 计算v的位数
	 * @param v
	 * @return
	 */
	public static int countNumberDigit(int v) {
		int devide = 10, count = 1;

		while (v / devide != 0) {
			count++;
			devide *= 10;
		}
		return count;
	}
	/**
	 * 获得整数某个位
	 * @param v
	 * @param len v的位数
	 * @param pos 1,2...,len
	 * @return
	 */
	public static int getDigit(int v, int len, int pos) {
		//负数的情况尚未考虑
		if (len == pos) {
			return v / (int) Math.pow(10, len - 1);
		} else if (pos == 1) {
			return v % 10;
		} else {
			return (int) ((v % (int) Math.pow(10, pos))/Math.pow(10, pos-1));
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getDigit(12345678, 8, 3));
	}
}
