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
		if(this.head == null)
			System.out.println("Empty list..");
		else{
			Nodo n = this.head;
			while(n != null){
				System.out.println(n.toString());
				n = n.getNext();
			}
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
	
	public void removeFirst(){
		if(this.head == null)
			System.out.println("Linked list is empty, can´t remove first.");
		else
			this.head = this.head.getNext();
	}
	
	public void addEnd(int info){
		
		Nodo n = this.head;
		if(n == null) this.head = new Nodo(info);
		else{
			while(n.getNext() != null){
				n = n.getNext();
			}
			
			n.setNext(new Nodo(info));
		}
	}
	
	public void removeLast(){
		if(this.head == null)
			System.out.println("Linked list is empty, can´t remove last.");
		else if(this.head.getNext() == null) 
			this.head = null;
		else{
			Nodo n = this.head;
			while(n.getNext().getNext() != null){
				n = n.getNext();
			}
			n.setNext(null);
		}
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
	
	public void removeByIndex(int index){
		if(this.head == null)
			System.out.println("List is empty");
		else if(index < 0 || index > this.length() - 1) 
			System.out.println("¡¡OUT OF BOUNDS!! allowed positions between 0 and " + (this.length() - 1) + " included.");
		else if(index == 0) 
			removeFirst();
		else if(index == length() - 1) 
			removeLast();
		else{
			Nodo prev = getByIndex(index - 1);
			prev.setNext(prev.getNext().getNext());
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

	public void sortByInfo(){
		
		int length = length();
		
		for(int i = 0; i < length; i++){
			for(int j = i + 1; j < length; j++){
				Nodo actual = getByIndex(i);
				Nodo toCompare = getByIndex(j);
				if(actual.compareTo(toCompare) > 0){
					int aux = actual.getInfo();
					actual.setInfo(toCompare.getInfo());
					toCompare.setInfo(aux);
				}
			}
		}
		
	}
	public Nodo getHead() {
		return head;
	}

	public void setHead(Nodo head) {
		this.head = head;
	}

}
