/**
 * Created by Hau on 4/25/17.
 */

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;
    private double[][] energys;
    private int[][] poo;

    public SeamCarver(Picture picture) {
        this.picture = picture;
    }
    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x >= width() || x < 0) {
            throw new IndexOutOfBoundsException("u mess up here fam");
        }

        if (y >= height() || y < 0) {
            throw new IndexOutOfBoundsException("u mess up here lol");
        }
        else {
            double Xred = Math.abs(picture.get(x - 1, y).getRed() - picture.get(x + 1, y).getRed());
            double Xblue = Math.abs(picture.get(x - 1, y).getBlue() - picture.get(x + 1, y).getBlue());
            double Xgreen = Math.abs(picture.get(x - 1, y).getGreen() - picture.get(x + 1, y).getGreen());

            double Yred = Math.abs(picture.get(x, y - 1).getRed() - picture.get(x, y + 1).getRed());
            double Yblue = Math.abs(picture.get(x, y - 1).getBlue() - picture.get(x, y + 1).getBlue());
            double Ygreen = Math.abs(picture.get(x, y - 1).getGreen() - picture.get(x, y + 1).getGreen());

            return (Xred * Xred) + (Xblue * Xblue) * (Xgreen * Xgreen) + (Yred * Yred) + (Yblue * Yblue) + (Ygreen * Ygreen);
        }
    }
    private void shortestPath(int x1, int y1, int x2, int y2) {
        if (energys[x2][y2] > energy(x2, y2) + energys[x1][y1]) {
            energys[x2][y2] = energy(x2, y2) + energys[x1][y1];
            poo[x2][y2] = x1;
        }
    }
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        Picture original = picture;
        Picture transpose = new Picture(original.height(), original.width());

        for (int i = 0; i < transpose.height(); i++) {
            for (int p = 0; p < transpose.width(); p++) {
                transpose.set(i, p, original.get(p, i));
            }
        }
        this.picture = transpose;
        int[] lol = findVerticalSeam();
        this.picture = original;
        return lol;
    }



    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        energys  = new double[width()][height()];
        poo = new int[width()][height()];

        for (int i = 0; i < width(); i++) {
            for (int o = 0; i < height(); o++) {
                energys[i][o] = Double.POSITIVE_INFINITY;
            }
        }

        for (int i = 0; i < height(); i++) {
            for (int l = 0; l < width(); l++) {
                if (l > 0) {
                    shortestPath(l, i, l - 1, i + 1);
                }

                shortestPath(l, i, l, i + 1);

                if (l < width() -  1) {
                    shortestPath(l, i, l + 1, i + 1);
                }
            }
        }

        double minEn = Double.POSITIVE_INFINITY;
        int ooo = -1;
        for (int i = 0; i < width(); i++) {
            if (energys[i][height() - 1] < minEn) {
                ooo = i;
                minEn = energys[i][height() - 1];
            }
        }

        assert ooo != -1;
        int[] seam = new int[height()];
        seam[height() - 1] = ooo;
        int pre = poo[ooo][height() - 1];

        for (int k = height() - 2; k >= 0; k--) {
            seam[k] = pre;
            pre = poo[pre][k];
        }

        return seam;
    }

    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {

    }
}
