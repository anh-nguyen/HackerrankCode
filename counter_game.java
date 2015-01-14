/*
Problem Description page: https://www.hackerrank.com/challenges/counter-game/
Short Description: Write a program to decide the winner in the counter game:

(from Hackerrank.com)
Louise and Richard play a game. They have a counter set to N. Louise gets the first turn and the turns alternate thereafter. In the game, they perform the following operations.

If N is not a power of 2, they reduce the counter by the largest power of 2 less than N.
If N is a power of 2, they reduce the counter by half of N.
The resultant value is the new N which is again used for subsequent operations.
The game ends when the counter reduces to 1, i.e., N == 1, and the last person to make a valid move wins.

Given N, your task is to find the winner of the game.
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;

public class counter_game {
    public static void find_winner(BigInteger N) {
        String winner = "Richard";
        if (N.equals(BigInteger.valueOf(1))) {
            System.out.println(winner);
        } else {
        	// convert N to binary. It's a given that this binary number starts with 1.
        	String binary_N = N.toString(2);
            winner = "Louise";
            while (true) {
                // if N is a power of 2, divide by 2 i.e. shift number 1 place right
                // if N is not a power of 2, subtract from largest power of 2 i.e. the leftmost 1
                // always check if N is 1 at the end 
            	int first_one = binary_N.indexOf("1", 1);
            	if (first_one == -1) {		// N is power of 2 (is in the form of 100...0)
            		binary_N = binary_N.substring(0, binary_N.length()-1);
            		if (binary_N.equals("1")) {
            			System.out.println(winner);
            			break;
            		} else {
            			winner = flip_winner(winner);
            		}
            	} else {					// N is not power of 2
            		binary_N = binary_N.substring(first_one);
            		if (binary_N.equals("1")) {
            			System.out.println(winner);
            			break;
            		} else {
            			winner = flip_winner(winner);
            		}
            	}
            }
            
            
        }
    }
    
    public static String flip_winner(String winner){
        if (winner == "Richard"){
            return "Louise";
        } else {
            return "Richard";
        }      
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number_of_tests = sc.nextInt();
        for (int i=0; i<number_of_tests; i++) {
            find_winner(sc.nextBigInteger());
        } 
    }
}
