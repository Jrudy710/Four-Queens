/* Jason Rudinsky
* Rudinsky Programs
* August 30, 2021

* This is a program that will be used to find the places on a four
* by four chess board, where four queens can be placed so that they
* do not interfere with each other and cannot be taken/removed from the board.
* 8/30/2021 - Original Version
*/

package FourQueens;                                                                             // Sets the destination of the file

import java.util.Scanner;                                                                       // Imports Java Scanner for User Input 

public class FourQueens{                                                                        // Class Block 
   public static void main(String[] args){                                                      // Method Block
      Scanner input = new Scanner(System.in);                                                   // Creates Scanner Object
      
                                                                                                // VARIABLE DEFINITION
      int arrayDimension = 8;                                                                   // Sets the value of arrayDimension
   
      QueensCompute QueenSolver = new QueensCompute(arrayDimension);                            // Creates an Object of the QueensCompute class
      
      //QueenSolver.printArray();                                                                 // Performs an action of the QueenCompute class
      
      //System.out.println("\nSolving...");                                                       // Prints out to the user
      
      if(QueenSolver.placeQueens(0, 0, 0, 0, false)){
         QueenSolver.eliminateExtraChar();                                                         // Performs an action of the QueensCompute class
         QueenSolver.printArray();                                                                 // Call to method printArray   
      }
      else{
         System.out.printf("I can not put %d Queens on a %d X %d chess board", arrayDimension, arrayDimension, arrayDimension);
      }
      
   }
}