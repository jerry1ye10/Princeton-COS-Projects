import java.util.ArrayList;
public class BeadFinder{
  public double bound;
  public Picture picture;
  public boolean[][] pixelsChecked;
  ArrayList<Blob> blob;
  int width;
  int height;
  public BeadFinder(Picture pic, double tau){
    bound = tau;
    picture = pic;
    width = picture.width();
    height = picture.height();
    pixelsChecked = new boolean[width][height];
    blob = new ArrayList<Blob>();
    for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(!pixelsChecked[i][j]) {
                        double luminance = Luminance.lum(picture.get(i, j));
                        if(luminance >= bound) {
                            Blob b = new Blob();
                            blob.add(b);
                            dfs(b, i, j);
                        }
                }
            }
        }
  }

  //performing depth-first search with recurssion
      public void dfs(Blob b, int x, int y) {
          if(x < 0 || y < 0 || x >= width || y >= height || pixelsChecked[x][y]==true)
              return;

          pixelsChecked[x][y] = true;
          double luminance = Luminance.lum(picture.get(x, y));
          if(luminance >= bound) {

              b.add(x, y);
              dfs(b, x+1, y);
              dfs(b, x-1, y);
              dfs(b, x, y+1);
              dfs(b, x, y-1);
          }
          return;

      }
      //  returns all beads (blobs with >= P pixels)
      public Blob[] getBeads(int P) {
          ArrayList<Blob> beads = new ArrayList<>(); //arraylist of all beads in picture
          for(int i = 0; i < blob.size(); i++) {
              if(blob.get(i).mass() >= P)
                  beads.add(blob.get(i));
          }
          Blob[] beads_array = new Blob[beads.size()];
          beads_array = beads.toArray(beads_array);  //converting arraylist to array of beads
          return beads_array;
      }

      //  unit tests the BeadFinder data type, as described below
      public static void main(String[] args) {
          int P = Integer.parseInt(args[0]);
          double bound = Double.parseDouble(args[1]);
          String picture_name = args[2];

          Picture picture = new Picture(picture_name);
          BeadFinder bf = new BeadFinder(picture, bound);

          Blob[] beads_array = bf.getBeads(P);
          for(int i = 0; i < beads_array.length; i++) {
              System.out.println(beads_array[i].toString());
          }
      }


}
