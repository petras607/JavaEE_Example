package pieniazek.srvr;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.axis.encoding.Base64;

public class ImgProc
{
	BufferedImage img;
	
	private boolean openImg(String path)
	{
		try
		{
			img = ImageIO.read(new File(path));
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private String saveImg(String path, int mode)
	{
		int ext_pos = path.length() - 1;
		
		while (path.charAt(ext_pos) != '.')
			ext_pos--;
		
		String n_path = path.substring(0, ext_pos) + "_" + mode + ".png";
		
		try
		{
			ImageIO.write(img, "png", new File(n_path));
			
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "";
		}
		
		return n_path;
	}
	
	private void negative()
	{
		for (int x = 0; x < img.getWidth(); x++)
		{
			for (int y = 0; y < img.getHeight(); y++)
			{
				// Odwracamy kolor
				img.setRGB(x, y, 0xFFFFFFFF - img.getRGB(x, y) + 0xFF000000);
			}
		}
	}
	
	private void flipHorizontal()
	{
		for (int x = 0; x < img.getWidth() / 2; x++)
		{
			for (int y = 0; y < img.getHeight(); y++)
			{
				int pom = img.getRGB(x, y);
				img.setRGB(x, y, img.getRGB(img.getWidth() - x - 1, y));
				img.setRGB(img.getWidth() - x - 1, y, pom);
			}
		}
	}
	
	private void flipVertical()
	{
		for (int x = 0; x < img.getWidth(); x++)
		{
			for (int y = 0; y < img.getHeight() / 2; y++)
			{
				int pom = img.getRGB(x, y);
				img.setRGB(x, y, img.getRGB(x, img.getHeight() - y - 1));
				img.setRGB(x, img.getHeight() - y - 1, pom);
			}
		}
	}
	
	private void makeGray()
	{
		for (int x = 0; x < img.getWidth(); x++)
		{
			for (int y = 0; y < img.getHeight(); y++)
			{
				Color rgb = new Color(img.getRGB(x, y));
				int l = (int)Math.sqrt((rgb.getRed() * rgb.getRed() + rgb.getGreen() * rgb.getGreen() + rgb.getBlue() * rgb.getBlue()) / 3);
				rgb = new Color(l, l, l, rgb.getAlpha());
				img.setRGB(x, y, rgb.getRGB());
			}
		}
	}
	
	public String startProc(String path, int mode)
	{
		if (openImg(path) == false)
		{
			return "";
		}
		
		switch (mode)
		{
		case 1:
			negative();
			break;
		case 2:
			flipHorizontal();
			break;
		case 3:
			flipVertical();
			break;
		case 4:
			makeGray();
			break;
		}
		
		saveImg(path, mode);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, "png", outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encodedImage = "data:image/png;base64," + Base64.encode(outputStream.toByteArray());
		return encodedImage;
	}
}
