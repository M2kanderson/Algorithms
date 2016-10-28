import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
public class KdTree {
    private class Node{
        private String orientation;
        private Point2D val;
        private Node left;
        private Node right;
        public Node(Point2D val, String orientation)
        {
            this.orientation = orientation;
            this.val = val;
            this.left = null;
            this.right = null;
        }
        public String orientation(){
          return this.orientation;
        }
        public Point2D val(){
          return this.val;
        }
    }
   private Node root;
   private int size;
   public KdTree()                               // construct an empty set of points
   {
       this.root = null;
       this.size = 0;
   }
   public boolean isEmpty()                      // is the set empty?
   {
     return root == null;
   }
   public int size()                         // number of points in the set
   {
     return this.size;
   }
   public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   {
       if(root == null)
       {
           root = new Node(p, "vertical");
           this.size += 1;
       }
       else if(p.x() <= root.val().x())
       {
          root.left = put(root.left, p, "vertical");
       }
       else
       {
         root.right = put(root.right, p, "vertical");
       }
   }
   private Node put(Node node, Point2D p, String lastOrientation)
   {
      if(node == null)
      {
        String orientation = lastOrientation == "vertical" ? "horizontal" : "vertical";
        this.size += 1;
        return new Node(p, orientation);
      }
      else if(node.orientation == "vertical")
      {
        if(p.x() <= node.val().x())
        {
          node.left = put(node.left, p, "vertical");
        }
        else
        {
          node.right = put(node.right, p, "vertical");
        }
      }
      else if(node.orientation == "horizontal")
      {
        if(p.y() <= node.val().y())
        {
          node.left = put(node.left, p, "horizontal");
        }
        else
        {
          node.right = put(node.right, p, "horizontal");
        }
      }
      return node;
   }
   public boolean contains(Point2D p)            // does the set contain point p?
   {
     if(root == null)
     {
       return false;
     }
     else if(root.val().equals(p))
     {
       return true;
     }
     else
     {
       if(p.x() <= root.val().x())
       {
         return contains(root.left, p);
       }
       else
       {
         return contains(root.right, p);
       }
     }
   }
   private boolean contains(Node node, Point2D p)
   {
     if(node == null){
       return false;
     }
     else if(node.val().equals(p))
     {
       return true;
     }
     else
     {
       if(node.orientation() == "vertical")
       {
         if(p.x() <= node.val().x())
         {
           return contains(node.left, p);
         }
         else
         {
           return contains(node.right, p);
         }
       }
       else
       {
         if(p.y() <= node.val().y())
         {
           return contains(node.left, p);
         }
         else
         {
           return contains(node.right, p);
         }
       }

     }
   }
   public void draw()                         // draw all points to standard draw
   {
       if(!isEmpty())
       {
           this.root.val().draw();
           if(root.left != null)
           {
               draw(root.left);
           }
           if(root.right != null)
           {
               draw(root.right);
           }
       }
       
   }
   private void draw(Node node)
   {
       if(node != null)
       {
           node.val().draw();
           if(node.left != null)
           {
               draw(node.left);
           }
           if(node.right != null)
           {
               draw(node.right);
           }
       }
       
   }
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle
   {
       Stack<Point2D> stack = new Stack<Point2D>();
       
       if(rect.contains(this.root.val()))
       {
           stack.push(this.root.val());
           stack.addAll(range(rect, this.root.left));
           stack.addAll(range(rect, this.root.right));
       }
       else if(rect.xmax() >= this.root.val().x() && rect.xmin() <= this.root.val().x())
       {
           stack.addAll(range(rect, this.root.left));
           stack.addAll(range(rect, this.root.right));
       }
       else if(rect.xmax() <= this.root.val().x())
       {
           stack.addAll(range(rect, this.root.left));
       }
       else if(rect.xmin() > this.root.val().x())
       {
           stack.addAll(range(rect, this.root.right));
       }
       return stack;
   }
   private Stack<Point2D> range(RectHV rect, Node node)
   {
       Stack<Point2D> stack = new Stack<Point2D>();
       if(node != null)
       {
           if(rect.contains(node.val()))
           {
               stack.push(node.val());
               stack.addAll(range(rect, node.left));
               stack.addAll(range(rect, node.right));
           }
           else if(node.orientation() == "vertical")
           {
               if(rect.xmax() >= node.val().x() && rect.xmin() <= node.val().x())
               {
                   stack.addAll(range(rect, node.left));
                   stack.addAll(range(rect, node.right));
               }
               else if(rect.xmax() <= node.val().x())
               {
                   stack.addAll(range(rect, node.left));
               }
               else if(rect.xmin() >= node.val().x())
               {
                   stack.addAll(range(rect, node.right));
               }
           }
           else
           {
               if(rect.ymax() >= node.val().y() && rect.ymin() <= node.val().y())
               {
                   stack.addAll(range(rect, node.left));
                   stack.addAll(range(rect, node.right));
               }
               else if(rect.ymax() <= node.val().y())
               {
                   stack.addAll(range(rect, node.left));
               }
               else if(rect.ymin() >= node.val().y())
               {
                   stack.addAll(range(rect, node.right));
               }
           }
       }
       return stack;
       
       
       
   }
   public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
   {
       Point2D closestPoint = this.root.val();
       Point2D nodePoint = this.root.val();
       if(this.root.val().equals(p))
       {
           return this.root.val();
       }
       else if(p.x() < nodePoint.x())
       {
           Point2D nearestLeftPoint = nearest(this.root.left, p, closestPoint);
           if(nearestLeftPoint.distanceTo(p) < closestPoint.distanceTo(p))
           {
               return nearestLeftPoint;
           }
           
           Point2D nearestRightPoint = nearest(this.root.right, p, closestPoint);
           return nearestRightPoint;
       }
       else
       {
           Point2D nearestRightPoint = nearest(this.root.right, p, closestPoint);
           if(nearestRightPoint.distanceTo(p) < closestPoint.distanceTo(p))
           {
               return nearestRightPoint;
           }
           
           Point2D nearestLeftPoint = nearest(this.root.left, p, closestPoint);
           return nearestLeftPoint;
       }
   }
   
   private Point2D nearest(Node node, Point2D p, Point2D closestPoint)
   {
       if(node == null)
       {
           return closestPoint;
       }
       Point2D nodePoint = node.val();
       if(nodePoint.equals(p))
       {
           return nodePoint;
       }
       if(nodePoint.distanceTo(p) < closestPoint.distanceTo(p))
       {
           closestPoint = nodePoint;
       }
       if(node.orientation() == "vertical")
       {
           System.out.println("vert");
           if(p.x() < nodePoint.x())
           {
               Point2D nearestLeftPoint = nearest(node.left, p, closestPoint);
               if(nearestLeftPoint.distanceTo(p) < closestPoint.distanceTo(p))
               {
                   return nearestLeftPoint;
               }
               
               Point2D nearestRightPoint = nearest(node.right, p, closestPoint);
               return nearestRightPoint;
           }
           else
           {
               Point2D nearestRightPoint = nearest(node.right, p, closestPoint);
               if(nearestRightPoint.distanceTo(p) < closestPoint.distanceTo(p))
               {
                   return nearestRightPoint;
               }
               
               Point2D nearestLeftPoint = nearest(node.left, p, closestPoint);
               return nearestLeftPoint;
           }
       }
       else
       {
           System.out.println("horiz");
           if(p.y() < nodePoint.y())
           {
               Point2D nearestLeftPoint = nearest(node.left, p, closestPoint);
               if(nearestLeftPoint.distanceTo(p) < closestPoint.distanceTo(p))
               {
                   return nearestLeftPoint;
               }
               
               Point2D nearestRightPoint = nearest(node.right, p, closestPoint);
               return nearestRightPoint;
           }
           else
           {
               Point2D nearestRightPoint = nearest(node.right, p, closestPoint);
               if(nearestRightPoint.distanceTo(p) < closestPoint.distanceTo(p))
               {
                   return nearestRightPoint;
               }
               
               Point2D nearestLeftPoint = nearest(node.left, p, closestPoint);
               return nearestLeftPoint;
           }
       }
   }

   public static void main(String[] args)                  // unit testing of the methods (optional)
   {
        String filename = args[0];
        In in = new In(filename);


        // initialize the data structures with N points from standard input
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }
        
        double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
        double x1 = 0.0, y1 = 0.0;      // current location of mouse
        boolean isDragging = false;     // is the user dragging a rectangle
        
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        kdtree.draw();
        StdDraw.show();
                RectHV rect = new RectHV(Math.min(0, 0.5), Math.min(0, 0.5),
                                     Math.max(0, 0.5), Math.max(0, 0.5));       
        StdDraw.setPenRadius(.02);
            StdDraw.setPenColor(StdDraw.BLUE);
            for (Point2D p : kdtree.range(rect))
            {
//                System.out.println(p.toString());
                p.draw();
            }
        
//        while(true)
//        {
//            if (StdDraw.mousePressed() && !isDragging) {
//                x0 = StdDraw.mouseX();
//                y0 = StdDraw.mouseY();
//                isDragging = true;
//                continue;
//            }
//            else if (StdDraw.mousePressed() && isDragging) {
//                x1 = StdDraw.mouseX();
//                y1 = StdDraw.mouseY();
//                StdDraw.clear();
//                StdDraw.setPenColor(StdDraw.BLACK);
//                StdDraw.setPenRadius(0.01);
//                kdtree.draw();
//                RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
//                                     Math.max(x0, x1), Math.max(y0, y1));
//                StdDraw.setPenColor(StdDraw.BLACK);
//                StdDraw.setPenRadius();
//                rect.draw();
//                StdDraw.show();
//                StdDraw.pause(40);
//                continue;
//            }
//            else if (!StdDraw.mousePressed() && isDragging) {
//
//                isDragging = false;
//            }
//
//            RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
//                                     Math.max(x0, x1), Math.max(y0, y1));
//                        // draw the points
//            StdDraw.clear();
//            StdDraw.setPenColor(StdDraw.BLACK);
//            StdDraw.setPenRadius(0.01);
//            kdtree.draw();
//
//            // draw the rectangle
//            StdDraw.setPenColor(StdDraw.BLACK);
//            StdDraw.setPenRadius();
//            rect.draw();
//            StdDraw.show();
//            StdDraw.pause(40);
//        }
   }
}
