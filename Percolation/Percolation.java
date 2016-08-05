/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    Used in conjunction with PercolationStats and PercolationVisualizer.
 *  See PercolationStats and PercolationVisualizer for execution.
 *  Dependencies: WeightedQuickUnionUF
 *
 *  This program takes one input: N, the grid size.
 *  From the input, it
 *
 *    - Generates a grid of blocked sites
 *    - Blocked sites can be opened through the open(int i, int j) function, where i and j are the position in the grid
 *    - Percolation chan be checked through the percolates() function
 *
 * TODO: This program has a backwash bug. All open sites that are connected to
 * the bottom virtual site are "full" if percolation occurs. This should not occur.
 ******************************************************************************/


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private WeightedQuickUnionUF quf;
   private int size;
   private int[][] grid;
   private int numberOpenSites = 0;
   
   public Percolation(int N) // create N-by-N grid, with all sites blocked
   {
       
       this.grid = new int[N][N];
       this.size = N;
       this.quf = new WeightedQuickUnionUF(N * N + 2);
       for (int i = 0; i < N; i++)
       {
           for (int j = 0; j < N; j++)
           {
               grid[i][j] = 0; //0 is blocked
           }
       }
       
   }
   public int numberOfOpenSites()
   {
       return this.numberOpenSites;
   }
   private int oneDSpace(int i, int j)
   {
       return i * this.size + j;
   }
   
   // open site (row i, column j) if it is not open already
   public void open(int i, int j) 
   {
       if (i > this.size || i < 0 || j > this.size || j < 0)
           throw new java.lang.IndexOutOfBoundsException("i: " + i +", " + "j: " + j);
       int currentPos = oneDSpace(i, j);
       if (this.grid[i][j] == 0) {
           if (i == 0) //if i is in the top row
           {
               //connect it with the top virtual site
               this.quf.union(currentPos, oneDSpace(this.size, 0)); 
           }
           else if (i == this.size - 1) //if i is in the bottom row
           {
               //connect it with the bottom virtual site
               this.quf.union(currentPos, oneDSpace(this.size, 1)); 
           }
           if (i > 0 && this.grid[i - 1][j] == 1) {
               this.quf.union(currentPos, oneDSpace(i - 1, j));
           }
           if (i < this.size - 1 && this.grid[i + 1][j] == 1) {
               this.quf.union(currentPos, oneDSpace(i + 1, j));
           }
           if (j > 0 && this.grid[i][j - 1] == 1) {
               this.quf.union(currentPos, oneDSpace(i, j - 1));
           }
           if (j < this.size - 1 && this.grid[i][j + 1] == 1) {
               this.quf.union(currentPos, oneDSpace(i, j + 1));
           }
           this.numberOpenSites += 1;
           this.grid[i][j] = 1; //1 is open site
       }
   }
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {
       if (i > this.size || i < 0 || j > this.size || j < 0)
           throw new java.lang.IndexOutOfBoundsException("i: " + i +", " + "j: " + j);
       return grid[i][j] == 1;
   }
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {
       if (i >= this.size || i < 0 || j >= this.size || j < 0)
           throw new java.lang.IndexOutOfBoundsException("i: " + i +", " + "j: " + j);
       return this.quf.connected(oneDSpace(i, j), oneDSpace(this.size, 0)) && grid[i][j] == 1;
   }
   public boolean percolates()             // does the system percolate?
   {
       return this.quf.connected(oneDSpace(this.size, 0), oneDSpace(this.size, 1));
   }
}
