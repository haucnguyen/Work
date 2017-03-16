package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int openSites;
    boolean world[][];
    int worldSize;
    int topSite;
    int bottomSite;
    WeightedQuickUnionUF worldQUF;
    WeightedQuickUnionUF checkFill;


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        } else {
            openSites = 0;
            worldSize = N;
            topSite = N * N + 1;
            bottomSite = N * N + 2;
            world = new boolean[N][N];
            worldQUF = new WeightedQuickUnionUF(N * N + 3);
            checkFill = new WeightedQuickUnionUF(N * N  + 3);
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row >= worldSize || col >= worldSize || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        } else if (!world[row][col]) {
            int num =  xyTo1D(row, col);
            world[row][col] = true;
            openSites++;
            if (row != 0) {
                if (isOpen(row - 1, col)) {
                    worldQUF.union(xyTo1D(row - 1, col), num);
                    checkFill.union(xyTo1D(row - 1, col), num);
                }
            }

            if (row != worldSize - 1) {
                if (isOpen(row + 1, col)) {
                    worldQUF.union(xyTo1D(row + 1, col), num);
                    checkFill.union(xyTo1D(row + 1, col), num);
                }
            }

            if (col != 0) {
                if (isOpen(row, col - 1)) {
                    worldQUF.union(xyTo1D(row, col - 1), num);
                    checkFill.union(xyTo1D(row, col - 1), num);
                }
            }

            if (col != worldSize - 1) {
                if (isOpen(row, col + 1)) {
                    worldQUF.union(xyTo1D(row, col + 1), num);
                    checkFill.union(xyTo1D(row, col + 1), num);
                }
            }

            if (row == 0) {
                worldQUF.union(num, topSite);
                checkFill.union(num, topSite);
            }

            if (row == worldSize - 1) {
                worldQUF.union(num, bottomSite);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= worldSize || col >= worldSize || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            return world[row][col];
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= world.length || col >= world.length) {
            throw new IndexOutOfBoundsException();
        } else {
            return checkFill.connected(xyTo1D(row, col), topSite);
        }
    }


    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return worldQUF.connected(topSite, bottomSite);
    }

    public int xyTo1D(int r, int x) {
        return ((r * worldSize) + x);
    }

}                       
