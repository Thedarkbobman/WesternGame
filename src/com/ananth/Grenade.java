package com.ananth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Grenade {
	int x;
	int origX;
	double y,placeholder;
	double dY = -4;
	boolean direction,hit;
	public Grenade(int a, double b,boolean c) {
		x=a;
		origX = x;
		y=b;
		direction = c;
		placeholder = b;
		hit = false;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return (int)y;
	}
	public void newMove(){
		move1.start();
		
	}
	public void hit(){
		hit = true;
	}
	public boolean getHit(){
		return hit;
	}
	public Timer move1 = new Timer(10, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(Math.abs(y-placeholder) < 100){
				if(direction){
					x+=7;
				}
				else{
				x-=7;	
				}
				//y-=2;
				
				y += dY;
				
				dY += 0.1;

			}
			System.out.println("hi");
			if(y>=533-10){
				move1.stop();
			}
		}

	});
	public Timer move2 = new Timer(10, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(y<533){
				if(direction){
					x+=7;
				}
				else{
				x-=7;	
				}
				y+=2;
				if(y>=533-10){
					
					move2.stop();
				}
			}
		}

	});
}
