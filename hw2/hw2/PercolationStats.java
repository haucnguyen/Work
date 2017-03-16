package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;
    private double[] num;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        num = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation world = new Percolation(n);
            double count = 0;
            while (!world.percolates()) {
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);
                if (!world.isOpen(x, y)) {
                    world.open(x, y);
                    count++;
                }
            }
            num[i] = (count / (n * n));
        }
        stddev = StdStats.stddev(num);
        mean = StdStats.mean(num);
        confidenceLow = mean - (1.96 * stddev) / Math.sqrt(trials);
        confidenceHigh = mean + (1.96 * stddev) / Math.sqrt(trials);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLow() {
        return confidenceLow;
    }

    public double confidenceHigh() {
        return confidenceHigh;
    }
}