package org.delin.Algorithm.sort;

import org.delin.Algorithm.TestUtils;

public class Sorter {
	/**
	 * 选择排序
	 * O(n*n)
	 * @param arr
	 */
	public static void select(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; ++i) {
			for (int j = i; j < len; ++j) {
				if (arr[i] > arr[j]) {
					swap(arr, i, j);
				}
			}
		}
	}

	/**
	 * 冒泡排序
	 * O(n*n)
	 * @param arr
	 */
	public static void bubble(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; ++i) {
			boolean flag = false;
			for (int j = 0; j < len - 1; ++j) {
				if (arr[j + 1] < arr[j]) {
					swap(arr, j + 1, j);
					flag = true;
				}
			}
			if (!flag) {
				break;
			}
		}
	}

	/**
	 * 插入排序
	 * O(n*n)
	 * 最坏情况 倒序
	 * @param arr
	 */
	public static void insertSort(int[] arr) {
		int len = arr.length;
		for (int i = 1; i < len; ++i) {
			int j = i - 1;
			int t = arr[i];
			while (j >= 0 && arr[j] > t) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = t;
		}
	}

	/**
	 * shell排序
	 * 主要减少了移动元素的次数
	 * @param arr
	 */
	public static void shellSort(int[] arr) {
		int len = arr.length;
		int h = 1;
		// 选择合适的分组因子
		while (h <= arr.length / 3) {
			h = h * 3 + 1;
		}
		while (h > 0) {
			for (int i = h; i < len; i += h) {
				int j = i - h;
				int t = arr[i];
				while (j >= 0 && arr[j] > t) {
					arr[j + h] = arr[j];
					j -= h;
				}
				arr[j + h] = t;
			}
			h = (h - 1) / 3;
		}
	}
	static int c=0;
	/**
	 * nlgn
	 * @param arr
	 * @param l
	 * @param h
	 */
	public static void quickSort(int[] arr, int l, int h) {
		if (l < h) {
			int i = partition(arr, l, h);
			// i不参与排序
			quickSort(arr, l, i - 1);
			quickSort(arr, i + 1, h);
		}
	}

	/**
	 * 寻找划分点
	 * @param arr
	 * @param l
	 * @param h
	 * @return
	 */
	public static int partition(int[] arr, int l, int h) {
		int key = arr[l];
		int i = l, j = h;
		while (i != j) {
			while (arr[j] >= key && i < j) {
				j--;
			}
			while (arr[i] <= key && i < j) {
				i++;
			}
			if (i < j) {
				swap(arr, i, j);
				c++;
			}
		}
		//把基准点元素交换到i点 左边的元素都小于key 右边的元素都大于key
		//|<-----<i>------>|
		arr[l] = arr[i];
		arr[i] = key;
		c++;
		return i;
	}

	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static void main(String[] args) {
		TestUtils.test(() -> {
			bubble(new int[] { 3, 4, 7, 213, 32, 43, 23, 54, 768, 343, 65, 2, 76 });
		});
		TestUtils.test(() -> {
			insertSort(new int[] { 3, 4, 7, 213, 32, 43, 23, 54, 768, 343, 65, 2, 76 });
		});
		TestUtils.test(() -> {
			shellSort(new int[] { 3, 4, 7, 213, 32, 43, 23, 54, 768, 343, 65, 2, 76 });
		});
		TestUtils.test(() -> {
			bubble(new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
		});
		TestUtils.test(() -> {
			insertSort(new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
		});
		TestUtils.test(() -> {
			shellSort(new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
		});
		TestUtils.test(() -> {
			int[] arr = new int[] { 3, 4, 7, 213, 32, 43, 23, 54, 768, 343, 65, 2, 76 };
			quickSort(arr, 0, arr.length - 1);
			TestUtils.printArr(c+"次"+"快速排序:", arr);
		});

	}
}
