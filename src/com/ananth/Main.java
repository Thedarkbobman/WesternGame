package com.ananth;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
//import java.awt.Toolkit;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.*;


@SuppressWarnings("serial")
public class Main extends JPanel {

	static JFrame frame;
	static keyControl keyControl;
	static HitBox box;
	static Players p1;
	static Players p2;
	int rand;
	double r,g2,b;
	public boolean timeCheck;
	public static ArrayList<Bullets> bullets;
	public static ArrayList<Bullets> bullets2;
	public static ArrayList<Grenade> nades;
	public static ArrayList<Grenade> nades2;
	private int sunX;
	private double sunY,sundY;
	private boolean isDay,isG,isB;
	private String user;
	private String[] good;
	
	Image hat;
	public Main(JFrame jf) {
		frame = jf;
	}

	public void init(int x, int y) throws IOException {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

		rand = (2 * getHeight()) / 3;
		p1 = new Players(50, 533 - 60 +1, 1);
		p2 = new Players(750, 533 - 60 + 1, 2);
		bullets = new ArrayList();
		bullets2 = new ArrayList();
		nades = new ArrayList();
		nades2 = new ArrayList();
		keyControl = new keyControl(x, y, p1, p2, bullets, bullets2,nades,nades2);
		sunX = 0;
		sunY = 532;
		sundY = -2;
		isDay = true;
		box = new HitBox();
		hat = ImageIO.read(Main.class.getResource("/hat.png"));
		r=0;
		g2=51;
		b=51;
		isG = false;
		isB = false;
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hitTest1.run();
		hitTest2.run();
		if (isDay) {
			//setBackground(new Color(56, 212, 255));
			setBackground(new Color(0,(int)g2,(int)b));
		}
		if (!isDay) {
			setBackground(Color.black);
		}
		if (isDay) {
			g.setColor(Color.yellow);
			g.fill3DRect(sunX, (int)sunY, 100, 100, true);
		}
		if (!isDay) {
			g.setColor(Color.white);
			g.fillRoundRect(sunX, (int)sunY, 100, 100, 100,100);
		}
		
		g.setColor(new Color(87, 16, 16));
		g.fillRect(0, 533, getWidth(), getWidth());
		g.setColor(Color.red);
		if (!p1.crouched) {
			g.setColor(Color.black);
			g.drawRect(p1.getX(), p1.getY(), 40, 60);
			g.setColor(Color.red);
			g.fill3DRect(p1.getX(), p1.getY(), 40, 60, true);

		} else {
			g.setColor(Color.black);
			g.drawRect(p1.getX(), p1.getY()+30, 40, 30);
			g.setColor(Color.red);
			g.fill3DRect(p1.getX(), p1.getY()+30, 40, 30, true);

		}
		if(p1.iscrouched()){
		g.drawImage(hat, p1.getX(), p1.getY()-2, 40, 43, null);
		}
		else{
		g.drawImage(hat, p1.getX(), p1.getY()-32, 40, 43, null);
		}
		g.setColor(Color.green);
		if (!p2.crouched) {
			g.setColor(Color.black);
			g.drawRect(p2.getX(), p2.getY(), 40, 60);
			g.setColor(Color.green);
			g.fill3DRect(p2.getX(), p2.getY(), 40, 60, true);
		} else {
			g.setColor(Color.black);
			g.drawRect(p2.getX(), p2.getY()+30, 40, 30);
			g.setColor(Color.green);
			g.fill3DRect(p2.getX(), p2.getY()+30, 40, 30, true);

		}

		if(p2.iscrouched()){
		g.drawImage(hat, p2.getX(), p2.getY()-2, 40, 43, null);
		}
		else{
		g.drawImage(hat, p2.getX(), p2.getY()-32, 40, 43, null);
		}
		sunX += 1;
		sunY += sundY;
		sundY += .004;
		if (sunY >= 553) {
			sunX = 0;
			sunY = 532;
			sundY = -2;
			if (isDay) {
				isDay = false;

			} else if (!isDay) {
				isDay = true;
				isG = false;
				isB = false;
				r=0;
				g2=51;
				b=51;
			}
		}
		for (int i = 0; i < bullets.size(); i++) {
			g.setColor(Color.black);
			g.drawRect(bullets.get(i).getX(), bullets.get(i).getY(), 10, 3);
			g.setColor(Color.red);
			g.fill3DRect(bullets.get(i).getX(), bullets.get(i).getY(), 10, 3,
					true);
			bullets.get(i).move();
		}
		for (int i = 0; i < bullets2.size(); i++) {
			g.setColor(Color.black);
			g.drawRect(bullets2.get(i).getX(), bullets2.get(i).getY(), 10, 3);
			g.setColor(Color.green);
			g.fill3DRect(bullets2.get(i).getX(), bullets2.get(i).getY(), 10, 3,
					true);
			bullets2.get(i).move();
		}
		if (!keyControl.keyACheck() && !keyControl.keyDCheck()) {
			p1.drift(p1.getMomentumDir());
		}
		if (!keyControl.keylCheck() && !keyControl.keyrCheck()) {
			p2.drift(p2.getMomentumDir());
		}
		if(isDay){
			g.setColor(Color.black);
		}
		else{
			g.setColor(Color.white);
		}
		g.drawString("P1 Health: " + p1.getHealth(), 50, 50);
		g.drawString("Points: " + p1.getPoint(), 50, 70);
		g.drawString("P2 Health: " + p2.getHealth(), 1050, 50);
		g.drawString("Points: " + p2.getPoint(), 1050, 70);
		if(p1.getHealth()<=0 || p2.getHealth() <= 0){
			if(p1.getHealth()<=0){
				p2.setPoint(p2.getPoint()+1);
				p1.setHealth(100);
				p1.setX(50);
				p1.setY(533 - 60);
				p2.setHealth(100);
				p2.setX(750);
				p2.setY(533 - 60);
			}
			else{
				p1.setPoint(p1.getPoint()+1);
				p1.setHealth(100);
				p1.setX(50);
				p1.setY(533 - 60);
				p2.setHealth(100);
				p2.setX(750);
				p2.setY(533 - 60);
			}
		}
		if(p1.getY()+60> 533){
			p1.setY(533-60);
			keyControl.timer.stop();
		}
		if(p2.getY()+60> 533){
			p2.setY(533-60);
			keyControl.timer2.stop();
		}

		for (int i = 0; i < nades.size(); i++) {
			g.setColor(Color.black);
			g.drawRoundRect(nades.get(i).getX(), nades.get(i).getY(), 10, 10,10,10);
			g.setColor(Color.red);
			g.fillRoundRect(nades.get(i).getX(), nades.get(i).getY(), 10, 10,10,10);

			System.out.println(nades.get(i).getY());
		}
		for (int i = 0; i < nades2.size(); i++) {
			g.setColor(Color.black);
			g.drawRoundRect(nades2.get(i).getX(), nades2.get(i).getY(), 10, 10,10,10);
			g.setColor(Color.green);
			g.fillRoundRect(nades2.get(i).getX(), nades2.get(i).getY(), 10, 10, 10, 10);

		}

		if(!isG){
		if(g2<255){
			g2+=.4;
		}
		}
		else{
			if(g2>51){
				g2-=.4;
			}
		}
		if(!isB){
		if(b<255){
			b+=.4;
		}
		}
		else{
			if(b>51){
				b-=.4;
			}
		}

		if(g2>=255){
			isG=true;
		}
		if(b>=255){
			isB = true;
		}
		if(g2 <= 51){
			isG = false;
			g2=51;
			b=51;
		}
		if(b<=51){
			isG = false;
			g2=51;
			b=51;
		}
		System.out.println("0," + g2 + "," + b);
	}

	public static void main(String[] args) throws InterruptedException,
			IOException {
		JFrame frame = new JFrame("Fight");
		final Main game = new Main(frame);
		game.init(1200, 800);

		keyControl.addKeyListener(keyControl);
		keyControl.setFocusable(true);
		frame.add(keyControl);

		frame.add(game);
		frame.setSize(1200, 800);
		frame.setResizable(false);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBackground(Color.black);
		
		while (true) {

			keyControl.tick();

			game.repaint();
			Thread.sleep(30);
		}

	}
	public static Thread hitTest1 = new Thread(new Runnable(){
		//collisions between p2 and bullets from p1
		@Override
		public void run() {
			if(bullets.size()>0){
				int max = bullets.size();
				for(int i = 0; i < max; i ++){
					max=bullets.size();
					if(!p2.iscrouched()){
					if(box.collision(p2.getX(), p2.getY(), bullets.get(i).getX(), bullets.get(i).getY(), false,true) && i < max){
						p2.hit();
						bullets.remove(i);
						max = bullets.size();
					}
					
					}
					else{
						if(box.collision(p2.getX(), p2.getY(), bullets.get(i).getX(),  bullets.get(i).getY(), true,true)&&i<max){
							p2.hit();
							bullets.remove(i);
							max = bullets.size();
						}
					}
					if(i<max){
					if(bullets.get(i).getX()>frame.getWidth()||bullets.get(i).getX()<0){
						bullets.remove(i);
						max = bullets.size();
					}
					}
					break1:if(bullets2.size()>0 && bullets.size()>0&&i<max){
						for(int j = 0; j < bullets2.size(); j++){
							if(bullets.size()>0&&bullets2.size()>0&&i<max){
								if(box.collision2(bullets.get(i).getX(), bullets.get(i).getY(), bullets2.get(j).getX(), bullets2.get(j).getY())){
									bullets.remove(i);
									bullets2.remove(j);
								max = bullets.size();
								
							}
						}
					}
					}

				}
			}
			
			if(bullets2.size()>0){
				int max = bullets2.size();

				for(int i = 0; i < max; i ++){
					max = bullets2.size();
					if(!p1.iscrouched()){
					if(box.collision(p1.getX(), p1.getY(), bullets2.get(i).getX(),  bullets2.get(i).getY(),false,true)&&i<max){
						p1.hit();
						bullets2.remove(i);
						max = bullets2.size();
					}
					}
					else{
						if(box.collision(p1.getX(), p1.getY(), bullets2.get(i).getX(),  bullets2.get(i).getY(),true,true)&&i<max){
							p1.hit();
							bullets2.remove(i);
							max = bullets2.size();
						}
					}
					if(i<max){
					if(bullets2.get(i).getX()>frame.getWidth()||bullets2.get(i).getX()<0){
						bullets2.remove(i);
						max = bullets2.size();
					}}
					break2:if(bullets2.size()>0 &&   bullets.size()>0&&i<max){
						for(int j = 0; j < bullets.size(); j++){
							if(bullets.size()>0&&bullets2.size()>0&&i<max){
							if(box.collision2(bullets.get(j).getX(), bullets.get(j).getY(), bullets2.get(i).getX(), bullets2.get(i).getY())){
								bullets.remove(j);
								bullets2.remove(i);
								max = bullets2.size();
								
							}
						}
					}
					}
					
				}}
			
		}
		
	});
	public Thread hitTest2 = new Thread(new Runnable(){
		//collisions between p1 and bullets from p2
		@Override
		public void run() {
			if(nades.size()>0){
				int max = nades.size();

				for(int i = 0; i < max; i ++){
					max = nades.size();
					if(!p2.iscrouched()){
					if(box.collision(p2.getX(), p2.getY(), nades.get(i).getX(),  nades.get(i).getY(),false,false)&&i<max){
						p2.gHit();
						nades.remove(i);
						max = nades.size();
					}
					}
					else{
						if(box.collision(p2.getX(), p2.getY(), nades.get(i).getX(),  nades.get(i).getY(),true,false)&&i<max){
							p2.gHit();
							nades.remove(i);
							max = nades.size();
						}
					}
					if(i<max){
					if(nades.get(i).getX()>frame.getWidth()||nades.get(i).getX()<0||nades.get(i).getY()>=533-10){
						nades.remove(i);
						max = nades.size();
					}}
					break2:if(nades.size()>0 &&   nades2.size()>0&&i<max){
						for(int j = 0; j < nades2.size(); j++){
							if(nades.size()>0&&nades2.size()>0&&i<max){
							if(box.collision2(nades.get(i).getX(), nades.get(i).getY(), nades2.get(j).getX(), nades2.get(j).getY())){
								nades.remove(i);
								nades2.remove(j);
								max = nades.size();
								
							}
						}
					}
					}
					
				}}
			if(nades2.size()>0){
				int max = nades2.size();

				for(int i = 0; i < max; i ++){
					max = nades2.size();
					if(!p1.iscrouched()){
					if(box.collision(p1.getX(), p1.getY(), nades2.get(i).getX(),  nades2.get(i).getY(),false,false)&&i<max){
						p1.gHit();
						nades2.remove(i);
						max = nades2.size();
					}
					}
					else{
						if(box.collision(p1.getX(), p1.getY(), nades2.get(i).getX(),  nades2.get(i).getY(),true,false)&&i<max){
							p1.gHit();
							nades2.remove(i);
							max = nades2.size();
						}
					}
					if(i<max){
					if(nades2.get(i).getX()>frame.getWidth()||nades2.get(i).getX()<0||nades2.get(i).getY()>=533-10){
						nades2.remove(i);
						max = nades2.size();
					}}
					break2:if(nades2.size()>0 &&   nades.size()>0&&i<max){
						for(int j = 0; j < nades.size(); j++){
							if(nades.size()>0&&nades2.size()>0&&i<max){
							if(box.collision2(nades.get(j).getX(), nades.get(j).getY(), nades2.get(i).getX(), nades2.get(i).getY())){
								nades.remove(j);
								nades2.remove(i);
								max = nades2.size();
								
							}
						}
					}
					}
					
				}}
		}
		
	});
}
