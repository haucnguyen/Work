package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double means;
    private double stddevs;
    private double confidenceLows;
    private double confidenceHighs;
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
        stddevs = StdStats.stddev(num);
        means = StdStats.mean(num);
        confidenceLows = means - (1.96 * stddevs) / Math.sqrt(trials);
        confidenceHighs = means + (1.96 * stddevs) / Math.sqrt(trials);
    }

    public double mean() {
        return means;
    }

    public double stddev() {
        return stddevs;
    }

    public double confidenceLow() {
        return confidenceLows;
    }

    public double confidenceHigh() {
        return confidenceHighs;
    }
}
