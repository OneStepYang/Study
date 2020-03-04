package com.harvey.work.basics;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @program: Study
 * @ClassName : StudyMongoDb
 * @description: 学习MongoDB
 * @author: Harvey
 * @create: 2020-03-04 16:19
 */
public class StudyMongoDb {
    public static void main(String[] args) {
        try {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "password".toCharArray());
            ServerAddress serverAddress = null;
            serverAddress = new ServerAddress("182.92.67.19", 27017);
            MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
            DB db = mongoClient.getDB("admin");
            DBCollection coll = db.getCollection("system.users");
            System.out.println(coll.getCount());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}