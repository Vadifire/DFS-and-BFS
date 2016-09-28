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
	
	// static variables and constants only here.
	public static String startGlobal;
	public static String endGlobal;
	
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
				
		ArrayList<String> input = parse(kb);	//input gets the 2 keyboard input words,that is - start and end
		String start = input.get(0);	// start get first word
		String end = input.get(1);	// end get second word

		//TESTING
		printLadder(getWordLadderDFS(start,end)); //(NOTE: Must have .txt files in project directory)
		printLadder(getWordLadderBFS(start,end)); //(NOTE: Must have .txt files in project directory)
		
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
	 * Attempts to create path from start to end composed of words that each vary in 1 letter to adjacent elements.
	 * Returns empty ArrayList<String> if no ladder can be made.
	 * @param String start is the first word
	 * @param String end is the final word
	 * @returns ArrayList ladder to trace the path from start to end. Empty list if no path exists.
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		Set<String> dict = makeDictionary();
		startGlobal = start; //Initialize global vars
		endGlobal = end;
		VisitableString[] vs = new VisitableString[dict.size()]; //Used to keep track of which strings have been visited
		int i=0;
		for (String s : dict){
			vs[i] = new VisitableString(s, s.equalsIgnoreCase(start));
			i++;
		}
		
		ArrayList<String> ladder = new ArrayList<String>();
		return recursiveDFS(ladder,vs,start,end);
	}
	
	/*
	 * Recursively looks through branches until a branch reaches end, or all branches have reached dead ends.
	 * @param ArrayList<String> ladder keeps track of the word "path" we have taken in attempting to reach end
	 * @param VisitableString[] vs keeps track of which strings have been visited.
	 * @param String curWord is the word we have hopped to in our path.
	 * @param String end is the desired word to path to.
	 */
	public static ArrayList<String> recursiveDFS(ArrayList<String> ladder, VisitableString[] vs, String curWord, String end){
		ladder.add(curWord);
		for (int i = 0; i < vs.length; i++){ //Check through all strings in dict
			String s = vs[i].getString();
			if (differByOne(curWord, s) && !vs[i].isVisited()){ //Valid jump to make
				//System.out.println("Jumped to string "+s);
				vs[i].setVisited(true);
				if (curWord.equalsIgnoreCase(end)){
					return ladder; //Found end
				}
				else{
					ArrayList<String> resultLadder = recursiveDFS(ladder,vs,s,end);
					if (resultLadder.size() != 0)
						return resultLadder;
				}
			}
			
		}
		//System.out.println("Reached a dead end.");
		ladder.remove(curWord);
		return new ArrayList<String>(); //could not find ladder down this branch
	}

	
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		startGlobal = start; //Initialize global vars
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
		if (size < 2){ //empty
			if (startGlobal != null && endGlobal != null){
				System.out.println("no word ladder can be found between "+startGlobal+" and "+endGlobal+".");
			}
			else{ //Should ideally never occur.
				System.out.println("no word ladder can be found between <start> and <end>.");
			}
		}
		else{
			System.out.println("a "+size+"-rung word ladder exists between "+ladder.get(0).toLowerCase()+" and "
		+ladder.get(size-1).toLowerCase()+".");
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
