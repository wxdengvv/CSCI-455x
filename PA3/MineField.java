// Name: Wanxin Deng
// USC NetID:
// CS 455 PA3
// Spring 2022

import java.util.Random;
/**
 MineField
 class with locations of mines for a game.
 This class is mutable, because we sometimes need to change it once it's created.
 mutators: populateMineField, resetEmpty
 includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {

   // <put instance variables here>
   private int numRows;
   private int numCols;
   private boolean[][] mine;

   private int numMine = 0;



   /**
    Create a minefield with same dimensions as the given array, and populate it with the mines in the array
    such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
    this minefield will corresponds to the number of 'true' values in mineData.
    @param mineData  the data for the mines; must have at least one row and one col,
    and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {
      numRows = mineData.length;
      numCols = mineData[0].length;
      mine = new boolean[numRows][numCols];
      mine = mineData;

      for(int i = 0; i< numRows; i++){
         for (int j = 0; j< numCols; j++){
            if(mine[i][j]){
               numMine++;
            }
         }
      }

   }


   /**
    Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once
    populateMineField is called on this object).  Until populateMineField is called on such a MineField,
    numMines() will not correspond to the number of mines currently in the MineField.
    @param numRows  number of rows this minefield will have, must be positive
    @param numCols  number of columns this minefield will have, must be positive
    @param numMines   number of mines this minefield will have,  once we populate it.
    PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations).
    */
   public MineField(int numRows, int numCols, int numMines) {
      this.numRows = numRows;
      this.numCols = numCols;
      numMine = numMines;
      mine = new boolean[numRows][numCols];

   }


   /**
    Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
    ensuring that no mine is placed at (row, col).
    @param row the row of the location to avoid placing a mine
    @param col the column of the location to avoid placing a mine
    PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {
      mine = new boolean[numRows][numCols];
      Random generator = new Random();

      int setMine = 0;
      while(setMine < numMine){
         int setRow = generator.nextInt(numRows);
         int setCol = generator.nextInt(numCols);
         if(mine[setRow][setCol]==false){
            mine[setRow][setCol] = true;
            setMine++;
         }
         if(mine[row][col] == true){
            mine[row][col] = false;
            setMine--;
         }
      }

   }


   /**
    Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
    Thus, after this call, the actual number of mines in the minefield does not match numMines().
    Note: This is the state a minefield created with the three-arg constructor is in
    at the beginning of a game.
    */
   public void resetEmpty() {

      mine = new boolean[numRows][numCols];
   }


   /**
    Returns the number of mines adjacent to the specified mine location (not counting a possible
    mine at (row, col) itself).
    Diagonals are also considered adjacent, so the return value will be in the range [0,8]
    @param row  row of the location to check
    @param col  column of the location to check
    @return  the number of mines adjacent to the square at (row, col)
    PRE: inRange(row, col)
    */
   public int numAdjacentMines(int row, int col) {
      int adjMine = 0;
      if(row-1>=0 && col-1>=0 && mine[row-1][col-1]==true){
         adjMine++;
      }
      if(row-1>=0 && mine[row-1][col]==true){
         adjMine++;
      }
      if(row-1>=0 && col+1<numRows && mine[row-1][col+1]==true){
         adjMine++;
      }
      if(col-1>=0 && mine[row][col-1]==true){
         adjMine++;
      }
      if(col+1<numCols && mine[row][col+1]==true){
         adjMine++;
      }
      if(row+1<numRows && col+1<numCols && mine[row+1][col+1]==true){
         adjMine++;
      }
      if(row+1<numRows && mine[row+1][col]==true){
         adjMine++;
      }
      if(row+1<numRows && col-1>=0 && mine[row+1][col-1]==true){
         adjMine++;
      }
      return adjMine;       // DUMMY CODE so skeleton compiles
   }


   /**
    Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
    start from 0.
    @param row  row of the location to consider
    @param col  column of the location to consider
    @return whether (row, col) is a valid field location
    */
   public boolean inRange(int row, int col) {
      if(row<numRows && col<numCols && row>=0 && col>=0){
         return true;
      }
      return false;       // DUMMY CODE so skeleton compiles
   }


   /**
    Returns the number of rows in the field.
    @return number of rows in the field
    */
   public int numRows() {
      return numRows;       // DUMMY CODE so skeleton compiles
   }


   /**
    Returns the number of columns in the field.
    @return number of columns in the field
    */
   public int numCols() {
      return numCols;       // DUMMY CODE so skeleton compiles
   }


   /**
    Returns whether there is a mine in this square
    @param row  row of the location to check
    @param col  column of the location to check
    @return whether there is a mine in this square
    PRE: inRange(row, col)
    */
   public boolean hasMine(int row, int col) {
      if(mine[row][col]==true){
         return true;
      }
      return false;       // DUMMY CODE so skeleton compiles
   }


   /**
    Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
    some of the time this value does not match the actual number of mines currently on the field.  See doc for that
    constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
      return numMine;       // DUMMY CODE so skeleton compiles
   }


   // <put private methods here>
  /* public void printMine() {
      for (boolean[] each : mine) {
         System.out.println(Arrays.toString(each));
      }

      //return mineVal;   // dummy code to get stub to compile

   }
*/

}

