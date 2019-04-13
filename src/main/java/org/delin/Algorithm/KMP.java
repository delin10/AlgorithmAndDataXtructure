package org.delin.Algorithm;

/**
 * @author delin
 *
 */
public class KMP {
	public static int[] getModel(String str) {
		int len = str.length();
		int[] pattern = new int[len];

		int i = 0, j = -1;
		// 第一个位置设置-1
		/*
		 * 因为当第一个位置不匹配时，需要指针向后移动
		 */
		pattern[0] = -1;
		while (i < len - 1) {
			/**
			 * -1之后再次匹配失败
			 * 若匹配中断则是第二种情况
			 */
			if (j == -1 || str.charAt(i) == str.charAt(j)) {
				pattern[++i] = ++j;
			} else {
				// 进行模式回溯
				System.out.println("<-<-<-<-<-" + j + " 回溯..." + pattern[j]);
				j = pattern[j];
			}
			TestUtils.printArr("模式数组 ", pattern);
		}
		return pattern;
	}

	public static int match(String str, String pStr, int[] pattern) {
		int i = 0, j = 0, len = str.length(), pLen = pStr.length();
		while (i < len && j < pLen) {
			if (str.charAt(i) == pStr.charAt(j)) {
				++i;
				++j;
			} else {
				j = pattern[j];
				//不跟前缀匹配 
				if (j == -1) {
					++i;
					++j;
				}
			}
		}
		if (i==len) {
			return -1;
		}
		return i - pLen;
	}
}
