package com.ananth;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.*;
import javax.swing.Timer;
public class Players {
int x,y,move,momentum,number,health,points;
boolean crouched,ableShot,momentumDir,canNade;

public Players(int ox,int oy,int n){
	x=ox;
	y=oy;

	ableShot = true;
	number = n;
health = 100;
points = 0;
canNade = true;
}
public void moveLeft(){
	if(x>0 && momentum>0 && !momentumDir){
	momentum-=2;
	x++;

	}
	else if(x>0 && momentum>0 && momentumDir){
		x-=momentum/2;
	}
	if(momentum==-1){
		momentum = 0;
	}
}
public void moveUp(){
	y--;
}
public void moveRight(){

	if(x<1200-40 && momentum>0 && momentumDir){
	momentum-=2;
	x--;
	}
	else if(x<1200-40 && momentum>0 && !momentumDir){
		x+=momentum/2;
	}
	if(momentum==-1){
		momentum = 0;
	}
}
public void moveDown(){
	crouched = true;
}
public void setX(int x2){
	x= x2;
}
public void setY(int y2){
	y=y2;
}

public int getX(){
	return x;
}
public int getY(){
	return y;
}
public boolean iscrouched(){
	return crouched;
}

public void noCrouch(){
	crouched = false;
}
public boolean canShoot(){
	return ableShot;
}
public void notAbleShot(){
	ableShot = false;
}
public boolean canNade(){
	return canNade;
}
public void notCanNade(){
	canNade = false;
}
private Timer timer = new Timer(750, new ActionListener(){
	//  count3C();
	@Override
	public void actionPerformed(ActionEvent e) {
		ableShot=true;
		
	}
	
});
private Timer timer2 = new Timer(1500, new ActionListener(){
	//  count3C();
	@Override
	public void actionPerformed(ActionEvent e) {
		canNade=true;
		
	}
	
});
public void runTimer(){
	timer.start();
}
public void runTimer2(){
	timer2.start();
}
public void increaseMomentum(int x){
	if(momentum<20){
	momentum+=x;
	}
}
public void decreaseMomentum(int x){
	if(momentum>0){
		if(momentum<=0){
			momentum = 0;
		}
		else{
			momentum-=x;
		}
	}
}
public int getMomentum(){
	return momentum;
}
public void setMomentumDir(boolean b){
	momentumDir=b;
}
public boolean getMomentumDir(){
	return momentumDir;
}
public void drift(boolean b){
	if(b && momentum >0&&x>0){
		x-=2;
	}
	else if (!b && momentum > 0&&x<1200-40){
		x+=2;
	}
	decreaseMomentum(1);
}
public void hit(){
	health-=5;
}
public void gHit(){
	health-=10;
}
public int getHealth(){
	return health;
}
public void setHealth(int x){
	health = x;
}
public void setPoint(int x){
	points = x;
}
public int getPoint(){
	return points;
}
}
