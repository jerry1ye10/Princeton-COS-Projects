import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class Board{
    public int[][] board;
    public int moves;
    public Board parent;

    public Board(int[][] tiles){
      board = tiles;
      moves = 0;
      parent = null;
    }
    public Board(int[][] tiles,int m, Board b){
      board = tiles;
      moves = m;
      parent = b; 
    }


    // string representation of this board
    public String toString(){
      return (Arrays.deepToString(board));

    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int row, int col){
      return board[row][col];
    }

    // board size n
    public int size(){
      return board.length;
    }

    // number of tiles out of place
    public int hamming(){
      int totalDistance = 0;
      int correctNum = 1;
      for(int row = 0; row < size(); row++ ){
        for(int col = 0; col < size(); col++ ){
            if (correctNum != this.tileAt(row,col)){
              totalDistance += 1;
            }
            correctNum += 1;
        }
      }
      return totalDistance-1;
    }
    public int singleManhattan(int num, int r, int c){
      int correctRow = (num/this.size());
      int correctCol = (num % this.size()) - 1;
      return Math.abs(correctCol - c) + Math.abs(correctRow - r);

    }

    public int manhattan(){
      int totalDistance = 0;
      int correctNum = 1;
      for(int row = 0; row < size(); row++ ){
        for(int col = 0; col < size(); col++ ){
            if (correctNum != this.tileAt(row,col)){
              if (this.tileAt(row,col)!= 0){
              totalDistance += singleManhattan(this.tileAt(row,col), row, col);
            }
            }
            correctNum += 1;
        }
      }
      return totalDistance;
    }

    // is this board the goal board?
    public boolean isGoal(){
      return this.hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Board y){
      for(int row = 0; row < size(); row++ ){
        for(int col = 0; col < size(); col++ ){
          if (this.tileAt(row,col) != y.tileAt(row,col)){
            return false;
          }
        }}
        return true;
    }


    // is this board solvable?
    public boolean isSolvable(){
      if (this.size() % 2 == 0){
        return this.evenIsSolvable();
      }
      else{
        return this.oddIsSolvable();
      }
    }

    public static int numInversions(int[] arr){ //n^2logn run time through "divide and conquer" algorithm
      if (arr.length <= 1){
        return 0;
      }
      int[] leftArray = Arrays.copyOfRange(arr, 0, arr.length/2);
      int[] rightArray = Arrays.copyOfRange(arr, arr.length/2, arr.length);
      int left = numInversions(leftArray);
      int right = numInversions(rightArray);
      int mergeInversions = 0;
      Arrays.sort(leftArray);
      Arrays.sort(rightArray);
      ArrayList leftArraySorted = new ArrayList();
      for (int i = 0; i < leftArray.length; i++){
        leftArraySorted.add(leftArray[i]);
      }
      ArrayList rightArraySorted = new ArrayList();
      for (int i = 0; i < rightArray.length; i++){
        rightArraySorted.add(rightArray[i]);
      }
      //ArrayList leftArraySorted = Arrays.asList(leftArray);
      //ArrayList rightArraySorted = Arrays.asList(rightArray);
      while (leftArraySorted.size() != 0 && rightArraySorted.size() != 0){
        if ((int)leftArraySorted.get(0) > (int) rightArraySorted.get(0)){
          rightArraySorted.remove(0);
          mergeInversions += leftArraySorted.size();
          //System.out.println(leftArraySorted);
          //System.out.println(rightArraySorted);
        }
        else{
          leftArraySorted.remove(0);
        }
      }
      //System.out.print(arr.length);
      //System.out.println(left + right + mergeInversions);
      return left + right + mergeInversions;



    }
    public boolean oddIsSolvable(){
      int[] arrBoard = new int[board.length * board.length - 1];
      int i = 0;
      for(int row = 0; row < size(); row++ ){
        for(int col = 0; col < size(); col++ ){
          if (board[row][col] != 0){
            arrBoard[i] = board[row][col];
            //System.out.println(i);
            i+=1;
          }
        }
      }
      //System.out.println(arrBoard[0]);
      //System.out.println(arrBoard[1]);
      return numInversions(arrBoard) %2 == 0;
    }
    public boolean evenIsSolvable(){
      int[] arrBoard = new int[board.length * board.length - 1];
      int i = 0;
      int blankRow = 0;
      for(int row = 0; row < size(); row++ ){
        for(int col = 0; col < size(); col++ ){
          if (board[row][col] != 0){
            arrBoard[i] = board[row][col];
            i+=1;
          }
          else{
            blankRow = row;
          }
        }
      }
      return (numInversions(arrBoard) %2) + blankRow == 1;
    }

    // unit testing (required)
    public static void main(String[] args){
      int[] arr = {1,2,3,4,6,8,5,7};
      int[] arr2 = {1,3,4,2,5,7,8,6};
      System.out.println(numInversions(arr));
      System.out.println(numInversions(arr2));
      int[][] b = {{8,1,3}, {4,0,2},{7,6,5}};
      int[][] b2 = {{1,2,3,4}, {5,6,0,8},{9,10,7,11},{13,14,15,12}};
      Board bo = new Board(b);
      System.out.println(bo);
      System.out.println(bo.isSolvable());
      Board bo2 = new Board(b2);
      System.out.println(bo2);
      System.out.println(bo2.isSolvable());
      System.out.println(bo.hamming());
      System.out.println(bo.manhattan());
      System.out.println(bo2.hamming());
      System.out.println(bo2.manhattan());
    }

}
