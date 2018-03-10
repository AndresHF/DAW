package reapasoEva2;

public class Main {
	
	public static void main(String[] args){
		System.out.println(StaticArray.ordena("en un lugar de la mancha de cuyo, nombre no quiero acordarme"));
		System.out.println("--------------------------------------------");

		Lista l = new Lista();
		
		for(int i = 0; i < 10 ; i++){
			l.addStart(new Nodo(new Punto((int)(Math.random() * 20),(int) (Math.random() *20))));
		}
		l.print();
		System.out.println("--------------------------------------------");
		l.sortList(new CoorXComparator());
		l.print();
		System.out.println("--------------------------------------------");

		
		l.addAtPos(new Nodo(new Punto(0,0)), 10);
		l.print();
		System.out.println("LENGTH " +l.length());
		System.out.println("--------------------------------------------");
		int length= l.length();
		for(int i = 0; i < length; i++){
			l.removeByPos(9);
		}
		l.print();
		System.out.println("LENGTH " +l.length());


	}

}
