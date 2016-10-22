/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    Used in conjunction with PercolationTester. See PercolationTester for execution
 *  Dependencies: Percolation.java
 *
 *  This program takes two inputs: N, the grid size and T, the number of experiments.
 *  From those inputs, it
 *
 *    - Calculates the mean number of sites open at the time of percolation for all T experiments.
 *    - Calculates the standard deviation of the T experiments.
 *    - Calculates the High and Low Confidence Levels.
 *
 ******************************************************************************/


import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
   private int openCount;
   private int[] counts;
   private int experiments;
   private double mean = -1;
   private double stddev = -1;
   private int size;
   
   public PercolationStats(int N, int T)   // perform T independent experiments on an N-by-N grid
   {
       if (N <= 0 || T <= 0)
           throw new java.lang.IllegalArgumentException();
       int index = 0;
       this.experiments = T;
       this.size = N;
       this.counts = new int[T];
       while (index < T) {
           this.openCount = 0;
           Percolation grid = new Percolation(N);
           while (!grid.percolates()) {
             int i = StdRandom.uniform(N) + 1;
             int j = StdRandom.uniform(N) + 1;
             if (!grid.isOpen(i, j)) {
                 grid.open(i, j);
                 this.openCount++;
             }  
           }
           this.counts[index] = this.openCount;
           index++;
       }
   }
   public double mean()                    // sample mean of percolation threshold
   {
       if (this.mean == -1)
       {
           double sum = 0;
           for (int i = 0; i < this.experiments; i++)
           {
               sum += this.counts[i] / (1.0 * this.size * this.size);
           }
           this.mean = sum / (this.experiments);
       }
       return this.mean;
   }
   public double stddev()                  // sample standard deviation of percolation threshold
   {
       if (this.stddev == -1)
       {
           double diffSum = 0;
           for (int i = 0; i < this.experiments; i++)
           {
               diffSum += Math.pow(this.counts[i] / (1.0 * this.size * this.size) - mean(), 2);
           }
           this.stddev = Math.sqrt(diffSum / (this.experiments - 1));
       }
       
       return this.stddev;
   }
   public double confidenceLo()           // low  endpoint of 95% confidence interval
   {
       return mean() - 1.96*stddev()/Math.sqrt(this.experiments);
   }
   public double confidenceHi()          // high endpoint of 95% confidence interval
   {
       return mean() + 1.96*stddev()/Math.sqrt(this.experiments);
   }
   
   public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("Confidence(Low) = " + stats.confidenceLo());
        System.out.println("Confindence(High) = " + stats.confidenceHi());
    }
}