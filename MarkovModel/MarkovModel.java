import java.util.HashMap;
//import java.util.map;
import java.util.*;

public class MarkovModel {

    // creates a Markov model of order k for the specified text
  private String text;
  private int order;
  private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
  public MarkovModel(String t, int k){
    text = t;
    order = k;
    generateMap();
      }
  public boolean generateMap(){
    //System.out.println(text);
    for(int i = 0; i< text.length();i++){
      int mod = text.length();
      if (i + order >= mod){
        String str1 = text.substring(i, text.length());
        String str2 = text.substring(0, (i + order) % mod );
        String str = str1 + str2;
        if (map.containsKey(str)){
          map.get(str).add(text.substring((i+order) % mod ,(i+order+1) % mod ));
        }
        else {
          ArrayList newArrList = new ArrayList<String>();
          newArrList.add(text.substring((i+order) % mod ,(i+order+1) % mod));
          map.put(str, newArrList);
        }
        return true;
      }
      String str = text.substring(i%mod,(i+order));
      if (map.containsKey(str)){
        map.get(str).add(text.substring(i+order,(i+order+1)));
      }
      else {
        ArrayList newArrList = new ArrayList<String>();
        newArrList.add(text.substring((i+order),i+order+1));
        map.put(str, newArrList);
      }
    }
    return true;
  }

    // returns the order k of this Markov model
  public int order(){
    return order;
  }
    // returns a string representation of the Markov model (as described below)
  public String toString(){
    String retStr = new String();
    for (String key: map.keySet()){
      System.out.print(key);
      System.out.println(map.get(key));
    }
    return "";
  }

    // returns the number of times the specified kgram appears in the text
  public int freq(String kgram){
    return map.get(kgram).size();
  }

    // returns the number of times the character c follows the specified
    // kgram in the text
  public int freq(String kgram, char c){
    int retInt = 0;
    for (String s: map.get(kgram)){
      if (c == s.charAt(0)){
        retInt += 1;
      }
    }
    return retInt;
  }
    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
  public char random(String kgram){
    Random rand = new Random();
    ArrayList<String> arrList = map.get(kgram);
    int index = rand.nextInt(arrList.size());
    String s = arrList.get(index);
    return s.charAt(0);
  }

    // tests this class by directly calling all instance methods
  public static void main(String[] args){
    MarkovModel m = new MarkovModel("gagggagaggcgagaaa", 2);
    System.out.println(m.random("cg"));
    System.out.println(m);
    System.out.println(m.freq("aa"));
    System.out.println(m.freq("aa", 'c'));
  }
}
