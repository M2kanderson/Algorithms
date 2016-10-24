// import java.lang.Math.abs;
import java.util.Stack;

public class Board {
   private int[][] tiles;
   private int moves;
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
   private Board dupBoard(){
       int N = this.tiles.length;
       int[][] newTiles = new int[N][N];
       for(int i = 0; i < N; i++)
       {
           for(int j = 0; j < N; j++)
               newTiles[i][j] = this.tiles[i][j];
       }
       return new Board(newTiles);
           
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
       Board newBoard = dupBoard();
       int temp = newBoard.tiles[zeroX - 1][zeroY];
       newBoard.tiles[zeroX - 1][zeroY] = 0;
       newBoard.tiles[zeroX][zeroY] = temp;
       stack.push(newBoard);
     }
     if(zeroX < this.tiles.length)
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
     if(zeroY < this.tiles.length)
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
     System.out.println(this.tiles[0][2]);
     int N = this.tiles.length;
     String finalString = "";
     for(int i = 0; i < N; i++)
     {
       String line = "";
       for(int j = 0; j < N; j++)
       {
         line += this.tiles[i][j] + " ";
       }
       finalString += line + "\n";
     }
     return finalString;
   }

   //  test client
   public static void main(String[] args){
     int N = 3;
     int[][] tiles = new int[N][N];
//     for (int i = 0; i < N; i++)
//         for (int j = 0; j < N; j++)
//             tiles[i][j] = j + i*N + 1;
     tiles[0][0] = 8;
     tiles[0][1] = 1;
     tiles[0][2] = 3;
     tiles[1][0] = 4;
     tiles[1][1] = 0;
     tiles[1][2] = 2;
     tiles[2][0] = 7;
     tiles[2][1] = 6;
     tiles[2][2] = 5;
     System.out.println(tiles[0][2]);
     Board board = new Board(tiles);
     System.out.println(board.toString());
     System.out.println(board.hamming());
     System.out.println(board.manhattan());
     Stack<Board> neighbors = (Stack<Board>) board.neighbors(); 
     while(neighbors.size() > 0)
     {
         
         System.out.println(neighbors.pop().toString());
     }
     
     
   }
}
