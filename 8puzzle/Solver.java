public class Solver {
   public Solver(Board initial)   // find a solution to the initial board
   public boolean isSolvable()    // is the initial board solvable?
   public int moves()             // return min number of moves to solve the initial board;
                                  // -1 if no such solution
   public String toString()       // return string representation of solution (as described above)

   //  read puzzle instance from stdin and print solution to stdout (in format above)
   public static void main(String[] args)
   {
     int N = StdIn.readInt();
     int[][] tiles = new int[N][N];
     for (int i = 0; i < N; i++)
         for (int j = 0; j < N; j++)
             tiles[i][j] = StdIn.readInt();
     Board board = new Board(tiles);
     Solver solution = new Solver(board);
   }
}
