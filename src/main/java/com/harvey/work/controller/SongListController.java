package com.harvey.work.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.harvey.work.entity.SongList;
import com.harvey.work.util.AddWaterMark;
import com.harvey.work.util.SongListUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.springframework.core.io.ClassPathResource;
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
@RequestMapping({"/"})
public class SongListController
{
  @GetMapping({"/create"})
  public void create(String singer, String songs, int begin, HttpServletResponse response)
  {
    try
    {
      songs = songs.replace("，", ",");
      String[] songsArr = songs.split(",");

      songsArr = SongListUtils.StringArrOrder(songsArr, 0, songsArr.length - 1);
      System.out.println("一共有" + songsArr.length + "首歌！");

      boolean flag = songsArr[(songsArr.length - 1)].length() > 14;
      int[] lengthArr = SongListUtils.countQuantity(songsArr.length, flag);
      System.out.println("一共有" + lengthArr.length + "张歌单！");

      String[] han = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五" };
      int hasMarked = 0;

      ZipArchiveOutputStream zous = new ZipArchiveOutputStream(response.getOutputStream());
      zous.setUseZip64(Zip64Mode.AsNeeded);
      for (int i = 0; i < lengthArr.length; ++i)
      {
        String[] songsArrThis;
        InputStream fis6;
        if ((i == lengthArr.length - 1) && (flag)) {
          songsArrThis = new String[lengthArr[i] * 2];
          System.arraycopy(songsArr, hasMarked, songsArrThis, 0, Math.min(songsArr.length - hasMarked, lengthArr[i] * 2));
        } else {
          songsArrThis = new String[lengthArr[i] * 3];
          System.arraycopy(songsArr, hasMarked, songsArrThis, 0, Math.min(songsArr.length - hasMarked, lengthArr[i] * 3));
        }
        String fileName = singer + "歌单（" + han[(i + begin)] + "）.jpg";
        //String title = "玛雅乐团—" + singer;
        //String order = "—歌曲名单（" + han[(i + begin)] + "）—";
        ClassPathResource resource = new ClassPathResource("newBG.jpg");
        if ((i == lengthArr.length - 1) && (flag))
          resource = new ClassPathResource("songListTwo.jpg");

        String fileType = "jpg";
        InputStream fis = resource.getInputStream();
        //InputStream fis1 = SongListUtils.addWaterMarkTitle(fis, fileType, title);
        //InputStream fis2 = SongListUtils.addWaterMarkOrder(fis1, fileType, order);

        if ((i == lengthArr.length - 1) && (flag))
          fis6 = SongListUtils.addWaterMarkTwoRows(fis, fileType, songsArrThis, lengthArr[i]);
        else
          fis6 = SongListUtils.addWaterMarkSongs(fis, fileType, songsArrThis, lengthArr[i]);

        byte[] bytes = new byte[1024];
        response.setHeader("Content-Type", "application/x-zip-compressed");
        response.setHeader("Content-Disposition", "attachment;filename=" + singer + ".zip");
        ArchiveEntry entry = new ZipArchiveEntry(fileName);
        zous.putArchiveEntry(entry);
        int index = 0;
        while ((index = fis6.read(bytes)) != -1)
          zous.write(bytes);

        zous.closeArchiveEntry();
        fis.close();
        hasMarked += lengthArr[i] * 3;
        System.out.println("第" + han[i] + "张歌单已经做完！");
      }
      zous.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    String singer;
    String[] songsArr;
    boolean flag;
    int[] lengthArr;
    String[] han;
    int hasMarked;
    int i;
    try {
      singer = "梓潼";
      String songs = "I Believe，Nothing's Gonna Change My Love For You，Die A Happy Man，Dear Life，That Girl，Toe，Must Be Doing Something Right，Cry On My Shoulder，Take Me Home Country Roads，You're Beautiful，Waiting For You，W-H-Y，Five Hundred Miles，Take Me To You Heart，春泥，青春纪念册，拯救，特别的爱给特别的你，牵挂你的人是我，浪子回头，花香，我该走了吗，红颜旧，蟑螂小强，后来，过火，信仰，体面，初爱，解脱，放生，梦一场，王妃，新不了情，如果没有你，会痛的石头，月亮代表谁的心，爱很简单，小镇姑娘，第一千个昼夜，台北寂寞部屋，五月的雪，下沙，恋上另一个人，自由，爱我别走，握你的手，第一次，勇气，我不会唱歌，第二顺位，最近，听我爱你，手放开，远走高飞，你那么爱他，痴心绝对，晴天，烟花易冷，安静，黑色幽默，退后，成都，需要人陪，你不知道的事，我们的歌，爱的就是你，不可能错过你，白狐狸，安全感，江南，一千年以后，豆浆油条，翅膀，醉赤壁，爱笑的眼睛";
      songs = songs.replace("，", ",");
      songsArr = songs.split(",");

      songsArr = SongListUtils.StringArrOrder(songsArr, 0, songsArr.length - 1);
      System.out.println("一共有" + songsArr.length + "首歌！");

      flag = songsArr[(songsArr.length - 1)].length() > 14;
      System.out.println("是否有两列的歌单：" + flag);
      lengthArr = SongListUtils.countQuantity(songsArr.length, flag);
      System.out.println("一共有" + lengthArr.length + "张歌单！");

      han = new String[] { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五" };
      hasMarked = 0;
      for (i = 0; i < lengthArr.length; ++i)
      {
        String[] songsArrThis;
        InputStream fis6;
        if ((i == lengthArr.length - 1) && (flag)) {
          songsArrThis = new String[lengthArr[i] * 2];
          System.arraycopy(songsArr, hasMarked, songsArrThis, 0, Math.min(songsArr.length - hasMarked, lengthArr[i] * 2));
        } else {
          songsArrThis = new String[lengthArr[i] * 3];
          System.arraycopy(songsArr, hasMarked, songsArrThis, 0, Math.min(songsArr.length - hasMarked, lengthArr[i] * 3));
        }
        String fileName = singer + "歌单（" + han[i] + "）";
        String title = "玛雅乐团—" + singer;
        String title2 = "M  a  Y  a     Y  u  e  T  u  a  n";
        String order = "—歌曲名单（" + han[i] + "）—";

        File file = new File("./src/main/resources/songlistWidth.jpg");
        if ((i == lengthArr.length - 1) && (flag))
          file = new File("./src/main/resources/songlistTwo.jpg");

        String fileType = "jpg";
        InputStream fis = new FileInputStream(file);
        InputStream fis1 = SongListUtils.addWaterMarkTitle(fis, fileType, title);
        InputStream fis2 = SongListUtils.addWaterMarkOrder(fis1, fileType, order);

        if ((i == lengthArr.length - 1) && (flag))
          fis6 = SongListUtils.addWaterMarkTwoRows(fis2, fileType, songsArrThis, lengthArr[i]);
        else
          fis6 = SongListUtils.addWaterMarkSongs(fis2, fileType, songsArrThis, lengthArr[i]);

        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream("D:\\" + fileName + ".jpg");
        int index = 0;
        while ((index = fis6.read(bytes)) != -1) {
          downloadFile.write(bytes, 0, index);
          downloadFile.flush();
        }
        downloadFile.close();
        fis.close();
        hasMarked += lengthArr[i] * 3;
        System.out.println("第" + han[i] + "张歌单已经做完！");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}