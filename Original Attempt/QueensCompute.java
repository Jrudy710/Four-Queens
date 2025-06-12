/* Jason Rudinsky
* Rudinsky Programs
* August 30, 2021

* This is the class file that will be used to compute where the designated number
* of queens can be placed on the chess board without conflicting with any of the
* other queens that are on the board. This will be done through recursive methods
* so as to limit the length of code that is used to compute this problem.

* 8/30/2021 - Original Version
* 8/31/2021 - Added the Pseudocode for the method for placing Queens on the board
* 9/1/2021 - Added the method to determine the number of queens that are currently on the board
* 9/13/2021 - Added more of the recursion to solve the placement of up to 7 queens on a 7 by 7 chessboard.
   * When the user tries to do anything larger, it results in a stack overflow error because of the recursion.
*/

package FourQueens;                                                                                               // Sets the destination of the file

public class QueensCompute{                                                                                       // Class Block
   
                                                                                                                  // VARIABLE DEFINITIONS
   private int boardDimensions;                                                                                   // Defines the private int variable boardDimensions
   private int wantedQueens;
   
   private String[][] chessBoard;                                                                                 // Defines the private 2D array of String variable types
   
   
   // This is the constructor for when the user enters a specific board size and
   // matching number of queens that should be on the board
   public QueensCompute(int arrWidth){                                                                            // Argument Constructor
      boardDimensions = arrWidth;                                                                                 // Sets the value of the INSTANCE VARIABLE boardDimensions
      chessBoard = new String[arrWidth][arrWidth];                                                                // Defines the length and width of the 2D array chessBoard
      wantedQueens = chessBoard.length;
      blankArray();                                                                                               // Call to method blankArray
   }
   
   // This is the no argument constructor for if the user does not have
   // a specific size for the number of queens and size of the board
   public QueensCompute(){                                                                                        // No Argument Constructor
      boardDimensions = 4;                                                                                        // Sets the value of the INSTANCE VARIABLE boardDimensions
      chessBoard = new String[boardDimensions][boardDimensions];                                                  // Sets the length and width the 2D array chessBoard
      blankArray();                                                                                               // Call to Method blankArray
   }
   
   public void blankArray(){                                                                                      // Method Block
      
                                                                                                                  // VARIABLE DEFINITION
      int row = 0;                                                                                                // Defines row
      int column = 0;                                                                                             // Defines column
      
      
      // This is the section of code that will be used 
      // to fill the 2D array with empty spaces
      for(row = 0; row < chessBoard.length; row++){                                                               // For Loop
         for(column = 0; column < chessBoard[row].length; column++){                                              // Nested For Loop
            chessBoard[row][column] = " ";                                                                        // Sets the value at the index of row and column
         }
      }
   }
   
   
   // This is the method that will be used
   // to print out the array to the user
   public void printArray(){                                                                                      // Method Block
      
                                                                                                                  // VARIABLE DEFINITION
      int row = 0;                                                                                                // Defines row
      int column = 0;                                                                                             // Defines column
            
      for(row = 0; row < chessBoard.length; row++){                                                               // For Loop
         System.out.print("|");
         for(column = 0; column < chessBoard[row].length; column++){                                              // Nested For Loop
            System.out.printf("%s|", chessBoard[row][column]);                                                    // Prints out to the user
         }
         System.out.println();                                                                                    // Prints out a blank line to the user
      }
   }
   
   
   /* Pseudocode for method placeQueens 
      * When the value for col is greater than or equal to the length of
         * the board I want to return true if my counter to count the number of queens is equal to
         * the variable boardDimensions
      * When at an index where the space is " ", we want to place the letter "Q"
         * and then we want to eliminate all of the places on the board that the 
         * queen can move to in the game of chess 
      * If the program reaches the end of the array and does not have 4 queens placed on the board
         * We want to go back (not start over) and remove the most recent one placed (along with all the
         * areas that having that queen at that spot made impossible
         * and try to place a piece farther down on the array
            * If nothing can be placed farther down the line, we want to go even further backwards
               * repeating the process until if you are at the first queen, you want to move that
               * first queen down the line
   */
   
   public boolean placeQueens(int row, int col, int startingRow, int startingCol, boolean backwards){          // Method Block
      
      
      if(startingRow >= chessBoard.length){                                                                    // If statement
         return placeQueens(0, startingCol + 1, 0, startingCol + 1, backwards);                                // Recursive call to method
      }
      
      if(startingCol >= chessBoard.length){                                                                    // If statement
         return false;                                                                                         // Returns false to the user
      }
          
      if(queenCount() == wantedQueens){                                                                        // If statement
         return true;                                                                                          // Returns true to the user
      }                                      
      else if(queenCount() < wantedQueens){                                                                    // Else if statement
         if(!backwards){                                                                                       // Nested If statement
            if(row >= chessBoard.length){                                                                      // Double Nested If statement
               return placeQueens(0, col + 1, startingRow, startingCol, false);                                // Recursive call to method
            }
            else if(col >= chessBoard.length){                                                                 // Double Nested Else if statement
               return placeQueens(chessBoard.length - 1, col - 1, startingRow, startingCol, true);             // Recursive call to the method
            }
            else{                                                                                              // Double Nested Else statement
               if(chessBoard[row][col].equals(" ")){                                                           // Triple Nested If statement for when the element at the designated index is empty
                  chessBoard[row][col] = "Q";                                                                  // Sets the value at the index of row and col
                  eliminateSpots(row, col);                                                                    // Call to method eliminateSpots
               }
               return placeQueens(row + 1, col, startingRow, startingCol, false);                              // Recursive call to method
            }
         }
         else{                                                                                                 // Nested Else statement
            if(row < 0){
               return placeQueens(chessBoard.length - 1, col - 1, startingRow, startingCol, true);
            }
            else if(col < 0){
               return placeQueens(startingRow, startingCol, startingRow + 1, startingCol, false);
            }
            else{
               if(chessBoard[row][col].equals("Q")){
                  replaceSpots();
                  
                  if(availableSpots(row, col)){
                     chessBoard[row][col] = " ";
                     /*
                     printArray();
                     System.out.println("\n\n\n");
                     */
                     return placeQueens(row + 1, col, startingRow, startingCol, false);
                  }
                  chessBoard[row][col] = " ";
               }
               
               return placeQueens(row - 1, col, startingRow, startingCol, true);
            }
         }
      }
      else{
        return true;
      }
   }
   
   public boolean availableSpots(int row, int column){
      
      int LCV = 0;
      int LCV2 = 0;
      
      for(LCV = row; LCV < chessBoard.length; LCV++){
         for(LCV2 = column; LCV2 < chessBoard[row].length; LCV2++){
            if(chessBoard[LCV][LCV2].equals(" ")){
               return true;
            }
         }
      }
      return false;
   }
   
   
   public void replaceSpots(){
      
                                                                                                               // VARIABLE DEFINITIONS
      int LCV = 0;                                                                                             // Defines LCV
      int LCV2 = 0;                                                                                            // Defines LCV2
      
      char placingChar = ' ';                                                                                  // Defines placingChar
      
      String stringForBoard = "";                                                                              // Defines stringForBoard
      
      placingChar = (char)(queenCount() + 96);                                                                 // Sets the value of placingChar
      
      stringForBoard += placingChar;                                                                           // Adds to the value of stringForBoard
      
      for(LCV = 0; LCV < chessBoard.length; LCV++){
         for(LCV2 = 0; LCV2 < chessBoard[LCV].length; LCV2++){
            if(chessBoard[LCV][LCV2].equals(stringForBoard)){
               chessBoard[LCV][LCV2] = " ";
            }
         }
      }
   }
   
   
   /* PseudoCode for method eliminateSpots
      * Given the coordinates of the Queen that was just placed, we want to get a char value 
         * that is dependent on the number of queens that is currently on the board
      * Once that is the case we want to place said character everywhere on the board where the queen 
         * has the possibility of moving to on the board
   */
   public void eliminateSpots(int row, int column){                                                            // Method Block
      
                                                                                                               // VARIABLE DEFINITIONS
      int LCV = 0;                                                                                             // Defines LCV
      int LCV2 = 0;                                                                                            // Defines LCV2
      
      char placingChar = ' ';                                                                                  // Defines placingChar
      
      String stringForBoard = "";                                                                              // Defines stringForBoard
      
      placingChar = (char)(queenCount() + 96);                                                                 // Sets the value of placingChar
      
      stringForBoard += placingChar;                                                                           // Adds to the value of stringForBoard
      
      for(LCV2 = 0; LCV2 < chessBoard.length; LCV2++){                                                         // For Loop
         
         
         // This is eliminating values horizontally on the board
         if(LCV2 != column && chessBoard[row][LCV2].equals(" ")){                                              // If statement
            chessBoard[row][LCV2] = stringForBoard;                                                            // Sets the value at the index of row and LCV2
         }
         
         // This is eliminating value vertically on the board
         if(LCV2 != row && chessBoard[LCV2][column].equals(" ")){                                              // If statement
            chessBoard[LCV2][column] = stringForBoard;                                                         // Sets the value at the index of LCV2 and column
         }
      }
      

      // This will be the code that will help with eliminating 
      // spots on the first half of the front diagonal row
      LCV2 = column;                                                                                           // Sets the value of LCV2
      for(LCV = row; LCV >= 0; LCV--){                                                                         // For Loop
         if(LCV >= 0 && LCV2 >= 0){                                                                            // If statement
            if(chessBoard[LCV][LCV2].equals(" ")){                                                             // Nested If statement
               chessBoard[LCV][LCV2] = stringForBoard;                                                         // Sets the value at the index of LCV and LCV2
            }
         }
         LCV2--;                                                                                               // Subtracts from the value of LCV2
      }
      
      // This will be the code that will help with eliminating 
      // spots on the second half of the front diagonal row
      LCV2 = column + 1;                                                                                       // Sets the value of LCV2
      for(LCV = row + 1; LCV < chessBoard.length; LCV++){                                                      // For Loop
         if(LCV < chessBoard.length && LCV2 < chessBoard.length){                                              // If statement
            if(chessBoard[LCV][LCV2].equals(" ")){                                                             // Nested If statement
               chessBoard[LCV][LCV2] = stringForBoard;                                                         // Sets the value at the index of LCV and LCV2
            }
         }
         LCV2++;                                                                                               // Adds to the value of LCV2
      }
      
      
      // This will be the code that will help with eliminating
      // spots on the first half of the backward diagonal row
      LCV2 = row + 1;                                                                                          // Sets the value of LCV2
      for(LCV = column - 1; LCV >= 0; LCV--){                                                                  // For Loop
         
         if(LCV >= 0 && LCV2 < chessBoard.length && chessBoard[LCV2][LCV].equals(" ")){                        // If statement
            chessBoard[LCV2][LCV] = stringForBoard;                                                            // Sets the value at the index of LCV2 and LCV
         }
         LCV2++;                                                                                               // Adds to the value of LCV2
      }
      
      
      // This will be the code that will help with eliminating
      // spots on the second half of the backward diagonal row
      LCV2 = row - 1;                                                                                          // Sets the value of LCV2
      for(LCV = column + 1; LCV < chessBoard.length; LCV++){                                                   // For Loop
         
         if(LCV2 >= 0 && LCV < chessBoard.length && chessBoard[LCV2][LCV].equals(" ")){
            chessBoard[LCV2][LCV] = stringForBoard;
         }
         LCV2--;
      }
      
   }
   
   
   // This is the method that will be used when the four
   // queens have been correctly placed and eliminates 
   // all of the characters that were used as placeholders
   public void eliminateExtraChar(){
      
      int row = 0;
      int column = 0;
      
      for(row = 0; row < chessBoard.length; row++){
         for(column = 0; column < chessBoard[row].length; column++){
            if(!chessBoard[row][column].equals("Q")){
               chessBoard[row][column] = " ";
            }
         }
      }   
   }
   
   
   
   // This is the method that will be used to determine the 
   // number of Queens on the board at the moment that the method is called
   public int queenCount(){                                                                                    // Method Block
      
                                                                                                               // VARIABLE DEFINITIONS
      int row = 0;                                                                                             // Defines row
      int column = 0;                                                                                          // Defines column
      int counter = 0;                                                                                         // Defines counter
      
      for(row = 0; row < chessBoard.length; row++){                                                            // For Loop
         for(column = 0; column < chessBoard[row].length; column++){                                           // Nested For Loop
         
            if(chessBoard[row][column].equals("Q")){                                                           // If statement
               counter++;                                                                                      // Adds to the value of counter
            }
         }
      }
      return counter;                                                                                          // Returns the value of counter to the user
   }
}