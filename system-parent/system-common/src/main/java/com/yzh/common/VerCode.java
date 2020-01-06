package com.yzh.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VerCode {
	private BufferedImage img = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);
	Color[] colors = new Color[]{Color.RED,Color.blue,Color.YELLOW,Color.green,Color.pink,Color.gray,Color.orange};
	public Map<BufferedImage,String> createGrap(){
		
		String randWord = "";
		
		Random random = new Random();
		List<Integer> words = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			words.add(random.nextInt(10));
			
			randWord += words.get(i);
		}
		
		Graphics2D gra = img.createGraphics();
		
		gra.setColor(Color.white);
		
		gra.fillRect(0, 0, 100, 50);
		
		gra.setFont(new Font("微软雅黑",Font.ITALIC|Font.BOLD,20));
		
		for(int i = 0; i <5 ;i++){
			gra.setColor(colors[random.nextInt(colors.length)]);
			gra.drawLine(0, random.nextInt(51), 100, random.nextInt(51));
		}
		for(int i = 0; i <10 ;i++){
			gra.setColor(colors[random.nextInt(colors.length)]);
			gra.drawOval(random.nextInt(101), random.nextInt(51), 2, 2);
		}
		for(int i = 0; i < words.size();i++){
			gra.setColor(colors[random.nextInt(colors.length)]);
			gra.drawString(words.get(i)+"", i*20, 35+(random.nextInt(11)-5));
		}
		
		
		Map<BufferedImage,String> map = new HashMap<>();
		map.put(img, randWord);
		
		return map;
		
	}
}
