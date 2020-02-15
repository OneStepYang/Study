package com.harvey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadTxt {
	public static void main(String[] args) {
		File file = new File("D:/ROFTP2019073110.txt");
        StringBuilder localStrBulider = new StringBuilder();
        if(file.isFile() && file.exists()) {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "gbk");
                BufferedReader bufferReader = new BufferedReader(inputStreamReader);
                try {
                	String lineStr = bufferReader.readLine();
                	System.out.println(lineStr);
                	int i = 1;
                    while((lineStr = bufferReader.readLine()) != null) {
                    	String[] array=lineStr.split("\\|"); 
                    	while(array.length<18) {
                    		lineStr +=bufferReader.readLine();
                    		array=lineStr.split("\\|");
                    	}
                    	System.out.println(i);
                        System.out.println(lineStr);
                        i++;
                    }
                    bufferReader.close();
                    inputStreamReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("file read error!");
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                System.out.println("file catch unsupported encoding!");
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                System.out.println("file not found!");
                e.printStackTrace();
            }
        }else {
            System.out.println("file is not a file or file is not existing!");
        }
        System.out.println("localStrBulider.toString():" + localStrBulider.toString());
	}
}
