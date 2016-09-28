package assignment3;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyQueue {
	public static class QueueNode<String>{
		private String data;
		private QueueNode<String> next;
		private QueueNode<String> father;
		private ArrayList<String> ladder;
		public QueueNode(String data, ArrayList<String> dadLadder) {
			this.data = data;
			this.ladder = (ArrayList<String>) dadLadder.clone();
			this.ladder.add(data);
		}
	}
	
	private QueueNode<String> first;
	private QueueNode<String> last;
	
	public void add(String str, ArrayList<String> dadLadder){
		QueueNode<String> node = new QueueNode<String>(str, dadLadder);
		if (last !=null)
			last.next = node;
		last = node;
		if (first == null)
			first = last;
	}
	
	

	
	public QueueNode<java.lang.String> remove(){
		if (first == null) throw new NoSuchElementException();
		first = first.next;
		if (first == null)
			last = null;
		return first;
	}
	
	public String peek(){
		if (first == null) throw new NoSuchElementException();
		return first.data;
	}
	
	public ArrayList<java.lang.String> peekLadder(){
		if (first == null) throw new NoSuchElementException();
		return first.ladder;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
}
