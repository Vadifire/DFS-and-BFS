/* WORD LADDER Main.java
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
import java.util.*;
import java.io.*;

public class Main {
	
	static String startGlobal;	// start is first word
	static String endGlobal;	// end is second word
	
	public static void main(String[] args) throws Exception {
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		// TODO methods to read in words, output ladder
		
		ArrayList<String> input = parse(kb);	//input gets the 2 keyboard input words,that is - start and end
		startGlobal = input.get(0);	// start get first word
		endGlobal = input.get(1);	// end get second word

		//TESTING
		printLadder(getWordLadderBFS(startGlobal,endGlobal)); //(NOTE: Must have .txt files in project directory)
		printLadder(getWordLadderDFS(startGlobal,endGlobal)); //(NOTE: Must have .txt files in project directory)
		
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> words= new ArrayList<String>();	// words are the 2 words to return
		String inputStream = "";
		String start, end;
		
		do {
			inputStream = inputStream + keyboard.nextLine();
			inputStream = inputStream.replaceAll(" ", ""); //Trim string of white space
			inputStream = inputStream.replaceAll("\n", "");
			inputStream = inputStream.replaceAll("\t", "");
		}
		while (inputStream.length() < 10);
		
		start = inputStream.substring(0,5).toLowerCase();
		end = inputStream.substring(5,10).toLowerCase();
		//System.out.println(start);
		//System.out.println(end);
		words.add(start);
		words.add(end);
		return words;
	}
	
	/*
	 * @param String start is the first word
	 * @param String end is the final word
	 * @returns ArrayList ladder to trace the path from start to end. Empty list if no path exists.
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		Set<String> dict = makeDictionary();
		
		ArrayList<String> ladder = new ArrayList<String>();
		ladder.add(start);
		//TODO: Recursive DFS to look for ladder
		ladder.add(end);
		return ladder;
	}

	
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	startGlobal= start;
    	endGlobal = end;
		MyQueue queue=new MyQueue();
		ArrayList<String> ladder = new ArrayList<String>();
		ladder.add(start);
		Set<String> dict = makeDictionary();
		int found =0;
		addWordsToQueue(dict,start,queue, ladder);
		while (!queue.isEmpty() && found==0){
			String current = queue.peek(); 
			ladder = queue.peekLadder();
			queue.remove();
			if (current.equals(end))
				found =1;
			addWordsToQueue(dict,current,queue, ladder);
		}
		return ladder; // replace this line later with real return
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	
	/*
	 * Prints out ladder from lowest index to highest index
	 * @param ArrayList  of ladder to print out
	 */
	public static void printLadder(ArrayList<String> ladder) {
		int size = ladder.size();
		
		/*
		 * TODO: Fix implementation of displaying empty list message. 
		 * I.E. Will <start> and <end> be stored as global static variables? DFS and BFS return empty lists
		 * if no ladder can be created, but maybe it would be useful to pass a ladder with start and end to this method
		 * for the purposes of printing if that is the case.
		 * **** Fixed **** please approve
		 */
		if (size <= 2){ //empty
			System.out.println("no word ladder can be found between <start> and <end>.");
		}
		else{
			System.out.println("a "+(size-2)+"-rung word ladder exists between "+startGlobal.toLowerCase()+" and "
		+endGlobal.toLowerCase()+".");
			for (int i = 0; i < size; i++){
				System.out.println(ladder.get(i).toLowerCase());
			}
		}
	}
	
	/**
	 * @param word
	 * @param dictWord
	 * @returns true if word and dictWord differ by exactly one letter 
	 */
	private static boolean differByOne (String word1, String dictWord){
		int dif=0;	// dif represents the amount of different letters
		String word = new String(word1.toLowerCase()); //Added to ignore case sensitivity.
		dictWord = dictWord.toLowerCase();
		for (int i=0; i<word.length();i++){
			if (word.charAt(i)!=dictWord.charAt(i))
				dif++;
		}
		if (dif == 1)
			return true;
		return false;
	}
	
	/**
	 * adds to the queue all the words that differ by one from word
	 * @param dict
	 */
	private static void addWordsToQueue(Set<String> dict, String word, MyQueue queue, ArrayList<String> ladder){
		for (String s : dict) {
			s=s.toLowerCase();
		 	if (differByOne(word,s) && !s.equals(word)){	//make sure that they differ by one, and not goes back to parent 
				queue.add(s, ladder);
		    		}
		}
	}
	
}
