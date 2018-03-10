package reapasoEva2;

import java.util.Comparator;

public class NaturalComparator implements Comparator<Punto>{

	@Override
	public int compare(Punto p1, Punto p2) {
		return p1.compareTo(p2);
	}

}
