package org.delin.Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import org.junit.Test;

public class Main_ {
	public static void setIfGreater(long[] A, int i, long val) {
		if (A[i] < val) {
			long prev = A[i];
			for (int j = i + 1; j < A.length; ++j) {
				long t = A[j];
				if (A[j] < prev) {
					A[j] = prev;
				}
				prev = t;
			}
			A[i] = val;
		}
	}

	public static void findMaxMulti(long[] A) {
		long[] pMax = new long[3];
		long[] fMin = new long[2];
		int len = A.length;
		for (int i = 0; i < len; ++i) {
			if (pMax[0] < A[i]) {
				setIfGreater(pMax, 0, A[i]);
			} else if (pMax[1] < A[i]) {
				setIfGreater(pMax, 1, A[i]);
			} else if (pMax[2] < A[i]) {
				setIfGreater(pMax, 2, A[i]);
			}

			if (fMin[0] > A[i]) {
				if (fMin[0] < fMin[1]) {
					fMin[1] = fMin[0];
				}
				fMin[0] = A[i];
			} else if (fMin[1] > A[i]) {
				fMin[1] = A[i];
			}
		}
		long r1 = pMax[0] * pMax[1] * pMax[2];
		long r2 = pMax[0] * fMin[0] * fMin[1];
		System.out.print(r1 > r2 ? r1 : r2);
	}

	public static void cal() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String[] numsIn = input.split("\\s");
		char[] nums1 = numsIn[0].toCharArray();
		char[] nums2 = numsIn[1].toCharArray();
		char[][] result = new char[nums1.length][];
		for (int i = nums1.length - 1; i >= 0; --i) {
			StringBuilder tempResult = new StringBuilder();
			int up = 0;
			int r = 0;
			for (int j = nums2.length - 1; j >= 0; --j) {
				int t = (nums1[i] - '0') * (nums2[j] - '0') + up;
				up = t / 10;
				r = t % 10;
				tempResult.insert(0, r);
			}
			if (up != 0) {
				tempResult.insert(0, up);
			}
			for (int k = nums1.length - 1; k > i; --k) {
				tempResult.append('0');
			}
			result[i] = tempResult.toString().toCharArray();
		}
		char[] sum = result[0];
		for (int i = 1; i < result.length; ++i) {
			char[] cur = result[i];
			char[] s, l;
			if (sum.length < cur.length) {
				l = cur;
				s = sum;
			} else {
				l = sum;
				s = cur;
			}
			int up = 0, r = 0;
			int k = s.length - 1;
			int j = l.length - 1;
			StringBuilder tSum = new StringBuilder();
			while (k >= 0) {
				int t = (s[k] - '0') + (sum[j] - '0') + up;
				up = t / 10;
				r = t % 10;
				tSum.insert(0, r);
				--k;
				--j;
			}

			for (; j >= 0; --j) {
				int t = (l[j] - '0') + up;
				up = t / 10;
				r = t % 10;
				tSum.insert(0, r);
			}
			if (up != 0) {
				tSum.insert(0, r);
			}
			sum = tSum.toString().toCharArray();
		}

		System.out.print(new String(sum));
	}

	public static void giveChocolate() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] h = new int[n];
		for (int i = 0; i < n; ++i) {
			h[i] = scan.nextInt();
		}

		int m = scan.nextInt();
		int[] w = new int[m];
		for (int i = 0; i < m; ++i) {
			w[i] = scan.nextInt();
		}

		Arrays.sort(h);
		Arrays.sort(w);

		int i = 0, j = 0, count = 0;
		while (i < n && j < m) {
			if (w[j] >= h[i]) {
				++i;
				++count;
			}
			++j;
		}
		System.out.print(count);

	}

	public static void mazeProblem() {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		char[][] matrix = new char[m][];
		for (int i = 0; i < m; ++i) {
			matrix[i] = in.nextLine().toCharArray();
		}

		int[] start = new int[2];
		int[] end = new int[2];
		ArrayList<int[]> key = new ArrayList<>(), door = new ArrayList<>();

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (matrix[i][j] == '2') {
					start[0] = i;
					start[1] = j;
				} else if (matrix[i][j] == '3') {
					end[0] = i;
					end[1] = j;
				} else if ('a' <= matrix[i][j] && matrix[i][j] <= 'z') {
					int[] tKeys = new int[3];
					tKeys[0] = matrix[i][j];
					tKeys[1] = i;
					tKeys[2] = j;
					key.add(tKeys);
				} else if ('A' <= matrix[i][j] && matrix[i][j] <= 'Z') {
					int[] tDoors = new int[3];
					tDoors[0] = matrix[i][j];
					tDoors[1] = i;
					tDoors[2] = j;
					door.add(tDoors);
				}
			}
		}
		// 初始化钥匙获取状态数组
		int[][] isGet = new int[key.size()][2];
		for (int i = 0; i < key.size(); ++i) {
			isGet[i][0] = key.get(i)[0];
			isGet[i][1] = 0;
		}
		// 该问题转换成求是否经过钥匙到达门
		// 或者到达门拿到钥匙

		// 先求到达出口需要经过的门
	}

	public static void countColorCure() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		char[] charset = new char[2];
		int j = 0;
		for (int i = 0; i < input.length(); ++i) {
			if (j < 2 && charset[0] != input.charAt(i) && charset[1] != input.charAt(i)) {
				charset[j++] = input.charAt(i);
			} else if (j == 2 && charset[0] != input.charAt(i) && charset[1] != input.charAt(i)) {
				System.out.print(0);
				return;
			}
		}

		if (j == 1) {
			System.out.print(1);
		} else {
			System.out.print(2);
		}
	}

	public static void chorue_Wangyi() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.nextInt();
		}

		int k = in.nextInt();
		int d = in.nextInt();

		// 初始化dp数组
		// 记录乘积最大值
		// 记录乘积最小值
		long[][] maxs = new long[n][k];
		long[][] mins = new long[n][k];
		for (int i = 0; i < n; ++i) {
			maxs[i][0] = a[i];
			mins[i][0] = a[i];
		}
		for (int i = 1; i < k; ++i) {
			for (int j = i; j < n; ++j) {
				// 限制条件是 剩下的元素不能小于i-1个
				for (int b = j - 1; b >= j - d && b >= i - 1; --b) {
					long cur1 = maxs[b][i - 1] * a[j];
					long cur2 = mins[b][i - 1] * a[j];
					long mx = Math.max(cur1, cur2);
					long mn = Math.min(cur1, cur2);
					if (mx > maxs[j][i]) {
						maxs[j][i] = mx;
					}
					if (mn < mins[j][i]) {
						mins[j][i] = mn;
					}
				}
			}
		}
		long max = maxs[0][k - 1];
		for (int i = 1; i < n; ++i) {
			if (max < maxs[i][k - 1]) {
				max = maxs[i][k - 1];
			}
		}

		System.out.println(max);
	}

	public static void chorue_Wangyi_Path() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.nextInt();
		}

		int k = in.nextInt();
		int d = in.nextInt();

		// 初始化dp数组
		// 记录乘积最大值
		// 记录乘积最小值
		long[][] maxs = new long[n][k];
		long[][] mins = new long[n][k];

		for (int i = 0; i < n; ++i) {
			maxs[i][0] = a[i];
			mins[i][0] = a[i];
		}
		for (int i = 1; i < k; ++i) {
			for (int j = i; j < n; ++j) {
				// 限制条件是 剩下的元素不能小于i-1个
				for (int b = j - 1; b >= j - d && b >= i - 1; --b) {
					long cur1 = maxs[b][i - 1] * a[j];
					long cur2 = mins[b][i - 1] * a[j];
					long mx = Math.max(cur1, cur2);
					long mn = Math.min(cur1, cur2);
					if (mx > maxs[j][i]) {
						maxs[j][i] = mx;
					}
					if (mn < mins[j][i]) {
						mins[j][i] = mn;
					}
				}
			}
		}

		LinkedList<Integer> path = new LinkedList<>();
		int max = 0;
		for (int i = 1; i < n; ++i) {
			if (max < maxs[i][k - 1]) {
				max = i;
			}
		}

		path.push(max);
		long tm = maxs[max][k - 1];
		for (int j = k - 2; j >= 0; --j) {
			tm = tm / a[max];
			System.out.println("------------------");
			System.out.println("tm=" + tm);
			if (tm < 0) {
				for (int i = 0; i < n; ++i) {
					System.out.println("mins：" + mins[i][j]);
					if (tm == mins[i][j]) {
						path.push(i);
						max = i;
						break;
					}
				}
			} else {
				for (int i = 0; i < n; ++i) {
					System.out.println("maxs：" + mins[i][j]);
					if (tm == maxs[i][j]) {
						path.push(i);
						max = i;
						break;
					}
				}
			}
		}

		System.out.println(path);
	}

	public static void charge(int m, int[] a) {
		int[] temp = new int[m + 1];
		for (int i = 1; i < m + 1; ++i) {
			for (int j = 0; j < a.length; ++j) {
				if (i - a[j] < 0) {
					continue;
				}
				if (temp[i] == 0) {
					temp[i] = temp[i - a[j]] + 1;
				} else {
					temp[i] = Math.min(temp[i - a[j]] + 1, temp[i]);
				}
			}
		}

		System.out.println(temp[m]);
	}

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		// int n = in.nextInt();
		// long[] a = new long[n];
		// for (int i = 0; i < n; ++i) {
		// a[i] = in.nextLong();
		// }
		// String s=in.nextLine();
		// String[] str=s.split("\\s");
		// int[] a=new int[str.length];
		// for(int i=0;i<str.length;i++){
		// a[i]=Integer.parseInt(str[i]);
		// }

		// findMaxMulti(a);
		// cal();
		// giveChocolate();
		// countColorCure();
		chorue_Wangyi_Path();
		// charge(3, new int[] { 1, 2, 3, 4, 5 });
	}
}
