import java.util.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class Solver {
   private int statesEnqueued;
   private Stack<Board> boards;

   public Solver(Board initial)   // find a solution to the initial board
   {
       this.boards = new Stack<Board>();
       this.boards.push(initial);
       if(isSolvable())
       {
           solve();
       }

       
   }
   private void solve()
   {

       
       Board board = this.boards.peek();
       Board prevBoard = null;
       
       this.statesEnqueued = 1;
       MinPQ priorityQueue = new MinPQ(4, board.BY_MANHATTAN);
       while(board.manhattan() > 0)
       {
           Stack<Board> neighbors = (Stack<Board>) board.neighbors();
           while(neighbors.size() > 0)
           {
               Board neighbor = neighbors.pop();
               
               if(!neighbor.equals(prevBoard))
               {
                   this.statesEnqueued += 1;
                   priorityQueue.insert(neighbor);
               }
               
           }
           prevBoard = board;
           board = (Board) priorityQueue.delMin();
           if(board.moves() == this.moves())
           {
               this.boards.pop();
           }
           this.boards.push(board);
       }
   }
   public boolean isSolvable()    // is the initial board solvable?
   {
       return this.boards.peek().isSolvable();
   }
   public Stack<Board> solution()
   {
       return this.boards;
   }
   public int moves()             // return min number of moves to solve the initial board;
   {                              // -1 if no such solution
       if(this.boards.peek() != null)
       {
           return this.boards.peek().moves();
       }
       return -1;   
   }
   public String toString()       // return string representation of solution (as described above)
   {
       String boardString = "";
       if(isSolvable()){
          for (Board board : solution()) 
          {
              boardString += board.toString();
          }
          boardString += "Number of states enqueued = " + this.statesEnqueued + "\n";
          boardString += "Number of moves = " + this.moves();
          return boardString; 
       }
       else
       {
           return "No solution possible";
       }
   }

   //  read puzzle instance from stdin and print solution to stdout (in format above)
   public static void main(String[] args)
   {
     String filename = args[0];
     In in = new In(filename);
     int N = in.readInt();
     int[][] tiles = new int[N][N];
     
     for (int i = 0; i < N; i++)
         for (int j = 0; j < N; j++)
             tiles[i][j] = in.readInt();
     Board board = new Board(tiles);
     Solver solution = new Solver(board);
     System.out.println(solution.toString());
   }
}
