package com.xxn.butils;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class LocationTool {
	public static  boolean checkWithJdkGeneralPath(Point2D.Double point,List<Point2D.Double> polygon){
		GeneralPath p = new GeneralPath();
		Point2D.Double first = polygon.get(0);
		p.moveTo(first.x, first.y);
		for(Point2D.Double d : polygon){
			p.lineTo(d.x, d.y);
		}
		p.lineTo(first.x, first.y);
		p.closePath();
		return p.contains(point);
	}
	public static void gouzao(){
		Point2D.Double point = new Point2D.Double(118.130541, 24.444345);
		long time1 = System.currentTimeMillis(); 
		List<Point2D.Double> polygon = new ArrayList<>();
		polygon.add(new Point2D.Double(118.130137, 24.445012));
		polygon.add(new Point2D.Double(118.129445, 24.444112));
		polygon.add(new Point2D.Double(118.130896, 24.444614));
		long time2 = 0;
		if(checkWithJdkGeneralPath(point,polygon)){
			time2 = System.currentTimeMillis();
			System.out.println("在内部");
			
		}
		else{
			time2 = System.currentTimeMillis();
			System.out.println("不在内部");
		}
		System.out.println(time2-time1);
	}
	
	public static boolean inSchool(Point2D.Double point,List<Point2D.Double> polygon){
		if(checkWithJdkGeneralPath(point,polygon)){
			System.out.println("在内部");
			return true;
		}
		else{
			System.out.println("不在内部");
			return false;
		}
	}
}
