/* WORD LADDER MyQueue.java
 * EE422C Project 3 submission by
 * Name: Cedric Debelle
 * EID: cfd363 
 * Unique: 16445
 * Gilad Croll
 * GC24654
 * 16445
 * Slip days used: <0>
 * Git URL: https://github.com/Vadifire/EE422C-Lab-3.git
 * Fall 2016
 */
package assignment3;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyQueue {
	public static class QueueNode{
		private String data;
		private QueueNode next;
		private QueueNode father;
		private ArrayList<String> ladder;
		public QueueNode(String data, ArrayList<String> dadLadder) {
			this.data = data;
			this.ladder = (ArrayList<String>) dadLadder.clone();
			this.ladder.add(data);
		}
	}
	
	/*
	 * Prints Queue in easy to read format; useful for debugging.
	 */
	public void printQueue(){
		if (isEmpty()){
			System.out.println("Queue is empty.");
		}
		QueueNode q = first;
		String output = "";
		while (q != null){
			output+=(q.data+", ");
			q = q.next;
		}
		System.out.println("["+output.substring(0,output.length()-2)+"]");
	}

	private QueueNode first;
	private QueueNode last;
	
	
	public void add(String str, ArrayList<String> dadLadder){
		QueueNode node = new QueueNode(str, dadLadder);
		if (last !=null)
			last.next = node;
		last = node;
		if (first == null)
			first = last;
	}
	
	

	
	public QueueNode remove(){
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
