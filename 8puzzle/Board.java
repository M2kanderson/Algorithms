// import java.lang.Math.abs;

public class Board {
   public Board(int[][] tiles)        // construct a board from an N-by-N array of tiles
   {
     this.tiles = tiles;
     this.moves = 0;
   }
   public int hamming()               // return number of blocks out of place
   {
     int outOfPlace = 0;
     int N = this.tiles.length;
     for(int i = 0; i < N; i++)
     {
       for(int j = 0; j < N; j++)
       {
         if(this.tiles[i][j] != 0 && this.tiles[i][j] != (i+1) +j*N)
         {
           outOfPlace += 1;
         }
       }
     }
     return outOfPlace;
   }
   public int manhattan()             // return sum of Manhattan distances between blocks and goal
   {
     int N = this.tiles.length;
     int sum = 0;
     for(int i = 0; i < N; i++)
     {
       for(int j = 0; j < N; j++)
       {
         int value = this.tiles[i][j];
         if(value != 0){
           int goalX = (value - 1) % N;
           int goalY = (value - 1) / N;
           sum += Math.abs(i - goalX) + Math.abs(j - goalY);
         }
       }
     }
     return sum;
   }
   public boolean equals(Object y)    // does this board equal y
   {
     int N = this.tiles.length;
     for(int i = 0; i < N; i++)
     {
       for(int j = 0; j < N; j++)
       {
         if(this.tiles[i][j] != y.tiles[i][j])
         {
           return false;
         }
       }
     }
     return true;
   }
   public Iterable<Board> neighbors() // return an Iterable of all neighboring board positions
   {
     Stack<Board> stack = new Stack<Board>();
     int N = this.tiles.length;
     int zeroX = 0;
     int zeroY = 0;
     for(int i = 0; i < N; i++)
     {
       for(int j = 0; j < N; j++)
       {
         if(this.tiles[i][j] == 0)
         {
           zeroX = i;
           zeroY = j;
           break;
         }
       }
     }

     if(zeroX > 0){
       
     }
     if(zeroX < this.tiles.length)
     {

     }
     if(zeroY > 0){

     }
     if(zeroY < this.tiles.length)
     {

     }

   }
   public String toString()           // return a string representation of the board
   {
     int N = this.tiles.length;
     for(int i = 0; i < N; i++)
     {
       String line = "";
       for(int j = 0; j < N; j++)
       {
         line += this.tiles[i][j] + " ";
       }
       System.out.println(line);
     }
   }

   //  test client
   public static void main(String[] args){

   }
}
