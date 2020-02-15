package com.harvey.work.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.harvey.work.util.AddWaterMark;

/*@RestController
@RequestMapping({"/song"})*/
public class SongListController
{
  public static void main(String[] args) {
    try {
      int index;
      String fileName = "CoCo歌单（一）";
      String title = "玛雅乐团—CoCo";
      String title2 = "M  a  Y  a     Y  u  e  T  u  a  n";
      String order = "—歌曲名单（一）—";
      String songs = "断了的弦，说好不哭，我不配，我们，孤独患者，浪子回头，第三人称，离人，水星记，演员，男孩，告白气球，圣诞结，淘汰，需要人陪，心跳，唯一,kiss Goodbye，国王与乞丐，空白格，为你我受冷风吹，戒烟，你就不要想起我，亲密爱人，明明就，枫";
      songs = songs.replace("，", ",");
      File file = new File(".\\src\\main\\resources\\static\\songlistWidth.jpg");

      String fileType = "jpg";
      InputStream fis = new FileInputStream(file);
      AddWaterMark addWaterMark = new AddWaterMark();
      InputStream fis3 = addWaterMark.addWaterMarkTitle(fis, fileType, title);
      InputStream fis5 = addWaterMark.addWaterMarkOrder(fis3, fileType, order);
      InputStream fis6 = addWaterMark.addWaterMarkSongs(fis5, fileType, songs.split(","), true);
      byte[] bytes = new byte[1024];
      FileOutputStream downloadFile = new FileOutputStream("D:\\" + fileName + ".jpg");
      while ((index = fis6.read(bytes)) != -1) {
        downloadFile.write(bytes, 0, index);
        downloadFile.flush();
      }
      downloadFile.close();
      fis.close();
    } catch (Exception e) {
      e.printStackTrace(); }
  }

  public static String ascii2native(String ascii) {
    List ascii_s = new ArrayList();
    String zhengz = "\\\\u[0-9,a-f,A-F]{4}";
    Pattern p = Pattern.compile(zhengz);
    Matcher m = p.matcher(ascii);
    while (m.find())
      ascii_s.add(m.group());

    int i = 0; for (int j = 2; i < ascii_s.size(); ++i) {
      String code = ((String)ascii_s.get(i)).substring(j, j + 4);
      char ch = (char)Integer.parseInt(code, 16);
      ascii = ascii.replace((CharSequence)ascii_s.get(i), String.valueOf(ch));
    }
    return ascii;
  }
}