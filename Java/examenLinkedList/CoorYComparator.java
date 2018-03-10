package reapasoEva2;
import java.util.*;

public class CoorYComparator implements Comparator<Punto>{

	@Override
	public int compare(Punto p1, Punto p2) {
		if(p1.getY() > p2.getY()) return 1;
		if(p1.getY() < p2.getY()) return -1;
		return 0;
	}

}
