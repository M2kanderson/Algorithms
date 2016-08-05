/******************************************************************************
 *  Compilation:  javac PercolationTester.java
 *  Execution:    java PercolationTester {Grid Size} {Number of Experiments}
 *  Dependencies: PercolationStats.java
 *
 *  This program takes two inputs: N, the grid size and T, the number of experiments.
 *  From those inputs, it
 *
 *    - Presents the mean number of sites open at the time of percolation for all T experiments.
 *    - Presents the standard deviation of the T experiments.
 *    - Presents the High and Low Confidence Levels.
 *
 ******************************************************************************/
public class PercolationTester {
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("Confidence(Low) = " + stats.confidenceLow());
        System.out.println("Confindence(High) = " + stats.confidenceHigh());
    }
}