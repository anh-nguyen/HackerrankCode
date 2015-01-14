/*
Hackerrank Problem Description: https://www.hackerrank.com/challenges/a-text-processing-warmup
 
Task Summary: given paragraphs of texts as input, print out occurences of the articles "a", "an", and "the", as well as the occurences of dates.
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;


public class word_processing_warmup {
	public static String[] months;
	
	public static void process (String text) {
		String[] tokens = text.replaceAll("[^ A-Za-z0-9]", "").split(" ");          // remove all punctuation from string
        
		// create a hashmap for 'a', 'an', 'the', and dates. The value is the number of occurences.
		HashMap<String, Integer> token_map = new HashMap<String, Integer>();
		token_map.put("a", 0); 
		token_map.put("an", 0);
        token_map.put("the", 0);
        token_map.put("date", 0);
        
        for (int i=0; i < tokens.length; i++) {		// go through every word in the text
                                                                    // check for a, an, and the
        	if (tokens[i].toLowerCase().matches("a")) {
        		token_map.put("a", token_map.get("a") + 1);
        	} else if (tokens[i].toLowerCase().equals("an")) {
        		token_map.put("an", token_map.get("an") + 1);
        	} else if (tokens[i].toLowerCase().matches("the")) {
        		token_map.put("the", token_map.get("the") + 1);
        	} else {                                                // do date checks
        		// check for format: number/number/number
        		if (tokens[i].matches("((0?[1-9])|(1[0-9])|(2[0-9])|(3[0-1])|((1|2)?[0-9][0-9][0-9]))(/|-)((0?[1-9])|(1[0-9])|(2[0-9])|(3[0-1]))(/|-)((0?[1-9])|(1[0-9])|(2[0-9])|(3[0-1])|((1|2)?[0-9][0-9][0-9]))")) {
        			token_map.put("date", token_map.get("date") + 1);
        		} else if (tokens[i].matches("((0?[1-9])|(1[0-9])|(2[0-9])|(3[0-1]))([st]|[nd]|[rd]|[th])?")) { // format: Date Month Year
                    if (i+1 >= tokens.length) {
        				break;
        			} else if (Arrays.asList(months).contains(tokens[i+1])) {   // matches month
        				if (i+2 >= tokens.length) {
        					break;
        				} else if (tokens[i+2].matches("((1|2)?[0-9][0-9][0-9])")) {    // matches year
        					token_map.put("date", token_map.get("date") + 1);
        				}
        			}
        		} else if (Arrays.asList(months).contains(tokens[i])) {     // format: Month Date Year
        			if (i+1 >= tokens.length) {
        				break;
        			} else if (tokens[i+1].matches("((0?[1-9])|(1[0-9])|(2[0-9])|(3[0-1]))([st]|[nd]|[rd]|[th])?")) { 	// matches date
        				if (i+2 >= tokens.length) {
        					break;
        				} else if (tokens[i+2].matches("((1|2)?[0-9][0-9][0-9])")) {    // matches year
        					token_map.put("date", token_map.get("date") + 1);
        				}
        			}
        		}
        		
        	}
        }
        
        // print out occurences
        System.out.println(token_map.get("a"));
        System.out.println(token_map.get("an"));
        System.out.println(token_map.get("the"));
        System.out.println(token_map.get("date"));
	}
	
	
	public static void main (String args[]) {
		months = new String[] {"January", "Jan", "February", "Feb", "March", "Mar", "April", "Apr", "May", "June", "Jun", "July", "Jul", "August", "Aug", "September", "Sept", "October", "Oct", "November", "Nov", "December", "Dec"};
		Scanner sc = new Scanner(System.in);
		int no_of_inputs = sc.nextInt();    // the first line of input is the number of text inputs in the test case.
        sc.nextLine();
		for (int i=0; i<no_of_inputs; i++) {
			process(sc.nextLine());         // text inputs are paragraphs of texts with one empty line in-between.
            sc.nextLine();
		}
		sc.close();
		
	}
}
