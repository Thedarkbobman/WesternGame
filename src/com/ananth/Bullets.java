package com.ananth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Bullets {
	int x;
	double y;
	boolean direction;
	public Bullets(int a, double b,boolean c) {
		x=a;
		y=b;
		direction = c;
	}
	public void move(){
		if(direction){
		x+=30;
		}
		else{
		x-=30;
		}
		y+=.5;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return (int)y;
	}
	public boolean direction(){
		return direction;
	}

}
