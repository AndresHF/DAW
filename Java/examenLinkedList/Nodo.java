package reapasoEva2;

public class Nodo {
	
	private Nodo next;
	private Punto punto;
	
	public Nodo(Punto punto){
		this.punto = punto;
	}

	public String toString(){
		
		return "NODO: " + this.punto.toString() + " ->" ;
	}

	public Nodo getNext() {
		return next;
	}

	public void setNext(Nodo next) {
		this.next = next;
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}
	
}
