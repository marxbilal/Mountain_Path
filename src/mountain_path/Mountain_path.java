package mountain_path;
import java.util.*;
import java.io.*;
import java.awt.*;
import edu.princeton.cs.introcs.*;



/**
 *
 * @author BILAWAL ALI
 */
public class Mountain_path {

    /**
     * @param args the command line arguments
     */
    public  static int[][] data;
    public static void main(String[] args) {
    data=new int[480][844];
   Scanner sc;
   try 
   {
    sc = new Scanner(new File("data.dat"));
    for (int i = 0; i < 480; i++)
    {
     for (int j = 0; j < 844; j++)
     {
      if (sc.hasNextInt())
      {
       data[i][j] = sc.nextInt(); 
       //StdOut.print(data[i][j]+" ");
      }}}}
   catch (FileNotFoundException e) {
     StdOut.println(" Invalid Input");
    e.printStackTrace();
    }
  

     init("data.dat", 480,844);
     int min = findMin();
     int max = findMax();
     int minRow=indexOfMinRow();
    StdDraw.enableDoubleBuffering();
     mapdraw();
      StdDraw.setPenColor(Color.GREEN);
      drawLowestElevPath(minRow);
       mapdraw();
      StdDraw.setPenColor(Color.RED);
     int a= indexOfLowestElevPath();
     StdOut.println(" min "+min+" Max "+max+"  index of min Row"+minRow+" index of lowest"+a);
    drawLowestElevPath(a);
     //StdOut.println(" index of loewstpath" +indexofloewstpath);
     
     }
    public static void init(String filename, int row, int col) {
    data = new int[row][col]; 
   Scanner sc;
   try 
   {
    sc = new Scanner(new File(filename));
    for (int i = 0; i < row; i++)
    {
     for (int j = 0; j < col; j++)
     {
      if (sc.hasNextInt())
      {
       data[i][j] = sc.nextInt(); 
    //   StdOut.print(data[i][j]+" ");
      }}}}
   catch (FileNotFoundException e) {
     StdOut.println(" Invalid Input");
    e.printStackTrace();
    }}
     public static int findMin()
  {
     int min = data[0][0];
   for (int i = 0; i<data.length; i++)
   {
    for (int j = 0; j<data[0].length; j++) 
    {
     if (data[i][j]<min)
     {
      min = data[i][j];
     }}}
   
   //System.out.print(min);
   return min;
  }
     public static int findMax()
     {
       int max = data[0][0];
   for (int i = 0; i<data.length; i++) {
    for (int j = 0; j<data[0].length; j++) {
     if (data[i][j]>max){
      max = data[i][j];
     }}}
  // System.out.print(" " + max);
   return max;
}
     public static int indexOfMinRow()
     { 
       int min = data[0][0];
   int minRow = 0;
      for (int i = 1; i < data.length; i++)
      {
       if (data[i][0] < min)
       {
        min = data[i][0];
        minRow = i;
       }
      }
      return minRow;
}public static void mapdraw()
  {
  StdDraw.setCanvasSize(844,480);
  StdDraw.setXscale(0,data.length);
  StdDraw.setYscale(0,data[0].length);
  int min = findMin();
  int max = findMax();
  for(int i = 0; i < data.length; i++)
    {
      for(int j = 0; j < data[0].length; j++)
      {
     // double b=j;
        int c = 255* (data[i][j]-min)/(max-min);
        StdDraw.setPenColor(new Color(c, c, c));
        StdDraw.filledSquare(j+0.5,data.length-j-0.5,0.5) ;
      }}
  StdDraw.show();
}

  public static int drawLowestElevPath(int row){
   int max = findMax();
   int totalChange = 0;
    StdDraw.setPenColor(Color.RED);
   
   for (int j = 0; j < data[0].length - 1; j++)
   {
    StdDraw.filledSquare(j, row, 0.5);
    int fwd = data[row][j + 1];
    int fwdup = -1;
    int fwddown = -1;
    if (row != 0)
    {
     fwdup =data[row - 1][j + 1];
    }
    if (row != data.length - 1)
    {
     fwddown = data[row + 1][j + 1];
    }
    int currentfwddiff = Math.abs(data[row][j] - fwd);
    int currentfwdupdiff = max + 1;
    int currentfwddowndiff = max + 1;
    if (fwdup > -1)
    {currentfwdupdiff = Math.abs(data[row][j] - fwdup);
    }
    if (fwddown > -1)
    {currentfwddowndiff = Math.abs(data[row][j] - fwddown);
    }
    int least = currentfwddiff;
    if (currentfwddiff > currentfwdupdiff)
    {if (currentfwdupdiff > currentfwddowndiff)
     {least = currentfwddowndiff;
      row++;}
     else{
      least = currentfwdupdiff;
      row--;}}
    else{
     if (currentfwddiff > currentfwddowndiff)
     {
      least = currentfwddowndiff;
      row++;}
     else
     {
      least = currentfwddiff;}
    }
    totalChange += least;}
   //StdDraw.show();
   return totalChange;
  }
 public static int indexOfLowestElevPath(){
     int least = drawLowestElevPath(0);
  // StdDraw.setPenColor(Color.GREEN);
     int index = 0;
        for (int i = 1; i < data.length; i++)
     {
   int change = drawLowestElevPath(i);
      if (change < least)
      {
       least = change;
       index = i;}}
       // StdDraw.show();
  return index;
}
}
