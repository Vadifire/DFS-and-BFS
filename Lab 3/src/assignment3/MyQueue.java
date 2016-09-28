package assignment3;

import java.util.NoSuchElementException;

/*
 * MyQueue class is used to manage BFS search
 * @author Gilad
 */
public class MyQueue {
	private static class QueueNode<T>{
		private String data;
		private QueueNode<String> next;
		
		public QueueNode(String data) {
			this.data = data;
		}
	}
	
	private QueueNode<String> first;
	private QueueNode<String> last;
	
	public void add(String str){
		QueueNode<String> node = new QueueNode<String> (str);
		if (last !=null)
			last.next = node;
		last = node;
		if (first == null)
			first = last;
	}
	
	public String remove(){
		if (first == null) throw new NoSuchElementException();
		String data = first.data;
		first = first.next;
		if (first == null)
			last = null;
		return data;
	}
	
	public String peek(){
		if (first == null) throw new NoSuchElementException();
		return first.data;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
}
