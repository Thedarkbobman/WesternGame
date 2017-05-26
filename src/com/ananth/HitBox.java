package com.ananth;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class HitBox extends Rectangle2D {
	int x, y, x2, y2;
	Rectangle player, bullet, shot, alien;

	public boolean collision(int a, int b, int c, int d, boolean e,boolean f) {
		x = a;
		y = b;
		x2 = c;
		y2 = d;
		Rectangle player = null;
		if (!e) {
			 player = new Rectangle(x, y, 40, 60);

		} else if(e){
			 player = new Rectangle(x, y+30, 40, 30);
		}
		Rectangle bullet = null;
		if(f){
		 bullet = new Rectangle(x2, y2, 10, 3);
		}
		else{
		 bullet = new Rectangle(x2, y2, 10, 10);
		}

		 return player.intersects(bullet);

	}

	public boolean collision2(int a, int b, int c, int d) {
		x = a;
		y = b;
		x2 = c;
		y2 = d;
		Rectangle ship = new Rectangle(x, y, 10, 3);
		Rectangle alienShot = new Rectangle(x2, y2, 10, 3);

		return ship.intersects(alienShot);
	}
	public boolean collision3(int a, int b, int c, int d) {
		x = a;
		y = b;
		x2 = c;
		y2 = d;
		Rectangle ship = new Rectangle(x, y, 10, 10);
		Rectangle alienShot = new Rectangle(x2, y2, 10, 10);

		return ship.intersects(alienShot);
	}

	@Override
	public Rectangle2D createIntersection(Rectangle2D r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D createUnion(Rectangle2D r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int outcode(double x, double y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRect(double x, double y, double w, double h) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
