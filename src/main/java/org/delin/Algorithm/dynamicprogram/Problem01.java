package org.delin.Algorithm.dynamicprogram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.delin.Algorithm.util.Utils;
/**
 * 
 * @author delin
 */
public class Problem01 {
	//this problem from 《算法图解》 --动态规划部分
	public static void plan(Good goods[], int bagSize) {
		int[][] planMatrix = new int[goods.length][bagSize + 1];
		for (int i = 1; i < bagSize; ++i) {
			planMatrix[0][i] = goods[0].weight > bagSize ? 0 : goods[0].price;
		}
		for (int i = 1; i < goods.length; ++i) {
			for (int j = 1; j <= bagSize; ++j) {
				System.out.println(goods[i]);
				// 如果刚好相等，第一列必须为0，无法放入新物件
				if (goods[i].weight <= j) {
					System.out.println("try to put in...");
					System.out.println(goods[i]);
					System.out.println("try to compare...");
					print(planMatrix);
					System.out.println(planMatrix[i - 1][j]);
					System.out.println(planMatrix[i - 1][j - goods[i].weight] + goods[i].price);
					planMatrix[i][j] = Math.max(planMatrix[i - 1][j],
							planMatrix[i - 1][j - goods[i].weight] + goods[i].price);
					System.out.println("after compare...");
					print(planMatrix);
				} else {
					planMatrix[i][j] = planMatrix[i - 1][j];
				}

			}
		}
		print(planMatrix);
		System.out.println("最终结果是:" + planMatrix[goods.length - 1][bagSize - 1]);
	}
	//LCS算法
	public static void LCS(String a, String b) {
		int aLen = a.length(), bLen = b.length();
		int[][] matrix = new int[aLen][bLen];
		Arrays.setAll(matrix[0], x -> a.charAt(0) == b.charAt(0) ? 1 : 0);

		for (int i = 0; i < aLen; ++i) {
			matrix[i][0] = a.charAt(0) == b.charAt(0) ? 1 : 0;
		}
		for (int i = 1; i < aLen; ++i) {
			for (int j = 1; j < bLen; ++j) {
				matrix[i][j] = a.charAt(i) == b.charAt(j) ? matrix[i - 1][j] + 1
						: Math.max(matrix[i - 1][j], matrix[i][j - 1]);
			}
		}
		print(matrix);
	}
	//《算法导论》 动态规划部分
	public static void cutIron(int n) {
		Map<Integer, Integer> prices = new HashMap<>();
		prices.put(1, 1);
		prices.put(2, 5);
		prices.put(3, 8);
		prices.put(4, 9);
		prices.put(5, 10);
		prices.put(6, 17);
		prices.put(7, 17);
		prices.put(8, 20);
		prices.put(9, 24);
		prices.put(10, 30);

		int[] matrix = new int[n];
		int limit = n > 10 ? 10 : n;
		for (int i = 0; i < limit; ++i) {
			matrix[i] = prices.get(i + 1);
		}
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < i && j < 10; ++j) {
				// here is important
				// i-j-1
				matrix[i] = Math.max(matrix[j] + matrix[i - j - 1], matrix[i]);
			}
			System.out.println(matrix[i]);
		}
		System.out.println("最终数组");
		Arrays.stream(matrix).forEach(System.out::print);

		System.out.println("最大收益:" + matrix[n - 1]);
	}

	/**
	 * @author delin
	 */
	public static class Good {
		private int price;
		private int weight;
		private String name;

		public Good(int price, int weight, String name) {
			super();
			this.price = price;
			this.weight = weight;
			this.name = name;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name + ":" + price + "元" + "," + weight + "磅";
		}
	}

	public static void main(String[] args) {
		Good good0 = new Good(1500, 1, "吉他");
		Good good1 = new Good(2000, 3, "笔记本电脑");
		Good good2 = new Good(3000, 4, "音响");
		Good good3 = new Good(2000, 1, "iphone");
		Good[] goods = { good0, good1, good2, good3 };
		plan(goods, 4);
		System.out.println( Utils.reverseString("character"));
		LCS("character", Utils.reverseString("character"));

		//cutIron(5);
	}

	public static void print(int[][] arr) {
		for (int i = 0; i < arr.length; ++i) {
			for (int j = 0; j < arr[i].length; ++j) {
				System.out.print(" ");
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
}
