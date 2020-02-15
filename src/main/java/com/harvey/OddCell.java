package com.harvey;

import com.alibaba.druid.sql.visitor.functions.Char;

public class OddCell {

	public static void main(String[] args) {
		int[][] indices = { { 0, 1 }, { 1, 1 } };
		System.out.println(oddCells(2, 3, indices));
	}

	public static int oddCells(int n, int m, int[][] indices) {
		int countRow = 0;// 行
		int countColumn = 0;// 列
		int[] row = new int[n]; // 行
		int[] Column = new int[m]; // 列
		for (int[] is : indices) {
			row[is[0]]++;
			if (row[is[0]] == 2) {
				row[is[0]] = 0;
				countRow--;
			} else {
				countRow++;
			}
			Column[is[1]]++;
			if (Column[is[1]] == 2) {
				Column[is[1]] = 0;
				countColumn--;
			} else {
				countColumn++;
			}
		}
		return countRow * m + n * countColumn - 2 * countColumn * countRow;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int value1 = l1.val;
		int value2 = l2.val;
		int count = 1;
		ListNode index = l1;
		while (index != null) {
			index = index.next;
			value1 = value1 + (index.val) * (10 ^ count);
			count++;
		}
		System.out.println(value1);
		count = 1;
		index = l2.next;
		while (index != null) {
			value2 = value2 + (index.val) * (10 ^ count);
			index = index.next;
			count++;
		}
		String sum = String.valueOf(value1 + value2);
		char[] num = sum.toCharArray();
		index = new ListNode(num[0]);
		for (int i = 0; i < num.length; i++) {
			System.out.println(num[i]);
		}
		return l1;
	}
}
