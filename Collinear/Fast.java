import java.util.Arrays;

public class Fast
{
  private final Point[] points;
  public Fast(Point[] points)
  {
    this.points = points;
  }
  
  private static boolean inAscendingOrder(Point a, Point b, Point c, Point d)
  {
     return (b.compareTo(a) > 0  && c.compareTo(b) > 0 && d.compareTo(c) > 0);
  }
  
  public void connectPoints()
  {
      for(int i = 0; i < this.points.length; i++)
      {
          Point[] points = this.points.clone();
          Point currentPoint = points[i];
          Arrays.sort(points, currentPoint.POLAR_ORDER);
          for(int j = 0; j < points.length - 2; j++)
          {
              if(currentPoint.slopeTo(points[j]) == currentPoint.slopeTo(points[j + 2]))
              {
                  int k  = j;
                  Point min = currentPoint;
                  Point max = currentPoint;
                  while(k < points.length && currentPoint.slopeTo(points[j]) == currentPoint.slopeTo(points[k]))
                  {
                      if(min.compareTo(points[k]) > 0)
                      {
                          min = points[k];
                      }
                      if(max.compareTo(points[k]) < 0)
                      {
                          max = points[k];
                      }
                      k++;
                  }
                  if(min == currentPoint)
                  {
                      min.drawTo(max);
                  }
                  j = k - 1;
                  
              }
          }
          
      }
  }
}