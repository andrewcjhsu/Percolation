package hw2;                       

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
  private double[] thresholds;
  private int experimenttimes;
  private int size;

  public PercolationStats(int N, int T) {
    if (N <= 0 || T <=0) throw new java.lang.IllegalArgumentException("N is out of bounds");
    size = N;
    experimenttimes = T;
    thresholds = new double[experimenttimes];
    for (int i=0; i<T; i++) {
      thresholds[i] = findPercolationThreshold();
    }
  } 
  public double mean() {
    return StdStats.mean(thresholds);
  }
  public double stddev() {
    if (experimenttimes == 1) return Double.NaN;
    return StdStats.stddev(thresholds);
  }

  public double confidenceLo() {
    return mean() - 1.96*stddev()/Math.sqrt(experimenttimes);
  }

  public double confidenceHi() {
    return mean() + 1.96*stddev()/Math.sqrt(experimenttimes);
  }
  
  private double findPercolationThreshold() {
    Percolation percolation = new Percolation(size);
    int i, j;
    int count = 0;
    while (!percolation.percolates()) {
      do {
        i = StdRandom.uniform(size) + 1;
        j = StdRandom.uniform(size) + 1;
      } while (percolation.isOpen(i,j));
      count++;
      percolation.open(i, j);
    }
    return count/(Math.pow(size,2));
  }

  public static void main (String[] args){
  	PercolationStats x= new PercolationStats(5,20);
  	System.out.println(x.mean());
  	System.out.println(x.stddev());
  	System.out.println(x.confidenceLo());
  	System.out.println(x.confidenceHi());
  }
}