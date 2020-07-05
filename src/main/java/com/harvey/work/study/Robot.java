package com.harvey.work.study;

/**
 * @program: Study
 * @description:
 * @author: Harvey_yang
 * @modified By：
 * @version: $
 * @create: 2020-04-04 20:27
 **/
public class Robot {
    public static void main(String[] args) {
        int m = 8;
        int n = 4;
        
        int [][] arr = new int [m][n];
        arr[0][0] = 0;
        arr[0][1] = 1;
        arr[1][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(i-1>=0){
                    arr[i][j] += arr[i-1][j];
                }
                if(j-1 >=0){
                    arr[i][j] += arr[i][j-1];
                }
            }
        }
        System.out.println(arr[m-1][n-1]);
    }
}
