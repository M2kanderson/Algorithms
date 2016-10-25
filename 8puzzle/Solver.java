import java.util.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import java.util.Comparator;

public class Solver {
    
   private class BoardNode{
       public final Comparator<BoardNode> BY_HAMMING = new ByHamming();
       public final Comparator<BoardNode> BY_MANHATTAN = new ByManhattan();
       private Board board;
       private int moves;
       private BoardNode prev;
       public BoardNode(Board board, BoardNode prev){
           this.board = board;
           this.prev = prev;
           if(prev==null) 
               this.moves = 0;
           else 
               this.moves = prev.moves + 1;
       }
       
       private class ByManhattan implements Comparator<BoardNode>
       {
           public int compare(BoardNode a, BoardNode b)
           {
               int manA = a.board.manhattan() + a.moves;
               int manB = b.board.manhattan() + b.moves;
               return (manA - manB);
           }
       }
       
       private class ByHamming implements Comparator<BoardNode>
       {
           public int compare(BoardNode a, BoardNode b)
           {
               int hamA = a.board.hamming() + a.moves;
               int hamB = b.board.hamming() + b.moves;
               return (hamA - hamB);

           }
       }
       
   };
   private int statesEnqueued;
   private BoardNode finalBoardNode;

   public Solver(Board initial)   // find a solution to the initial board
   {
       this.finalBoardNode = new BoardNode(initial, null);
       if(isSolvable())
       {
           solve();
       }

       
   }
   private void solve()
   {
       this.statesEnqueued = 1;
       BoardNode currentNode = this.finalBoardNode;
       MinPQ priorityQueue = new MinPQ(currentNode.BY_MANHATTAN);
       while(currentNode.board.manhattan() > 0)
       {
           Stack<Board> neighbors = (Stack<Board>) currentNode.board.neighbors();
           while(neighbors.size() > 0)
           {
               Board neighbor = neighbors.pop();
               
               if(currentNode.prev == null || !neighbor.equals(currentNode.prev.board))
               {
                   this.statesEnqueued += 1;
                   priorityQueue.insert(new BoardNode(neighbor, currentNode));
               }
               
           }
           currentNode = (BoardNode) priorityQueue.delMin();
       }
       this.finalBoardNode = currentNode;
   }
   public boolean isSolvable()    // is the initial board solvable?
   {
       return this.finalBoardNode.board.isSolvable();
   }
   public Stack<Board> solution()
   {
       Stack<Board> boards = new Stack<Board>();
       BoardNode currentBoardNode = this.finalBoardNode;
       while(currentBoardNode != null)
       {
           boards.push(currentBoardNode.board);
           currentBoardNode = currentBoardNode.prev;
       }
       Stack<Board> reverseBoards = new Stack<Board>();
       while(boards.size() > 0)
       {
           reverseBoards.push(boards.pop());
       }
       return reverseBoards;
   }
   public int moves()             // return min number of moves to solve the initial board;
   {                              // -1 if no such solution
       if(this.finalBoardNode != null)
       {
           return this.finalBoardNode.moves;
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
