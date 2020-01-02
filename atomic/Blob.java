import java.lang.Math;
public class Blob{
  public int numPixels;
  public double totalX;
  public double totalY;

  public Blob(){ //  creates an empty blob
    totalY = 0;
    totalX = 0;
    numPixels = 0;
  }
  public void add(int x, int y){//  adds pixel (x, y) to this blob
    totalX += x;
    totalY += y;
    numPixels += 1;
  }
  public int mass(){ //  number of pixels added to this blob
    return numPixels;
  }
  public double getCenterX(){ //gets x coordinate of center of mass
    return totalX / numPixels;
  }
  public double getCenterY(){ //gets y coordinate of center of mass
    return totalY/numPixels;
  }
  public double distanceTo(Blob that){ //  Euclidean distance between the center of masses of the two blobs
    return Math.sqrt( Math.pow(that.getCenterX() - this.getCenterX(),2) + Math.pow(that.getCenterY() - this.getCenterY(),2));
  }
  public String toString() {
    String retString = "";
    retString += String.valueOf(this.mass());
    retString += " (";
    double centerX = getCenterX();
    centerX *= 1000;
    int cenX = (int)centerX;
    centerX = (double) cenX;
    centerX /= 1000;
    double centerY = getCenterY();
    centerY *= 1000;
    int cenY = (int)centerY;
    centerY = (double) cenY;
    centerY /= 1000;
    retString += String.valueOf(centerX);
    retString += ",";
    retString += String.valueOf(centerY);
    retString += ")";
    return retString;
  }
  public static void main(String[] args){
    Blob b = new Blob();
    b.add(10, 100);
    b.add(1,10);
    b.add(3,11);
    b.add(13,17);
    System.out.println(b);

}
}
