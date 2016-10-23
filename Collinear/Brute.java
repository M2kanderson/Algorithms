public class Brute
{
  private final Point[] points;
  public Brute(Point[] points)
  {
    this.points = points;
  }
  
  private static boolean areCollinear(Point a, Point b, Point c, Point d)
  {
      double slopeab = a.slopeTo(b);
      double slopebc = b.slopeTo(c);
      double slopecd = c.slopeTo(d);
      double slopeda = d.slopeTo(a);
      return slopeab == slopebc && slopebc == slopecd && slopecd == slopeda && slopeda == slopeab; 
  }
  
  private static boolean inAscendingOrder(Point a, Point b, Point c, Point d)
  {
     return b.compareTo(a) > 0 && c.compareTo(b) > 0 && d.compareTo(c) > 0;
  }

  public void connectPoints(){
    for(int i = 0; i < this.points.length; i++)
    {
      for(int j = 0; j < this.points.length; j++)
      {
        for(int k = 0; k < this.points.length; k++)
        {
          for(int l = 0; l < this.points.length; l++)
          {
            boolean collinear = areCollinear(this.points[i], this.points[j], this.points[k], this.points[l]);
            boolean ascending = inAscendingOrder(this.points[i], this.points[j], this.points[k], this.points[l]);
            if(collinear && ascending)
            {
             

              this.points[i].drawTo(this.points[l]);

            }
          }
        }
      }
    }
  }
}
