package lab5_1;

public class MyLinkedList<T> {
	MyNode<T> head;
	MyNode<T> end;
	int length;
	
	public MyLinkedList(){
		head=end=null;
		length=0;
	}
	
	public void add(T d) {
		MyNode<T> element=new MyNode<T>(d);
		if(length==0) {
			head=end=element;
		}
		else {
			end.next=element;
			end=element;
		}
		length++;
	}	
	
	public T getFirst() {
		return head.data;
	}
	public T getEnd() {
		return end.data;
	}
	public T get(int index) {
		if(length==0 || index>length) return null;
		MyNode<T> g=head;
		for(int i=1;i!=index;i++) {
			g=g.next;
		}
		return g.data;
	}
	public int get(T d) {
		
		if(length==0) return -1;
		MyNode<T> g=head;
		for(int i=0;i<length && g!=null;i++) {
			if(g.data.equals(d))return i;
			g=g.next;
		}
		System.out.println("toString:"+toString());
		return -1;
	}
	public T remove(T targetData) {
		MyNode<T> popped=null;
		if(length==0) return null;
		MyNode<T> current=head;
		while(current!=null) {
			if(current.data == targetData && current == head) {
				popped=current;
				head=current.next;
				length--;
				break;
			}
			else if(current.next!=null && current.next.data == targetData) {
				popped=current.next;
				current.next=current.next.next;
				length--;
				break;
			}
			current=current.next;
		}
	
		if(popped == null) {//not found
			return null;
		}
		//found
		return (T) popped.data;
	}	
	 public String toString() {
	    	MyNode<T> n = head;
	    	String s="";
	    	for(int i=1; i<=length ;i++) {
	    		if( n!=null) {
	    		s=s+n.data+" ";
	    		n=n.next;
	    		}
	    	}
		    return s;
	    }
	public T remove(int idx) {
		MyNode<T> popped=null;
		if(length==0) return null;
		MyNode<T> current=head;
		for(int i=0;i<length && current!=null;i++) {
			if(idx==0 && current == head) {
				popped=current;
				head=current.next;
				length--;
				break;
			}
			else if(current.next!=null && i == idx-1) {
				popped=current.next;
				current.next=current.next.next;
				length--;
				break;
			}
			current=current.next;
		}
		
		if(popped == null) {//not found
			return null;
		}
		//found
		return (T) popped.data;
	}	
	public T removeFirst() {
		return remove(0);
	}
	public T removeLast() {
		MyNode<T> popped=end;
		if(length==0) return null;
		MyNode<T> prev=head;
		for(int i=1;i<length-1;i++) {
			prev=prev.next;
		}
		prev.next=null;
		end=prev;
		length--;
		
		
		return (T) popped.data;
	}	
	
}
