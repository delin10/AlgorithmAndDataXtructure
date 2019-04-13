package org.delin.Algorithm;

import java.util.Arrays;

public class TestUtils {
	public static <T> void printArr(String prev, T[] arr) {
		System.out.println(prev);
		Arrays.stream(arr).forEach(e -> System.out.print(e + " "));
		System.out.println();
	}
	public static <T> void printArr(String prev, int[] arr) {
		System.out.println(prev);
		Arrays.stream(arr).forEach(e -> System.out.print(e + " "));
		System.out.println();
	}
	public static void test(Runnable run) {
		long start = System.currentTimeMillis();
		run.run();
		long end = System.currentTimeMillis();
		System.out.println("时间: " + (end - start));
	}
}
