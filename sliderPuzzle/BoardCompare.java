import java.util.Comparator;
public class BoardCompare implements Comparator<Board>{

  public int compare(Board b1, Board b2){
    int a = b1.manhattan() + b1.moves;
    int b = b2.manhattan() + b2.moves;
    if (a < b){
      return -1;
    }
    else if(a==b) {
      return 0;
    }
    else{
      return 1;
    }
  }

}
