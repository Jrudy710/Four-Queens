/* Jason Rudinsky
* June 4, 2025
* Four Queens

* This is the program that will attempt to solve the problem of where to place queens on an n by n board, 
* so that none of the queens have the possibility of colliding with each other. 
* This will be attempted by using a DFS approach, with the stack implementation that will be used in the form of an ArrayList.
* Once the queens that are on the board are equal to the size of the chessboard, given that it will be a square board, 
* the values will be returned to the user.

* 6/4/2025 - Original Version
* 6/4/2025 - Added in the starting methods to just start the base 2D array. Now I get to implement the actual
    * meat and potatoes of the DFS implementation. It will involve the use of the arraylist and the .split()
    * method to get the coordinate values. I might also make use of the .contains method for Strings.
*/

import java.util.ArrayList;                                                                                 // Imports the arrayList libraries
import java.util.Scanner;                                                                                   // Imports Java Scanner

public class FourQueensImplementation{                                                                      // Class Block
    public static void main(String[] args){                                                                 // Main Method
        Scanner input = new Scanner(System.in);                                                             // Creates Scanner Object

                                                                                                            // VARIABLE DEFINITIONS
        int size = 0;                                                                                       // Defines size
        char[][] board;                                                                                     // Defines board
        
        System.out.print("Enter the size of the board (Must be greater than 4): ");                         // Prints out to the user
        size = input.nextInt();                                                                             // Sets the value of size        

        // System.out.printf("We will create a %d by %d array to store queens on\n", size, size);           // Debug print statement
        coordinateConversion('4', true);
        board = fillTheArray(size);                                                                         // Call to method fillTheArray
        printArray(board);                                                                                  // Call to method printArray
    }
    
    
    
    /*
        * This is just an easy method that will be used to fill the array
        * with default values and then return the array to the user. 
    */
    public static char[][] fillTheArray(int size){                                          // Method Block
        
                                                                                                            // VARIABLE DEFINITIONS
        int row = 0;                                                                                        // Defines row
        int column = 0;                                                                                     // Defines column 
        char[][] board = new char[size][size];                                                                       // Initializes the 2D array
        
        for(row = 0; row < size; row++){                                                                    // For Loop
            for(column = 0; column < size; column++){                                                       // Nested For Loop
                board[row][column] = ' ';                                                                   // Sets the value of board at the index of row and column
            }
        }
                
        return board;                                                                                       // Returns board to the user
    }
    
    
    
    /*
        * This is the method that will be used to print out the 2-D array to the console.  
    */
    public static void printArray(char[][] board){                                                          // Method Block
        
                                                                                                            // VARIABLE DEFINITIONS
        int row;                                                                                            // Defines row
        int column;                                                                                         // Defines column
        
        for(row = 0; row < board.length; row++){                                                            // For Loop
            for(column = 0; column < board[row].length; column++){                                          // Nested For Loop
               System.out.printf("| %c ", board[row][column]);                                                                  // Prints out to the user 
            }
            System.out.println("|");                                                                        // Prints out to the user
        }
    }
    
    

    /*
        * This is the method that will be used to convert either the row or column value
        * into the equivalent row or column value as dictated by the helper method that 
        * I use for determining the correct values.
    */
    public static int coordinateConversion(char value, boolean col){                                         // Method Block
        
                                                                                                            // VARIABLE DEFINITIONS
        int convertedValue = 0;                                                                             // Defines convertedValue
        
        // If the value of col is true then it will be (int)value - 97
        // Otherwise the value will be (int)value - 48
        
        // If the value of col is true then we are looking at the row values [0-9], otherwise it should be alphanumeric values [a-z]
        convertedValue = col ? (int)value - 48 : (int)value - 97;                                           // Sets the value of convertedValue
        // System.out.println(convertedValue);                                                              // Debug print statement
        
        return convertedValue;                                                                              // Returns the value to the user
    }
}