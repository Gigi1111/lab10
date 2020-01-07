package lab5_1;

//public interface MySet<T> {
//	    public int getSize();
//     	public boolean isEmpty();
//	    public void add(T data);
//	    public void remove(T data);
//	    public boolean contains(T data);
//	    public String toSet();
//	    public MySet<T> union(MySet<T> s1, MySet<T> s2);
//	    public MySet<T> intersection(MySet<T> s1, MySet<T> s2);
//	    public MySet<T> minus(MySet<T> s1, MySet<T> s2);
//}



public interface MySet<T>{
    public int getSize();//size
    public boolean isEmpty();
    public void add(T o);//addElement
    public MySetAsList union(MySetAsList s2);
    public MySetAsList intersection(MySetAsList s);
    public MySetAsList subtract(MySetAsList s);
    public void remove(T o);
    public boolean isElement(T o);//contains
    public String listElements();
    public String powerSet();
}