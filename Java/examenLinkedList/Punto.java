package reapasoEva2;

import java.util.*;

public class Punto  implements Comparable<Punto>{
	private int x;
	private int y;

	public Punto() {
		this.x = 0;
		this.y = 0;
	}

	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "\tX=" + this.x + "\tY=" + this.y + "\tdist= "+this.distanciaOrigen();
	}

	
	public double distanciaOrigen(){
		return Math.round((Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2))*100))/100.0;
	}
	
	public double distanciaOtro(Punto other){
		return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
	}
	
	@Override
	public int compareTo(Punto other) {
		
		double dist1 = this.distanciaOrigen();
		double dist2 = other.distanciaOrigen();
		if(dist1 > dist2) return 1;
		if(dist1 < dist2) return -1;
		return 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
