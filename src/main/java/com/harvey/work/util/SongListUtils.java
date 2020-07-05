package com.harvey.work.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * @program: Study
 * @description:
 * @author: Harvey_yang
 * @modified By：
 * @version: $
 * @create: 2020-07-05 10:23
 **/
public class SongListUtils {
    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static InputStream addWaterMarkTitle(InputStream is, String fileType, String title) {
        InputStream returnIs = null;
        try {
            BufferedImage buImage = ImageIO.read(is);
            int width = buImage.getWidth();
            int height = buImage.getHeight();

            BufferedImage bufferedImage = new BufferedImage(width, height, 4);

            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(buImage, 0, 0, width, height, null);
            Color color = new Color(215, 173, 103);
            Font font = new Font("Menlo", 1, 100);
            g.setColor(color);
            g.setFont(font);
            int textHeight = g.getFontMetrics().getHeight();
            int x = width / 2 - (getWatermarkLength(title, g) / 2);
            int y = (int) (height * 0.2D + textHeight);
            g.drawString(title, x, y);
            g.dispose();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, fileType, os);
            returnIs = new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnIs;
    }

    public static InputStream addWaterMarkOrder(InputStream is, String fileType, String title2) {
        InputStream returnIs = null;
        try {
            BufferedImage buImage = ImageIO.read(is);
            int width = buImage.getWidth();
            int height = buImage.getHeight();

            BufferedImage bufferedImage = new BufferedImage(width, height, 4);

            Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(buImage, 0, 0, width, height, null);
            Color color2 = new Color(215, 173, 103);
            Font font2 = new Font("微软雅黑", 0, 65);

            g2.setColor(color2);
            g2.setFont(font2);
            int textHeight2 = g2.getFontMetrics().getHeight();
            int x2 = width / 2 - (getWatermarkLength(title2, g2) / 2);
            int y2 = (int) (height * 0.265D + textHeight2);
            g2.drawString(title2, x2, y2);
            g2.dispose();

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, fileType, os);
            returnIs = new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnIs;
    }

    public static String[] StringArrOrder(String[] arr, int left, int right) {
        if (left > right)
            return null;
        int x = left;
        int y = right;
        String base = arr[left];
        Pattern p = Pattern.compile("[A-Za-z-' ]+");
        while (x < y) {
            while (x < y && arr[y].length() > base.length()) {
                --y;
            }
            while (x < y && arr[x].length() < base.length()) {
                ++x;
            }
            if (x < y) {
                String temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }
        }

        arr[left] = arr[x];
        arr[x] = base;

        StringArrOrder(arr, left, x - 1);
        StringArrOrder(arr, x + 1, right);
        return arr;
    }

    public static int[] countQuantity(int total, boolean flag) {
        int[] arr = new int[0];
        double count = 0.0D;
        int pieces = 0;
        int remainder = 0;

        int n = 13;  //行数
        if (flag) {
            pieces = 1 + (total - (n * 2)) / 39;
            remainder = (total - (n * 2)) % 39;
            count = 1.0D + (remainder - 2) / 3.0D;
        } else {
            pieces = total / 39;
            remainder = total % 39;
            count = remainder / 3.0D;
        }

        arr = new int[pieces];
        boolean countFlag = false;
        for (int i = 0; i < pieces; ++i) {
            arr[i] = (n + ((count > 0.0D) ? 1 : 0));
            count -= 1.0D;
        }
        return arr;
    }

    public static InputStream addWaterMarkSongs(InputStream is, String fileType, String[] songArr, int length) {
        double[] spaceArr = {0.0D, 0.0D, 5.4D, 4.4D, 3.6D, 3.0D, 2.6D, 2.35D, 2.15D, 1.9D, 1.78D, 1.6D, 1.5D, 1.4D};
        double spacing = spaceArr[(length - 1)];
        int fontSize = 75;
        int songArrLength = songArr.length;
        while (songArr[(songArrLength - 1)] == null)
            --songArrLength;

        if (songArr[(songArrLength - 1)].length() == 11) {
            fontSize = 70;
            spacing *= 1.05D;
        }
        if (songArr[(songArrLength - 1)].length() >= 12) {
            fontSize = 65;
            spacing *= 1.15D;
        }
        boolean flag = songArrLength != length * 3;
        Color color = new Color(255, 255, 255);
        Font font = new Font("宋体", 0, fontSize);
        InputStream returnIs = null;
        try {
            int x;
            int y;
            BufferedImage buImage = ImageIO.read(is);
            int width = buImage.getWidth();
            int height = buImage.getHeight();

            BufferedImage bufferedImage = new BufferedImage(width, height, 4);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(buImage, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(font);
            int textHeight = g.getFontMetrics().getHeight();
            for (int i = 0; i < length; ++i)
                if (songArr[i] != null) {
                    x = (int) (width * 0.2D - (getWatermarkLength(songArr[i], g) / 2));
                    y = (int) (height * 0.29D + textHeight * spacing * (i + 1));
                    g.drawString(songArr[i], x, y);
                }

            if (flag) {
                for (int i = 0; i < length - 1; ++i)
                    if (songArr[(i + length)] != null) {
                        x = (int) (width * 0.765D - (getWatermarkLength(songArr[(i + length)], g) / 2));
                        y = (int) (height * 0.29D + textHeight * spacing * (i + 1));
                        g.drawString(songArr[(i + length)], x, y);
                    }

                for (int i = 0; i < songArr.length - (2 * length) + 1; ++i)
                    if (songArr[(i - 1 + length * 2)] != null) {
                        x = (int) (width * 0.475D - (getWatermarkLength(songArr[(i - 1 + length * 2)], g) / 2));
                        y = (int) (height * 0.29D + textHeight * spacing * (i + 1));
                        g.drawString(songArr[(i - 1 + length * 2)], x, y);
                    }
            } else {
                for (int i = 0; i < length; ++i)
                    if (songArr[(i + length)] != null) {
                        x = (int) (width * 0.765D - (getWatermarkLength(songArr[(i + length)], g) / 2));
                        y = (int) (height * 0.29D + textHeight * spacing * (i + 1));
                        g.drawString(songArr[(i + length)], x, y);
                    }

                for (int i = 0; i < songArr.length - (2 * length); ++i)
                    if (songArr[(i + length * 2)] != null) {
                        x = (int) (width * 0.475D - (getWatermarkLength(songArr[(i + length * 2)], g) / 2));
                        y = (int) (height * 0.29D + textHeight * spacing * (i + 1));
                        g.drawString(songArr[(i + length * 2)], x, y);
                    }
            }

            g.dispose();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, fileType, os);
            returnIs = new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnIs;
    }

    public static InputStream addWaterMarkTwoRows(InputStream is, String fileType, String[] songArr, int length) {
        double[] spaceArr = {0.0D, 0.0D, 5.4D, 4.4D, 3.6D, 3.0D, 2.6D, 2.35D, 2.15D, 1.9D, 1.78D, 1.6D, 1.5D, 1.4D};
        double spacing = spaceArr[(length - 1)];
        int fontSize = 75;
        int songArrLength = songArr.length;
        while (songArr[(songArrLength - 1)] == null)
            --songArrLength;

        if (songArr[(songArrLength - 1)].length() == 11) {
            fontSize = 70;
            spacing *= 1.05D;
        }
        if (songArr[(songArrLength - 1)].length() >= 12) {
            fontSize = 65;
            spacing *= 1.15D;
        }
        Color color = new Color(255, 255, 255);
        Font font = new Font("宋体", 0, fontSize);
        InputStream returnIs = null;
        try {
            int x;
            int y;
            BufferedImage buImage = ImageIO.read(is);
            int width = buImage.getWidth();
            int height = buImage.getHeight();

            BufferedImage bufferedImage = new BufferedImage(width, height, 4);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(buImage, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(font);
            int textHeight = g.getFontMetrics().getHeight();
            for (int i = 0; i < length; ++i)
                if (songArr[i] != null) {
                    x = (int) (width * 0.3D - (getWatermarkLength(songArr[i], g) / 2));
                    y = (int) (height * 0.29D + textHeight * spacing * (i + 1));
                    g.drawString(songArr[i], x, y);
                }

            for (int i = 0; i < songArr.length - length; ++i)
                if (songArr[(i + length)] != null) {
                    x = (int) (width * 0.72D - (getWatermarkLength(songArr[(i + length)], g) / 2));
                    y = (int) (height * 0.29D + textHeight * spacing * (i + 1));
                    g.drawString(songArr[(i + length)], x, y);
                }

            g.dispose();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, fileType, os);
            returnIs = new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnIs;
    }
}
