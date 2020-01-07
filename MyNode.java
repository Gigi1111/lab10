package lab5_1;

public class MyNode<T> {
	public T data;
	public MyNode<T> next;
	public MyNode(T d) {
		data=d;
		next=null;
	}
}
