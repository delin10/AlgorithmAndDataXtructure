package org.delin.Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class TestSort {
	@Test
	public void testTime() {
		HeapSort<Integer> sort = new HeapSort<>((a, b) -> a - b);
		Integer[] arr = { 9, 23, 43, 5, 1, 4, 65, 3, 2 };
		sort.headSort(arr, sort.cmp);
		TestUtils.printArr("结果  ", arr);
		ArrayList<Integer> arrl2 = new ArrayList<>();
		Random random = new Random();
		int num = 10000;
		for (int i = 0; i < num; ++i) {
			arrl2.add(random.nextInt(Integer.MAX_VALUE));
		}
		Integer[] arr2 = arrl2.toArray(new Integer[0]);
		TestUtils.test(() -> {
			sort.headSort(arr2, sort.cmp);
		});
		TestUtils.test(() -> {
			Arrays.sort(arr2, sort.cmp);
		});
	}
}
