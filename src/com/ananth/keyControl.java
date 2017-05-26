package com.ananth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class keyControl extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	private int width, height, count, count2, count3, count4, moMove;
	private boolean[] keys = new boolean[65536];
	private Players p1, p2;
	private ArrayList<Bullets> bullets, bullets2;
	private ArrayList<Grenade> nades, nades2;
	private boolean check, aCheck, dCheck, leftCheck, rightCheck;
	public boolean timerRun1, timerRun2;

	public keyControl(int w, int h, Players a, Players b, ArrayList<Bullets> c,
			ArrayList<Bullets> d, ArrayList<Grenade> e,
			ArrayList<Grenade> f) {
		width = w;
		height = h;
		p1 = a;
		p2 = b;
		count = 0;
		check = false;
		timerRun1 = false;
		timerRun2 = false;
		bullets = c;
		bullets2 = d;
		aCheck = false;
		dCheck = false;
		leftCheck = false;
		rightCheck = false;
		nades = e;
		nades2 = f;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void tick() {
		if (keys[KeyEvent.VK_A]) {
			p1.moveLeft();
			if (p1.getMomentumDir()) {
				p1.increaseMomentum(1);
			} else {
				p1.decreaseMomentum(2);
			}
			if (p1.getMomentum() == 0 && !p1.getMomentumDir()) {
				p1.setMomentumDir(true);
			}
			aCheck = true;
			// System.out.println(p1.getMomentum()+" " + p1.getMomentumDir()+
			// "");
		} else {
			aCheck = false;
		}

		if (keys[KeyEvent.VK_D]) {
			p1.moveRight();
			if (!p1.getMomentumDir()) {
				p1.increaseMomentum(1);
			} else {
				p1.decreaseMomentum(2);
			}
			if (p1.getMomentum() == 0 && p1.getMomentumDir()) {
				p1.setMomentumDir(false);
			}
			dCheck = true;
			// System.out.println(p1.getMomentum()+" " + p1.getMomentumDir()+
			// "");
		} else {
			dCheck = false;
		}
		if (keys[KeyEvent.VK_W]) {
			timerRun1 = true;

			timer.start();

		}
		if (keys[KeyEvent.VK_S]) {
			p1.moveDown();
		} else {
			p1.noCrouch();
		}
		if (keys[KeyEvent.VK_SPACE]) {
			if (p1.ableShot) {
				if (p1.getX() < p2.getX()) {
					Bullets b = null;
					if (p1.iscrouched()) {
						b = new Bullets(p1.getX() + 40, p1.getY() + 40, true);
					} else {
						b = new Bullets(p1.getX() + 40, p1.getY() + 20, true);
					}

					bullets.add(b);
				} else {
					Bullets b = null;
					if (p1.iscrouched()) {
						b = new Bullets(p1.getX() , p1.getY() + 40, false);
					} else {
						b = new Bullets(p1.getX() , p1.getY() + 20, false);
					}
					bullets.add(b);
				}

				p1.notAbleShot();
				p1.runTimer();
			}
		}
		if (keys[KeyEvent.VK_ENTER]) {

			if (p2.ableShot) {
				if (p2.getX() < p1.getX()) {
					Bullets b = null;
					if (p1.iscrouched()) {
						b = new Bullets(p2.getX() + 40, p2.getY() + 40, true);
					} else {
						b = new Bullets(p2.getX() + 40, p2.getY() + 20, true);
					}
					bullets2.add(b);
				} else {
					Bullets b = null;
					if (p1.iscrouched()) {
						b = new Bullets(p2.getX(), p2.getY() + 40, false);
					} else {
						b = new Bullets(p2.getX(), p2.getY() + 20, false);
					}

					bullets2.add(b);
				}
				p2.notAbleShot();
				p2.runTimer();
			}

		}
		if (keys[KeyEvent.VK_LEFT]) {
			p2.moveLeft();
			if (p2.getMomentumDir()) {
				p2.increaseMomentum(1);
			} else {
				p2.decreaseMomentum(2);
			}
			if (p2.getMomentum() == 0 && !p2.getMomentumDir()) {
				p2.setMomentumDir(true);
			}
			leftCheck = true;
			// System.out.println(p1.getMomentum()+" " + p1.getMomentumDir()+
			// "");
		} else {
			leftCheck = false;
		}
		if (keys[KeyEvent.VK_RIGHT]) {
			p2.moveRight();
			if (!p2.getMomentumDir()) {
				p2.increaseMomentum(1);
			} else {
				p2.decreaseMomentum(2);
			}
			if (p2.getMomentum() == 0 && p2.getMomentumDir()) {
				p2.setMomentumDir(false);
			}
			rightCheck = true;
			// System.out.println(p1.getMomentum()+" " + p1.getMomentumDir()+
			// "");
		} else {
			rightCheck = false;
		}
		if (keys[KeyEvent.VK_UP]) {
			timerRun2 = true;
			timer2.start();
		}
		if (keys[KeyEvent.VK_DOWN]) {
			p2.moveDown();
		} else {
			p2.noCrouch();
		}
		if (keys[KeyEvent.VK_DELETE]) {

		}
		if(keys[KeyEvent.VK_SHIFT]){
			if (p1.canNade) {
				if (p1.getX() < p2.getX()) {
					Grenade b = null;
					if (p1.iscrouched()) {
						b = new Grenade(p1.getX() + 40, p1.getY() + 40, true);
					} else {
						b = new Grenade(p1.getX() + 40, p1.getY() + 20, true);
					}

					nades.add(b);
					b.newMove();
				} else {
					Grenade b = null;
					if (p1.iscrouched()) {
						b = new Grenade(p1.getX() , p1.getY() + 40, false);
					} else {
						b = new Grenade(p1.getX() , p1.getY() + 20, false);
					}
					nades.add(b);
					b.newMove();
				}
				
				p1.notCanNade();
				p1.runTimer2();
			}
		}
		if(keys[KeyEvent.VK_CONTROL]){
			if (p2.canNade) {
				if (p2.getX() < p1.getX()) {
					Grenade b = null;
					if (p2.iscrouched()) {
						b = new Grenade(p2.getX() + 40, p2.getY() + 40, true);
					} else {
						b = new Grenade(p2.getX() + 40, p2.getY() + 20, true);
					}

					nades2.add(b);
					b.newMove();
				} else {
					Grenade b = null;
					if (p2.iscrouched()) {
						b = new Grenade(p2.getX() , p2.getY() + 40, false);
					} else {
						b = new Grenade(p2.getX() , p2.getY() + 20, false);
					}
					nades2.add(b);
					b.newMove();
				}

				p2.notCanNade();
				p2.runTimer2();
			}
		}

	}

	public int xLoc() {
		return 1;
	}

	public int yLoc() {
		return 1;
	}

	public Timer timer = new Timer(10, new ActionListener() {
		// count3C();
		@Override
		public void actionPerformed(ActionEvent e) {
			if (count < 25) {
				p1.setY(p1.getY() - 3);
			} else if (count > 25 && count <= 49) {
				p1.setY(p1.getY() + 3);
			} else if (count == 50) {
				p1.setY(p1.getY() + 1);
				count = 0;
				timerRun1 = false;
				timer.stop();
			}


			/*
			 * if(count3-p1.getY() == 50){ check = true; } else if(!check){
			 * p1.setY(p1.getY()-1); } else if(check){ p1.setY(p1.getY()+1); }
			 * if(p1.getY()==count3&& check == true){ check = false;
			 * timer.stop(); }
			 */
			count++;

		}

	});
	public Timer timer2 = new Timer(10, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (count2 < 25) {
				p2.setY(p2.getY() - 3);
			} else if (count2 > 25 && count2 <= 49) {
				p2.setY(p2.getY() + 3);
			} else if (count2 == 50) {
				p2.setY(p2.getY() + 1);
				count2 = 0;
				timerRun2 = false;
				timer2.stop();
			}

			/*
			 * if(count3-p1.getY() == 50){ check = true; } else if(!check){
			 * p1.setY(p1.getY()-1); } else if(check){ p1.setY(p1.getY()+1); }
			 * if(p1.getY()==count3&& check == true){ check = false;
			 * timer.stop(); }
			 */
			count2++;

		}

	});
	public Timer timer3 = new Timer(0, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (moMove % 2 == 1) {
				moMove--;
			}
			if (moMove > 0) {
				p1.decreaseMomentum(2);
			}

		}

	});

	public boolean timerRun1() {
		return timerRun1;
	}

	public boolean timerRun2() {
		return timerRun2;
	}

	public boolean keyACheck() {
		return aCheck;
	}

	public boolean keyDCheck() {
		return dCheck;
	}

	public boolean keylCheck() {
		return leftCheck;
	}

	public boolean keyrCheck() {
		return rightCheck;
	}

}
