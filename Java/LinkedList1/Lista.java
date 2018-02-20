package eva2.linkedlist;

public class Lista {
	
	private Nodo head;
	
	public Lista(){
		this.head = null;
	}
	
	public Lista(Nodo head){
		this.head = head;
	}

	public void printList(){
		Nodo n = this.getHead();
		while(n != null){
			System.out.println(n.toString());
			n = n.getNext();
		}
	}
	
	public void addStart(int info){
		Nodo nuevo = new Nodo(info);
		if(this.head == null){
			this.head = nuevo;
		}else{
			nuevo.setNext(this.head);
			this.head = nuevo;
		}

	}
	
	public void addEnd(int info){
		
		Nodo n = this.head;
		
		while(n.getNext() != null){
			n = n.getNext();
		}
		
		n.setNext(new Nodo(info));
	}
	
	public void addByIndex(int index, int info){
		
		if(index < 0 || index > this.length()) 
			System.out.println("¡¡OUT OF BOUNDS!! allowed positions between 0 and " + this.length() + " included.");
		else if(index == 0) 
			addStart(info);
		else if(index == length()) 
			addEnd(info);
		else{
			Nodo nuevo = new Nodo(info);
			nuevo.setNext(getByIndex(index));
			getByIndex(index - 1).setNext(nuevo);
		}
	}
	public Nodo getByIndex(int index) throws NullPointerException{

		int counter = 0;
		Nodo n = this.head;
		
		while(n != null && counter != index){
			n = n.getNext();
			counter ++;
		}
		return n;
	}
	
	public int length(){
		
		Nodo n = this.head;
		int counter = 0;
		
		while(n != null){
			n = n.getNext();
			counter ++;
		}
		return counter;
	}
	public Nodo getHead() {
		return head;
	}

	public void setHead(Nodo head) {
		this.head = head;
	}

}
