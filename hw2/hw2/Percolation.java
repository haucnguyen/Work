package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.reflect.Array;

public class Percolation {
    int openSites = 0;
    Boolean world[][];
    int worldSize;
    int topSite;
    int bottomSite;


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        } else {
            worldSize = N;
            topSite = N * N + 1;
            bottomSite = N * N + 2;
            WeightedQuickUnionUF LOLOL = new WeightedQuickUnionUF(N * N * N);
            world = new Boolean[N][N];
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > world.length - 1 || col > world.length - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!world[row][col]) {
            world[row][col] = true;
            openSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > world.length || col > world.length) {
            throw new IndexOutOfBoundsException();
        } else {
            return world[row][col];
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > world.length || col > world.length) {
            throw new IndexOutOfBoundsException();
        }
        return false;
    }


    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public int xyTO1D(int r, int x) {
        return ((r * worldSize) + x);
    }

    // unit testing (not required)
    public static void main(String[] args) {

    }


}                       
