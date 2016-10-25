import java.util.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class Solver {
   private int moves;
   private int statesEnqueued;
   private Queue<Board> boards;
   private boolean solvable;
   public Solver(Board initial)   // find a solution to the initial board
   {
       this.solvable = true;
       this.boards = new Queue<Board>();
       Queue<Board> altBoards = new Queue<Board>();
       Board board = initial;
       Board prevBoard = null;
       Board altPrevBoard = null;
       Board altboard = board.altBoard();
       this.boards.enqueue(board);
       altBoards.enqueue(altboard);
       this.moves = 0;
       this.statesEnqueued = 1;
       MinPQ priorityQueue = new MinPQ(4, board.BY_HAMMING);
       MinPQ altPriorityQueue = new MinPQ(4, board.BY_HAMMING);
       while(board.hamming() > 0)
       {
           this.moves += 1;
           Stack<Board> neighbors = (Stack<Board>) board.neighbors();
           Stack<Board> altNeighbors = (Stack<Board>) altboard.neighbors();
           while(neighbors.size() > 0)
           {
               Board neighbor = neighbors.pop();
               
               if(!neighbor.equals(prevBoard))
               {
                   this.statesEnqueued += 1;
                   priorityQueue.insert(neighbor);
               }
               
           }
           while(altNeighbors.size() > 0)
           {
               Board neighbor = altNeighbors.pop();
               
               if(!neighbor.equals(altPrevBoard))
               {
                   altPriorityQueue.insert(neighbor);
               }
               
           }
           prevBoard = board;
           altPrevBoard = altboard;
           board = (Board) priorityQueue.delMin();
           altboard = (Board) altPriorityQueue.delMin();
           if(altboard.hamming() <= 0)
           {
               this.solvable = false;
               break;
           }
           this.boards.enqueue(board);
       }
   }
   public boolean isSolvable()    // is the initial board solvable?
   {
       return this.solvable;
   }
   public Queue<Board> solution()
   {
       return this.boards;
   }
   public int moves()             // return min number of moves to solve the initial board;
   {                              // -1 if no such solution
       return this.moves;
       
   }
   public String toString()       // return string representation of solution (as described above)
   {
       String boardString = "";
       if(isSolvable()){
          while(this.boards.size() > 0)
          {
              boardString += this.boards.dequeue().toString();
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
