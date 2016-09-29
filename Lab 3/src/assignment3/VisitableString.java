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

public class VisitableString{
	
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
	
}
