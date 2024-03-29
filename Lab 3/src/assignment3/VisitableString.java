/* WORD LADDER VisitableString.java
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

import java.util.Set;

/*
 * VisitableString class is used for recursive DFS.
 * It's primary purpose is keeping track of which strings have already been looked at.
 * @author Cedric
 */

public class VisitableString implements Comparable{
	
	private String string;
	private boolean visited;
	
	public VisitableString(String s, boolean v){
		string = s;
		visited = v;
	}
	
	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * @return the string
	 */
	public String getString() {
		return string;
	}

	/**
	 * @param s the string to set
	 */
	public void setString(String s) {
		this.string = s;
	}
	
	public static VisitableString[] createVisitArray(Set<String> dict, String start){
		VisitableString[] vs = new VisitableString[dict.size()]; //Used to keep track of which strings have been visited
		int i=0;
		for (String s : dict){
			vs[i] = new VisitableString(s, s.equalsIgnoreCase(start));
			i++;
		}
		return vs;
	}

	@Override
	//Main.endGlobal must be defined
	public int compareTo(Object o) {
		VisitableString other = (VisitableString)o;
		int thisDiffFromEnd = 0;
		int otherDiffFromEnd = 0;
		String s1 = string.toLowerCase();
		String s2 = other.string.toLowerCase();
		for (int i = 0; i <s1.length(); i++){
			if (s1.charAt(i)!=Main.endGlobal.charAt(i)){
				thisDiffFromEnd++;
			}
		}
		for (int i = 0; i <s2.length(); i++){
			if (s2.charAt(i)!=Main.endGlobal.charAt(i)){
				otherDiffFromEnd++;
			}
		}
		return thisDiffFromEnd-otherDiffFromEnd;
	}
	
}
