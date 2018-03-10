package reapasoEva2;

import java.util.Comparator;

public class Lista {

	private Nodo head;

	public Lista() {
		this.head = null;
	}

	public Lista(Nodo head) {
		this.head = head;
	}

	public void print() {

		Nodo p = this.head;
		int pos = 0;
		if (p == null)
			System.out.println("Empty list...");
		while (p != null) {
			System.out.print("POS " + pos + " " + p.toString());
			pos ++;
			if (p.getNext() == null)
				System.out.print(" LAST NODE");
			p = p.getNext();
			System.out.println();
		}
		
	}

	public void addStart(Nodo p) {
		if (this.head != null) {
			p.setNext(this.head);
			this.head = p;
		} else {
			this.head = p;
		}
	}

	public void addStart(Punto point) {
		Nodo p = new Nodo(point); 
		if (this.head != null) {
			p.setNext(this.head);
			this.head = p;
		} else {
			this.head = p;
		}
	}

	public void addEnd(Nodo last) {

		if(this.head == null) this.head = last;
		else{
			Nodo p = this.head;
			while(p.getNext() != null){
				p = p.getNext();
			}
			p.setNext(last);
		}
	}
	public void addEnd(Punto point) {

		if(this.head == null) this.head = new Nodo(point);
		else{
			Nodo p = this.head;
			while(p.getNext() != null){
				p = p.getNext();
			}
			p.setNext(new Nodo(point));
		}
	}
	public void sortList(Comparator<Punto> c){
		
		Nodo p = this.head;
		while(p != null){
			Nodo next = p.getNext();
			while(next != null){
				if(c.compare(p.getPunto(), next.getPunto()) > 0){
					Punto aux = p.getPunto();
					p.setPunto(next.getPunto());
					next.setPunto(aux);
				}
				next = next.getNext();
			}

			p = p.getNext();
		}
	}
	public int length(){
		Nodo p = this.head;
		int length = 0;
		while(p != null){
			length ++;
			p = p.getNext();
		}
		return length;
	}
	public void addAtPos(Nodo newNode, int pos){
		
		if(pos < 0 || pos > this.length()){
			System.out.println("Invalid pos");
		}else if(pos == this.length()){
			addEnd(newNode);
		}else if(pos == 0){
			addStart(newNode);
		}else{
			int counter = 1;
			Nodo p = this.head;
			while(counter < pos){
				counter ++;
				p = p.getNext();
			}
			newNode.setNext(p.getNext());
			p.setNext(newNode);
		}
		
	}
	
	public void removeFirst(){
		if(this.head.getNext() == null){
			this.head = null;
		}else if(this.head == null){
			System.out.println("Empty list...");
		}else{
			this.head = this.head.getNext();
		}
	}
	public void removeLast(){
		if(this.head.getNext() == null){
			this.head = null;
		}else if(this.head == null){
			System.out.println("Empty list...");
		}else{
			Nodo p = this.head;
			while(p.getNext().getNext() != null){
				p = p.getNext();
			}
			p.setNext(null);
		}
		
	}

	public void removeByPos(int pos){
		
		if(pos < 0 || pos > this.length() - 1){
			System.out.println("Invalid position...");
		}else if(pos == 0){
			this.setHead(this.head.getNext());
		}else{
			int counter = 1;
			Nodo p = this.head;
			while(counter < pos && p.getNext().getNext() != null){
				p = p.getNext();
			}
			p.setNext(p.getNext().getNext());
		}
	}
	public void emptyList(){
		this.head = null;
	}
	public Nodo getHead() {
		return head;
	}

	public void setHead(Nodo head) {
		this.head = head;
	}
	
}













