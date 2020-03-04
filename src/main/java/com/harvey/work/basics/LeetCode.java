package com.harvey.work.basics;

/**
 * @program: Study
 * @ClassName : LeetCode
 * @description: LeetCode刷题测试类
 * @author: Harvey
 * @create: 2020-02-25 13:24
 */
public class LeetCode {
    public static void main(String[] args) {
        int[] height = {-34,37,51,3,-12,-50,51,100,-47,99,34,14,-13,89,31,-14,-44,23,-38,6};
        int k = 151;
        System.out.println(shortestSubarray2(height, k));
    }

    public static int shortestSubarray(int[] A, int K) {
        int length = A.length + 1;
        int count = 0;
        int sum = 0;
        int begin = 0;
        for (int i = 0; i < A.length; i++) {
            if (i == begin && A[i] <= 0) {
                begin++;
                continue;
            }
            if (A[i] >= K) {
                length = 1;
                break;
            }
            sum = sum + A[i];
            count++;
            if (sum >= K) {
                if (count <= length) {
                    length = count;
                }
                i = begin;
                begin = begin + 1;
                sum = 0;
                count = 0;
                continue;
            }
            if (sum <= 0) {
                begin = i + 1;
                sum = 0;
                count = 0;
                continue;
            }
        }
        if (length == A.length + 1) {
            return -1;
        } else {
            return length;
        }
    }

    public static int shortestSubarray2(int[] A, int K) {
        int length = A.length + 1;
        int count = 1;
        int sum = 0;
        int j = 0;
        int i = 0;
        for (i = 0; i < A.length; i++) {
            if (A[i] <= 0) {
                continue;
            }
            if (A[i] >= K) {
                length = 1;
                break;
            }
            sum = A[i];
            for (j = i + 1; j < A.length; j++) {
                sum = sum + A[j];
                if (sum <= 0) {
                    i = j;
                    count = 1;
                    break;
                }
                count++;
                while (sum >= K) {
                    if (count < length) {
                        length = count;
                        if(length==1){
                            return 1;
                        }
                    }
                    sum=sum-A[i];
                    i++;
                    count--;
                    while(i<A.length&&A[i]<0){
                        sum=sum-A[i];
                        i++;
                        count--;
                    }

                }
            }
        }
        if (length == A.length + 1) {
            return -1;
        } else {
            return length;
        }
    }
}