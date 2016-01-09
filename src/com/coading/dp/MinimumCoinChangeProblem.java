package com.coading.dp;

import java.util.Arrays;

/*
 * Given coins of denomination v1=1,v2,v3,v4,...,vn in ascending order find minimum number of coins required to make an amount P
 */

public class MinimumCoinChangeProblem {

	public static void main(String[] args) {
		int[] d = { 1, 3, 5, 7 };
		int sum = 12;

		int minCoin = dpMinCoinChnage(sum, d);

		System.out.println(minCoin);

	}

	/*
	 * Naive approach
	 */
	public static int minCoin(int coins[], int V) {
		if (V == 0)
			return 0;

		int m = coins.length;
		int result = Integer.MAX_VALUE;

		for (int i = 0; i < m; i++) {
			if (coins[i] <= V) {
				int sub_result = minCoin(coins, V - coins[i]);

				if (sub_result != Integer.MAX_VALUE && sub_result + 1 < result) {
					result = sub_result + 1;
				}
			}
		}

		return result;
	}

	/*
	 * Dynamic Programming Approach. Assume that denomination array is sorted in
	 * ascending order.
	 */

	public static int dpMinCoinChnage(int sum, int[] d) {
		
		int a[] = new int[sum + 1];
		Arrays.fill(a, Integer.MAX_VALUE);
		a[0] = 0;

		for (int i = 1; i <= sum; i++) // Iterate through each element of sum
										// array.
		{
			for (int j = d.length - 1; j >= 0; j--) // check which element is <=
													// current index.
			{
				if (d[j] <= i) {
					a[i] = Math.min(1 + a[i - d[j]], a[i]);
				}
			}
		}

		return a[sum];

	}

}
