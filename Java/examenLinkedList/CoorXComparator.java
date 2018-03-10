package reapasoEva2;

import java.util.Comparator;

public class CoorXComparator implements Comparator<Punto>{

	@Override
	public int compare(Punto p1, Punto p2) {
		
		if(p1.getX() > p2.getX()) return 1;
		if(p1.getX() < p2.getX()) return -1;
		return 0;
	}

}
