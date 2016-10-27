import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
import java.util.Stack;
import edu.princeton.cs.algs4.In;

public class PointSET {
   private SET<Point2D> points;
   public PointSET()                               // construct an empty set of points
   {
     this.points = new SET<Point2D>();
     
   }
   public boolean isEmpty()                      // is the set empty?
   {
     return this.points.isEmpty();
   }
   public int size()                         // number of points in the set
   {
     return this.points.size();
   }
   public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   {
     this.points.add(p);
   }
   public boolean contains(Point2D p)            // does the set contain point p?
   {
     return this.points.contains(p);
   }
   public void draw()                         // draw all points to standard draw
   {
     for(Point2D point : this.points)
     {
       point.draw();
     }
   }
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle
   {
     Stack<Point2D> stack = new Stack<Point2D>();
     for(Point2D point : this.points)
     {
       if(rect.contains(point))
       {
         stack.push(point);
       }
     }
     return stack;
   }
   public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
   {
     Point2D nearestPoint = null;
     double nearestDistance = Double.POSITIVE_INFINITY;
     for(Point2D point: this.points)
     {
       double dist = p.distanceTo(point);
       if(dist < nearestDistance)
       {
         nearestPoint = point;
         nearestDistance = dist;
       }
     }
     return nearestPoint;
   }

   public static void main(String[] args)                  // unit testing of the methods (optional)
   {
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       StdDraw.show(0);
       
       String filename = args[0];
       In in = new In(filename);
       int N = in.readInt();
       Point2D[] points = new Point2D[N];
       for (int i = 0; i < N; i++) {
           int x = in.readInt();
           int y = in.readInt();
           Point2D p = new Point2D(x, y);
           points[i] = p;
       }
       
       PointSET set = new PointSET();
       for(int i = 0; i < points.length; i++)
       {
           set.insert(points[i]);
       }
       set.draw();

   }
}
