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
* 6/14/2025 - Determined most of the logic to place the queens onto the board, as well as formatted the print function a 
    * little for easier readability. Just need to finish up the final strokes for it to look nice and place the queens on the board.
* 6/14/2025 - Finished the four queens problem. It works almost instantaneously for sizes of the board up to at least 15.
    * There is some lag when there are sizes above that but that is to be expected with DFS. 
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
        // False == rows conversion
        // True == column conversion
        //System.out.println(coordinateConversion('a', false) + " " + alphaConversion(0));
        board = fillTheArray(size);                                                                         // Call to method fillTheArray
        board = placeQueens(board);                                                                         // Call to method placeQueens
        printArray(board);                                                                                  // Call to method printArray
    }
    
    
    
    /*
        * This is just an easy method that will be used to fill the array
        * with default values and then return the array to the user. 
    */
    public static char[][] fillTheArray(int size){                                                          // Method Block
        
                                                                                                            // VARIABLE DEFINITIONS
        int row = 0;                                                                                        // Defines row
        int column = 0;                                                                                     // Defines column 
        char[][] board = new char[size][size];                                                              // Initializes the 2D array
        
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
        
        char rowVal = 'a';                                                                                  // Defines rowVal
        
        for(row = 0; row < board.length; row++, rowVal++){                                                  // For Loop
            System.out.printf("%c ", rowVal);                                                               // Prints out to the user
            for(column = 0; column < board[row].length; column++){                                          // Nested For Loop
               System.out.printf("| %c ", board[row][column]);                                              // Prints out to the user 
            }
            System.out.println("|");                                                                        // Prints out to the user
        
        }
        System.out.print("  ");                                                                             // Format printing
        for(row = 0; row < board.length; row++){                                                            // For Loop
            System.out.printf(" %2d ", row);                                                                // Prints out the column number to the user
        }   
        System.out.println();                                                                               // Prints out the newline character
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
    
    
    
    /*
        * This is the method that will be used to convert row or column values into the appropriate row value.
        * I only need to do this for the rows as the columns will already be in the necessary numerical format.
    */
    public static char alphaConversion(int colValue){                                                       // Method Block
        
                                                                                                            // VARIABLE DEFINITIONS
        char returnValue = ' ';                                                                             // Defines returnValue
        
        returnValue = (char)(colValue + 97);                                                                // Sets the value of returnValue
        
        return returnValue;                                                                                 // Returns the value to the user
        
    }
    
    
    
    /*
        * This is the method that will be used to determine if the position where we hypothetically want
        * to place a Queen is able to in fact have a queen placed on that position. It will work by going
        * backward to see if the values conflict with any queens already placed on the board.
        * If there are no conflicts then true will be returned to the user, otherwise false will be returned.
    */
    public static boolean availableSpot(int depth, String queens, String nextPlace){                        // Method Block
        
                                                                                                            // VARIABLE DEFINITIONS
        int row = 0;                                                                                        // Defines row
        int column = 0;                                                                                     // Defines column
        
        String temp = "";                                                                                   // Defines temp
        
        temp += nextPlace.charAt(0);                                                                        // Adds to the value of temp
        
        // Horizontal Check
        for(column = Integer.valueOf(nextPlace.substring(1)); column >= 0; column--){                       // For Loop
            temp += column;                                                                                 // Adds to the value of temp
            //System.out.printf("Horizontally checking %s\n", temp);                                        // Debug print statement
            if(queens.contains(temp)){                                                                      // If the string is inside the already placed queens
                //System.out.printf("WOMP WOMP Horizontally checking %s\n", temp);                          // Debug print statement
                return false;                                                                               // Returns false to the user
            }
            else{
                temp = temp.substring(0, 1);                                                                // Changes the value of temp back to just the row value
            }
        }
        
        // Moonwalk up the stairs
        for(row = coordinateConversion(nextPlace.charAt(0), false), column = Integer.valueOf(nextPlace.substring(1)); row >= 0 && column >= 0; row--, column--){
            
            temp = "" + alphaConversion(row) + column;                                                      // Updates the value of temp
            //System.out.printf("Moonwalk checking %s\n", temp);                                            // Debug print statement
            if(queens.contains(temp)){                                                                      // If a queen is already placed on the board
                //System.out.printf("WOMP WOMP Moonwalk checking %s\n", temp);                              // Debug print statement
                return false;                                                                               // Returns false to the user
            }
        }
        
        // fall back down the stairs
        for(row = coordinateConversion(nextPlace.charAt(0), false), column = Integer.valueOf(nextPlace.substring(1)); row < depth && column >= 0; row++, column--){
            
            temp = "" + alphaConversion(row) + column;                                                      // Updates the value of temp
            //System.out.printf("Falling down checking %s\n", temp);                                        // Debug print statement
            if(queens.contains(temp)){                                                                      // If a queen is already placed on the board
                //System.out.printf("WOMP WOMP Falling down checking %s\n", temp);                          // Debug print statement
                return false;                                                                               // Returns false to the user
            }
        }
                
        return true;                                                                                        // Returns true to the user
    }
    
    public static char[][] placeQueens(char[][] board){                                                     // Method Block
        
                                                                                                            // VARIABLE DEFINITIONS
        int row = 0;                                                                                        // Defines row
        int column = 0;                                                                                     // Defines column
        int placeholder = 0;                                                                                // Defines placeholder
        
        String curr = "";                                                                                   // Defines curr
        String temp = "";                                                                                   // Defines temp
        
        ArrayList<String> myStack = new ArrayList<String>();                                                // Defines myStack
        
        String[] queens = new String[1];                                                                    // Defines queens
        
        myStack.add("");                                                                                    // Adds to myStack
        
        while(myStack.size() != 0){                                                                         // Loops through the stack
            
            curr = myStack.remove(myStack.size() - 1);                                                      // DFS traversal pop
            //System.out.println("Value in the stack: " + curr);                                            // Debug print statement
            
            queens = curr.split(",");                                                                       // Defines the number of queens on the current iteration of the board
            
            // The first iteration of the loop
            if(curr.equals("") && myStack.size() == 0){                                                     // If statement
                column = 0;                                                                                 // Sets the value of column
            }
            else{                                                                                           // Else statement
                column = Integer.valueOf(queens[queens.length - 1].substring(1));                           // Sets the value of column
                column += 1;                                                                                // Adds to the value of column
            }
            
            
            
            if(queens.length == board.length){                                                              // If the number of queens supposedly on the board matches the length of the board
                //System.out.println("Coordinates of valid queens: " + curr);                               // Debug print statement
                break;                                                                                      // Breaks out of the loop
            }
            
            for(row = 0; row < board.length && column < board.length; row++){                               // For Loop
                temp = alphaConversion(row) + "" + column;                                                  // Sets the value of temp
                
                if(availableSpot(board.length, curr, temp)){                                                // If statement
                    temp = curr.length() > 0 ? curr + "," + temp : temp;                                    // Updates the value of temp
                    myStack.add(temp);                                                                      // Adds to myStack
                }
            }
        }
        
        if(queens.length == board.length){                                                                  // If we found a solution
            for(row = 0; row < queens.length; row++){                                                       // For Loop
                placeholder = coordinateConversion(queens[row].charAt(0), false);                           // Sets the value of placeholder
                column = Integer.valueOf(queens[row].substring(1));                                         // Sets the value of column
                
                board[placeholder][column] = 'Q';                                                           // Sets the value of board at the index of placeholder and column
            }
        }
        else{                                                                                               // Else statement
            System.out.println("No valid places to put " + board.length + " queens on the board");          // Debug print statement
        }
        
        return board;                                                                                       // Returns the board to the user
    }
}