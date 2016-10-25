// import java.lang.Math.abs;
import java.util.Stack;
import edu.princeton.cs.algs4.MinPQ;

public class Board {
   private int[][] tiles;

   public Board(int[][] tiles)        // construct a board from an N-by-N array of tiles
   {
     this.tiles = tiles;
   }
   public int tileAt(int row, int col)
   {
       return this.tiles[row][col];
   }
   public int hamming()               // return number of blocks out of place
   {
     int outOfPlace = 0;
     int N = this.tiles.length;
     for(int i = 0; i < N; i++)
     {
       for(int j = 0; j < N; j++)
       {
         if(this.tiles[i][j] != 0 && this.tiles[i][j] != (j+1) +i*N)
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
           sum += Math.abs(j - goalX) + Math.abs(i - goalY);
         }
       }
     }
     return sum;
   }
   
   public boolean equals(Object y)    // does this board equal y
   {
     if(y == this)
         return true;
     if(y == null)
         return false;
     if(y.getClass() != this.getClass())
         return false;
     Board that = (Board) y;
     int N = this.tiles.length;
     for(int i = 0; i < N; i++)
     {
       for(int j = 0; j < N; j++)
       {
         if(this.tiles[i][j] != that.tiles[i][j])
         {
           return false;
         }
       }
     }
     return true;
   }
   
   public boolean isGoal()
   {
     int N = this.tiles.length;
     for(int i = 0; i < N; i++)
     {
       for(int j = 0; j < N; j++)
       {
         if(this.tiles[i][j] != 0 && this.tiles[i][j] != (j+1) +i*N)
         {
           return false;
         }
       }
     }
     return true;
   }
   
   public Board dupBoard(){
       int N = this.tiles.length;
       int[][] newTiles = new int[N][N];
       for(int i = 0; i < N; i++)
       {
           for(int j = 0; j < N; j++)
               newTiles[i][j] = this.tiles[i][j];
       }
       return new Board(newTiles);

   }
   private int[] zeroLocation()
   {
       int N = this.tiles.length;
       int[] zero = new int[2];
       zero[0] = 0;
       zero[1] = 0;
       for(int i = 0; i < N; i++)
       {
           for(int j = 0; j < N; j++)
           {
               if(this.tiles[i][j] == 0)
               {
                   zero[0] = i;
                   zero[1] = j;
                   return zero;
               }
           }
       }
       return zero;
   }
   public Iterable<Board> neighbors() // return an Iterable of all neighboring board positions
   {
     Stack<Board> stack = new Stack<Board>();
     int N = this.tiles.length;
     int[] zero = zeroLocation();
     int zeroX = zero[0];
     int zeroY = zero[1];

     if(zeroX > 0){
       Board newBoard = dupBoard();
       int temp = newBoard.tiles[zeroX - 1][zeroY];
       newBoard.tiles[zeroX - 1][zeroY] = 0;
       newBoard.tiles[zeroX][zeroY] = temp;
       stack.push(newBoard);
     }
     if(zeroX < this.tiles.length - 1)
     {
       Board newBoard = dupBoard();
       int temp = newBoard.tiles[zeroX + 1][zeroY];
       newBoard.tiles[zeroX + 1][zeroY] = 0;
       newBoard.tiles[zeroX][zeroY] = temp;
       stack.push(newBoard);
     }
     if(zeroY > 0){
       Board newBoard = dupBoard();
       int temp = newBoard.tiles[zeroX][zeroY - 1];
       newBoard.tiles[zeroX][zeroY - 1] = 0;
       newBoard.tiles[zeroX][zeroY] = temp;
       stack.push(newBoard);
     }
     if(zeroY < this.tiles.length - 1)
     {
       Board newBoard = dupBoard();
       int temp = newBoard.tiles[zeroX][zeroY + 1];
       newBoard.tiles[zeroX][zeroY + 1] = 0;
       newBoard.tiles[zeroX][zeroY] = temp;
       stack.push(newBoard);
     }
     return stack;

   }
   public String toString()           // return a string representation of the board
   {
     int N = this.tiles.length;
     String finalString = "";
     for(int i = 0; i < N; i++)
     {
       String line = "";
       for(int j = 0; j < N; j++)
       {
         if(this.tiles[i][j] != 0)
         {
             line += this.tiles[i][j] + " ";
         }
         else
         {
             line += "  ";
         }

       }
       finalString += line + "\n";
     }
     return finalString + "\n";
   }

   public boolean isSolvable()
   {
       if(manhattan() == 0)
       {
           return true;
       }
       int N = this.tiles.length;
       int count = 0;
       for(int idx = 0; idx < N * N; idx++)
       {
           int row = idx / N;
           int col = idx % N;
           int currentTile = this.tiles[row][col];
           if(currentTile == 0)
           {
               continue;
           }
           for(int afterIdx = idx + 1; afterIdx < N * N; afterIdx++)
           {
               int afterRow = afterIdx / N;
               int afterCol = afterIdx % N;
               int compareTile = this.tiles[afterRow][afterCol];
               if(compareTile != 0 && compareTile < currentTile)
               {
                   count += 1;
               }
           }
       }
       if(N % 2 == 1)
       {

           return count % 2 == 0;
       }
       else
       {
           int[] zero = zeroLocation();
           int row = zero[0];
           
           int col = zero[1];
           if((N - row) % 2 == 0)
           {
             return count % 2 == 1;
           }
           else
           {
             return count % 2 == 0;
           }
       }

   }

   //  test client
   public static void main(String[] args){


  }
}
