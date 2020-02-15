package com.harvey.work.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;

public class AddWaterMark {
	public InputStream addWaterMarkTitle(InputStream is, String fileType, String title) {
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

	public InputStream addWaterMarkTitle2(InputStream is, String fileType, String title2) {
		InputStream returnIs = null;
		try {
			BufferedImage buImage = ImageIO.read(is);
			int width = buImage.getWidth();
			int height = buImage.getHeight();

			BufferedImage bufferedImage = new BufferedImage(width, height, 4);

			Graphics2D g2 = bufferedImage.createGraphics();
			g2.drawImage(buImage, 0, 0, width, height, null);
			Color color2 = new Color(215, 173, 103);
			Font font2 = new Font("宋体", 0, 40);
			g2.setColor(color2);
			g2.setFont(font2);
			int textHeight2 = g2.getFontMetrics().getHeight();
			int x2 = width / 2 - (getWatermarkLength(title2, g2) / 2);
			int y2 = (int) (height * 0.24D + textHeight2);
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

	public InputStream addWaterMarkOrder(InputStream is, String fileType, String title2) {
		InputStream returnIs = null;
		try {
			BufferedImage buImage = ImageIO.read(is);
			int width = buImage.getWidth();
			int height = buImage.getHeight();

			BufferedImage bufferedImage = new BufferedImage(width, height, 4);

			Graphics2D g2 = bufferedImage.createGraphics();
			g2.drawImage(buImage, 0, 0, width, height, null);
			Color color2 = new Color(215, 173, 103);
			Font font2 = new Font("宋体", 0, 70);
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

	public InputStream addWaterMarkSongs(InputStream is, String fileType, String[] songs, boolean orderFlag) {
		String string;
		LinkedList two = new LinkedList();
		LinkedList three = new LinkedList();
		LinkedList four = new LinkedList();
		LinkedList five = new LinkedList();
		LinkedList six = new LinkedList();
		LinkedList seven = new LinkedList();
		LinkedList eight = new LinkedList();
		LinkedList nine = new LinkedList();
		LinkedList ten = new LinkedList();
		LinkedList eleven = new LinkedList();
		LinkedList twelve = new LinkedList();
		LinkedList thirteen = new LinkedList();
		LinkedList fourteen = new LinkedList();
		boolean flag = false;
		int ll = songs.length;
		System.out.println(ll);
		if(ll%3!=0) {
			flag = true;
		}
		ll = (int) Math.ceil(ll / 3.0D);
		System.out.println(ll);
		ArrayList songArr = new ArrayList();
		if (orderFlag) {
			String[] arrayOfString;
			int j = (arrayOfString = songs).length;
			for (int i = 0; i < j; ++i) {
				string = arrayOfString[i];
				int length = string.length();
				String regex1 = ".*[a-zA-z0-9].*";
				boolean result = string.matches(regex1);
				if (result)
					length = (int) Math.ceil(length / 1.80D);

				if (length == 1)
					two.addFirst(string);
				if (length == 2)
					two.add(string);
				if (length == 3)
					three.add(string);
				if (length == 4)
					four.add(string);

				if (length == 5)
					five.add(string);

				if (length == 6)
					six.add(string);

				if (length == 7)
					seven.add(string);

				if (length == 8)
					eight.add(string);

				if (length == 9)
					nine.addFirst(string);

				if (length == 10)
					ten.add(string);

				if (length == 11)
					eleven.add(string);

				if (length == 12)
					twelve.addFirst(string);

				if (length == 13)
					thirteen.add(string);

				if (length == 14)
					fourteen.addFirst(string);

				if (length > 14)
					fourteen.add(string);
			}
			Iterator localIterator;
			for (localIterator = two.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = three.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = four.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = five.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = six.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = seven.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = eight.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = nine.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = ten.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = eleven.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = twelve.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = thirteen.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
			for (localIterator = fourteen.iterator(); localIterator.hasNext();) {
				string = (String) localIterator.next();
				songArr.add(string);
			}
		}
		for (Iterator index = songArr.iterator(); index.hasNext();) {
			string = (String) index.next();
			System.out.print(string + ",");
		}
		System.out.println();
		Color color = new Color(255, 255, 255);
		Font font = new Font("宋体", 0, 80);
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
			for (int i = 0; i < ll; ++i) {
				x = (int) (width * 0.2D - (getWatermarkLength((String) songArr.get(i), g) / 2));
				y = (int) (height * 0.29D + textHeight * 1.8500 * (i + 1));
				g.drawString((String) songArr.get(i), x, y);
			}
			if(flag) {
				for (int i = 0; i < ll-1; ++i) {
					x = (int) (width * 0.765D - (getWatermarkLength((String) songArr.get(i + ll), g) / 2));
					y = (int) (height * 0.29D + textHeight * 1.8500 * (i + 1));
					g.drawString((String) songArr.get(i + ll), x, y);
				}
				for (int i = 0; i < songArr.size() - (2 * ll)+1; ++i) {
					x = (int) (width * 0.475D - (getWatermarkLength((String) songArr.get(i-1 + ll * 2), g) / 2));
					y = (int) (height * 0.29D + textHeight * 1.8500 * (i + 1));
					g.drawString((String) songArr.get(i-1 + ll * 2), x, y);
				}
			}else {
				for (int i = 0; i < ll; ++i) {
					x = (int) (width * 0.765D - (getWatermarkLength((String) songArr.get(i + ll), g) / 2));
					y = (int) (height * 0.29D + textHeight * 1.8500 * (i + 1));
					g.drawString((String) songArr.get(i + ll), x, y);
				}
				for (int i = 0; i < songArr.size() - (2 * ll); ++i) {
					x = (int) (width * 0.475D - (getWatermarkLength((String) songArr.get(i + ll * 2), g) / 2));
					y = (int) (height * 0.29D + textHeight * 1.8500 * (i + 1));
					g.drawString((String) songArr.get(i + ll * 2), x, y);
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

	public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
		return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
	}
}
