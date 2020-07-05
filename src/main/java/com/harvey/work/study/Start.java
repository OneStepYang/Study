package com.harvey.work.study;

/**
 * @program: Study
 * @description: LeetCode
 * @author: Harvey_yang
 * @modified By：
 * @version: $
 * @create: 2020-04-20 21:17
 **/
public class Start{
    public static void main(String[] args) {
        int [] nums = {1,15,47,6,2,78,14,36,95,75,42,38,91};
        quickSort(nums,0,nums.length-1);
        for(int i:nums){
            System.out.println(i);
        }
    }

    public static void quickSort(int[] arr,int left,int right){
        if(left>=right){
            return;
        }
        int base = arr[left];
        int x = left;
        int y = right;
        while(x<y){
            while(x<y && arr[y]>=base){
                y--;
            }
            while(x<y && arr[x]<=base){
                x++;
            }
            if(x<y){
                int temp=arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }
        }
        arr[left] = arr[x];
        arr[x] = base;
        quickSort(arr,left,x-1);
        quickSort(arr,x+1,right);
    }

}

