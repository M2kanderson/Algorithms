/*************************************************************************
 * Name:
 * Login:
 * Precept:
 *
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if(this.y == that.y)
        {
            return 0.0;
        }
        else if(this.x == that.x)
        {
            return Double.POSITIVE_INFINITY;
        }
        return (this.y - that.y)/ (double) (this.x - that.x);
    }

    private static class SlopeOrder implements Comparator<Point>
    {
      public int compare(Point a, Point b)
      {
        double slope = a.slopeTo(b);
        if(slope < 0)
        {
            return -1;
        }
        else if(slope > 0)
        {
            return +1;
        }
        else
        {
            return 0;
        }
      }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if(this.y < that.y){
          return -1;
        }
        else if(this.y > that.y){
          return +1;
        }
        else if(this.x < that.x){
          return -1;
        }
        else if(this.x > that.x){
          return +1;
        }
        else{
          return 0;
        }
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
