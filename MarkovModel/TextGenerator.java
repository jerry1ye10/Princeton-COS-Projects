import java.util.Scanner;
public class TextGenerator{

  public static void main(String[] args){
    int k = Integer.parseInt(args[0]);
    int l = Integer.parseInt(args[1]);
    Scanner in = new Scanner(System.in);
    String s = "";
    String line = new String();
    while(in.hasNext())//while there are lines on the file
    {
      line = in.nextLine();
      s += line;
    }
    //System.out.println(s);
    MarkovModel m2 = new MarkovModel(s, k);
    String retString = s.substring(0, k+1);
    for (int i = k; i <= l; i++){
      String sub = retString.substring(retString.length() - k, retString.length());
      String newChar = String.valueOf(m2.random(sub));
      retString += newChar;
    }
    System.out.println(retString);
  }
}
