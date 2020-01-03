public class BoardSolver{
  public BoardSolver(Board initial){
    BoardCompare bc = new BoardCompare();
    MinPQ<Board> pq = new MinPQ(bc);
    pq.insert(initial);
    int[][] b = {{1,2,3}, {4,5,6},{7,8,0}};
    Board target = new Board(b);
    while (true){
      Board current = pq.delMin();
      //System.out.println(current);
      int a = current.manhattan() + current.moves;
      if (target.equals(current)){
        System.out.println(current);
        System.out.println(current.moves);
        while (current.parent != null){
          System.out.println(current.parent);
          current = current.parent;
        }
        break;
      }
      else{
        int blankRow = 0;
        int blankCol = 0;
        for(int row = 0; row < current.size(); row++ ){
          for(int col = 0; col < current.size(); col++ ){
            if (current.tileAt(row,col) == 0){
              blankRow = row;
              blankCol = col;
            }
          }
        }
        if (blankRow == 0 || blankRow == 1){
          pq.insert(moveDown(current, blankRow, blankCol));
        }
        if (blankRow == 2 || blankRow == 1){
          pq.insert(moveUp(current, blankRow, blankCol));
        }
        if (blankCol == 2 || blankCol == 1){
          pq.insert(moveRight(current, blankRow, blankCol));
        }
        if (blankCol == 0 || blankCol == 1){
          pq.insert(moveLeft(current, blankRow, blankCol));
        }
      }

    }
  }

  public Board moveLeft(Board initial, int r, int c){
    int[][] b = new int[3][3];
    for(int row = 0; row < initial.size(); row++ ){
      for(int col = 0; col < initial.size(); col++ ){
        b[row][col] = initial.tileAt(row,col);
      }
    }
    int temp = b[r][c];
    b[r][c] = b[r][c+1];
    b[r][c+1] = temp;
    Board b1 = new Board(b, initial.moves + 1,initial);
    return b1;

  }
  public Board moveUp(Board initial, int r, int c){
    int[][] b = new int[3][3];
    for(int row = 0; row < initial.size(); row++ ){
      for(int col = 0; col < initial.size(); col++ ){
        b[row][col] = initial.tileAt(row,col);
      }
    }
    int temp = b[r][c];
    b[r][c] = b[r-1][c];
    b[r-1][c] = temp;
    Board b1 = new Board(b, initial.moves + 1,initial);
    return b1;

  }
  public Board moveDown(Board initial, int r, int c){
    int[][] b = new int[3][3];
    for(int row = 0; row < initial.size(); row++ ){
      for(int col = 0; col < initial.size(); col++ ){
        b[row][col] = initial.tileAt(row,col);
      }
    }
    int temp = b[r][c];
    b[r][c] = b[r+1][c];
    b[r+1][c] = temp;
    Board b1 = new Board(b, initial.moves + 1,initial);
    return b1;

  }
  public Board moveRight(Board initial, int r, int c){
    int[][] b = new int[3][3];
    for(int row = 0; row < initial.size(); row++ ){
      for(int col = 0; col < initial.size(); col++ ){
        b[row][col] = initial.tileAt(row,col);
      }
    }
    int temp = b[r][c];
    b[r][c] = b[r][c-1];
    b[r][c-1] = temp;
    Board b1 = new Board(b, initial.moves + 1,initial);
    return b1;

  }
  public static void main(String[] args) {
    int[][] b = {{8,1,3}, {4,0,2},{7,6,5}};
    Board bo = new Board(b);
    BoardSolver bs = new BoardSolver(bo);
  }
}
