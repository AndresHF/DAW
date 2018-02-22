package eva2.linkedlist;
public class Nodo implements Comparable<Nodo>{

	private Nodo next;
	private int info;

	public Nodo(){
		
		this.next = null;
		this.info = 0;
	}
	
	public Nodo(int info){
		this.next = null;
		this.info = info;
	}

	public String toString(){
		String nextInfo = "";
		if(this.next != null){
			nextInfo = "   --> ";
		}else{
			nextInfo = "   --> LAST NODE"; 
		}
		return "INFO: " + this.info + nextInfo;
	}
	
	public Nodo getNext() {
		return next;
	}

	public void setNext(Nodo next) {
		this.next = next;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	@Override
	public int compareTo(Nodo other) {
		
		return this.info - other.info;
	}

	
}
