package com.harvey.work.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.harvey.work.entity.SongList;
import com.harvey.work.util.AddWaterMark;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

//@RestController //需要返回页面，所以不加这个注解
@Controller
public class SongListController
{
  @GetMapping({"/create"})
  public String aaaPage(){
    //ModelAndView mv = new ModelAndView();
    //mv.setViewName("test");
    return "songList";
  }
  @PostMapping({"/create"})
  public void create(SongList songList,HttpServletResponse res) {
    {
      res.setHeader("content-type", "application/x-jpg");
      res.setContentType("application/x-jpg");
      OutputStream os = null;
      try {
        os = res.getOutputStream();
        int index;
        String fileName = songList.getName()+"歌单（"+songList.getOrder()+"）";
        String title = "好声音—"+songList.getName();
        String order = "—歌曲名单（"+songList.getOrder()+"）—";
        String songs = songList.getSongStr().replace("，", ",");
        File file = new File("./src/main/resources/songlistWidth.jpg");

        String fileType = "jpg";
        InputStream fis = new FileInputStream(file);
        AddWaterMark addWaterMark = new AddWaterMark();
        InputStream fis3 = addWaterMark.addWaterMarkTitle(fis, fileType, title);
        InputStream fis5 = addWaterMark.addWaterMarkOrder(fis3, fileType, order);
        InputStream fis6 = addWaterMark.addWaterMarkSongs(fis5, fileType, songs.split(","), true);
        byte[] bytes = new byte[1024];
        while ((index = fis6.read(bytes)) != -1) {
          os.write(bytes, 0, index);
          os.flush();
        }
        os.close();
        fis.close();
      } catch (Exception e) {
        e.printStackTrace(); }
    }
  }

  public static void main(String[] args) {
    try {
      int index=0;
      String fileName = "钟停歌单（二）";
      String title = "玛雅乐团—钟停";
      String title2 = "M  a  Y  a     Y  u  e  T  u  a  n";
      String order = "—歌曲名单（二）—";
      String songs = "独家记忆,爱我别走,我好想你,越过山丘,虎口脱险,你瞒我瞒,说散就散,光年之外,一生有你,一生所爱,往后余生,浪子回头,等一分钟,就是爱你,kiss goodbye,你是我的眼,女人的选择,只是太爱你,贝加尔湖畔,想你想疯了,可惜不是你,突然想起你,天使的翅膀,拿走了什么,慢慢喜欢你,八十年代的歌,再见吧喵小姐,流着泪说分手,说好的幸福呢,你一定要幸福,永不失联的爱,我已经爱上你,关于郑州的记忆,父亲写的散文诗,你就不要想起我,有没有人告诉你,你怎么舍得我难过,一千个伤心的理由,这一生关于你的风景,世界这么大还是遇见你";
      songs = songs.replace("，", ",");
      File file = new File("./src/main/resources/songlistWidth.jpg");

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