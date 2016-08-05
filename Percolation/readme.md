/******************************************************************************
 *  Name:    Justin Anderson
 *  Date:    August 5, 2016
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Operating system:
 *  Compiler: OSX 10.10.5
 *  Text editor / IDE: DrJava
 *
 *
 *  Hours to complete assignment: 4 hours
 *
 ******************************************************************************/


/******************************************************************************
 *  Implementation
 *****************************************************************************/

Percolation takes in a grid size and generates an N-by-N grid of closed sites.
The sites are either randomly set to open through PercolationStats.java, or are
set through a text input by PercolationVisualizer.java. Two virtual sites are
generated for the top and bottom of the grid.  If the two virtual sites are
connected, percolation has occurred.  Connections are checked using
WeightedQuickUnionUF.  As sites are opened, all adjacent sites are connected to
the opened site. WeightedQuickUnionUF stores a root index for each connected site.
The roots are compared in constant time to determine connection.


/******************************************************************************
 * Performance with QuickFindUF.java
 *****************************************************************************/

(keep T constant)

 N          time (seconds)
------------------------------
100         1.447
200	    20.374
400         385.984



(keep N constant)

 T          time (seconds)
------------------------------
100	    1.487
200  	    2.879
400	    5.563
800	    10.884
1600	    22.630


running time as a function of N and T:  ~ 3.71E-9 * N ^ 4.24
					~ 0.64 * T ^ 1.06


/******************************************************************************
 *  Performance with WeightedQuickUnionUF.java.
 *****************************************************************************/

(keep T constant)

 N          time (seconds)
------------------------------
100         0.214
200	    0.461
400	    1.323
800         10.738
1600        63.449



(keep N constant)

 T          time (seconds)
------------------------------
100         0.065
200         0.126
400         0.310
800	    0.553
1600	    1.055


running time as a function of N and T:  ~ 3.98E-7 * N^2.56  
					~ 1.11E-3 * T^0.93



/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

This program has a backwash bug. All open sites that are connected to
the bottom virtual site are "full" if percolation occurs. This should not occur.
