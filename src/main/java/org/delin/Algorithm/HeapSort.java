package org.delin.Algorithm;

import java.util.Comparator;
/**
 * 调整为最大/最小堆
 * 交换
 * loop
 * 
 * 最大堆 根节点为最大值
 * @author delin
 * @param <T>
 */
public class HeapSort<T> {
	public Comparator<T> cmp;

	public HeapSort(Comparator<T> cmp) {
		this.cmp = cmp;
	}
	/**
	 *                 i
	 *            /         \
	 *        2*(i+1)-1   2*(i+1)
	 * @param arr
	 * @param i
	 * @param limit
	 */
	public void maxHeap(T[] arr, int i, int limit) {
		if (i >= arr.length) {
			return;
		}
		int l = getLeftIndex(i);
		int r = getRightIndex(i);
		int max = i;

		if (l <= limit) {
			if (cmp.compare(arr[max], arr[l]) < 0) {
				max = l;
			}
		}
		if (r <= limit) {
			if (cmp.compare(arr[max], arr[r]) < 0) {
				max = r;
			}
		}

		swapArr(arr, i, max);
	}

	public int getLeftIndex(int i) {
		return (i + 1) * 2 - 1;
	}

	public int getRightIndex(int i) {
		return (i + 1) * 2;
	}

	public T getLeft(T[] arr, int i) {
		if (i < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
		int li = i * 2 - 1;
		if (li > arr.length) {
			return null;
		}
		return arr[li];
	}

	public T getRight(T[] arr, int i) {
		if (i < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
		int ri = i * 2;
		if (ri >= arr.length) {
			return null;
		}
		return arr[ri];
	}

	public void swapArr(T[] arr, int i, int j) {
		if (i >= arr.length || j >= arr.length || i < 0 || j < 0) {
			return;
		}
		T t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	/**
	 * nlogn
	 * 
	 * @param arr
	 * @param cmp
	 */
	public void headSort(T[] arr, Comparator<T> cmp) {
		for (int i = arr.length - 1; i >0; --i) {
			adjustMaxHeap(arr, i);
			swapArr(arr, 0, i);
		}
	}
	/**
	 * 
	 * 调整为最大堆
	 * @param arr
	 * @param i
	 */
	public void adjustMaxHeap(T[] arr, int i) {
		//获得最后一个结点的父节点
		for (int j = (i + 1) / 2 - 1; j >= 0; --j) {
			maxHeap(arr, j, i);
		}
	}
}
