/**
 * Created by Hau on 4/25/17.
 */

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;
    private double[][] energys;
    private int[][] poo;

    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
        this.energys = new double[picture.height()][picture.width()];
    }

    // current picture
    public Picture picture() {
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        return this.picture.width();
    }

    // height of current picture
    public int height() {
        return this.picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= this.width()) {
            throw new IndexOutOfBoundsException("u mess up here fam");
        }
        if (y < 0 || y >= this.height()) {
            throw new IndexOutOfBoundsException("u mess up here lol");
        }
        return gradientColor(x, y);
    }

    private double gradientColor(int x, int y) {
        double xred = Math.abs(this.picture.get(((x - 1) + width()) % width(), y).getRed()
                - this.picture.get((x + 1) % width(), y).getRed());

        double xblue = Math.abs(this.picture.get(((x - 1) + width()) % width(), y).getBlue()
                - this.picture.get((x + 1) % width(), y).getBlue());

        double xgreen = Math.abs(this.picture.get(((x - 1) + width()) % width(), y).getGreen()
                - this.picture.get((x + 1) % width(), y).getGreen());

        double yred = Math.abs(this.picture.get(x, ((y - 1) + height()) % height()).getRed()
                - this.picture.get(x, (y + 1) % height()).getRed());

        double yblue = Math.abs(this.picture.get(x, ((y - 1) + height()) % height()).getBlue()
                - this.picture.get(x, (y + 1) % height()).getBlue());

        double ygreen = Math.abs(this.picture.get(x, ((y - 1) + height()) % height()).getGreen()
                - this.picture.get(x, (y + 1) % height()).getGreen());

        double poop = (xred * xred) + (xblue * xblue) + (xgreen * xgreen)
                + (yred * yred) + (yblue * yblue) + (ygreen * ygreen);

        return poop;
    }


//    private void getEnergy() {
//        for (int r = 0; r < this.energys.length; r++) {
//            for (int c = 0; c < this.energys[r].length; c++) {
//                this.energys[r][c] = this.energy(c, r);
//            }
//        }
//    }
    private void shortestPath(int x1, int y1, int x2, int y2) {
        if (energys[x2][y2] > energys[x1][y1] + energy(x2, y2)) {
            energys[x2][y2] = energys[x1][y1] + energy(x2, y2);
            poo[x2][y2] = x1;
        }
    }
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        Picture original = picture;
        Picture transpose = new Picture(original.height(), original.width());

        for (int i = 0; i < transpose.width(); i++) {
            for (int p = 0; p < transpose.height(); p++) {
                transpose.set(i, p, original.get(p, i));
            }
        }
        this.picture = transpose;
        int[] seam = findVerticalSeam();
        this.picture = original;

        return seam;
    }



    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        energys = new double[width()][height()];
        poo = new int[width()][height()];

        for (int i = 0; i < width(); i++) {
            for (int o = 0; o < height(); o++) {
                energys[i][o] = Double.POSITIVE_INFINITY;
            }
        }

        for (int i = 0; i < width(); i++) {
            energys[i][0] = 255 * 255 * 3;
        }

        for (int i = 0; i < height() - 1; i++) {
            for (int l = 0; l < width(); l++) {
                if (l > 0) {
                    shortestPath(l, i, l - 1, i + 1);
                }

                shortestPath(l, i, l, i + 1);

                if (l + 1 < width()) {
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
        System.out.println(ooo);
        int pre = poo[ooo][height() - 1];

        for (int k = height() - 2; k >= 0; k--) {
            seam[k] = pre;
            pre = poo[pre][k];
        }

        return seam;
    }

    private int position(int col, int row) {
        return width() * row + col;
    }

    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {
//        Picture original = picture;
//        Picture transpose = new Picture(original.height(), original.width());
//
//        for (int i = 0; i < transpose.width(); i++) {
//            for (int p = 0; p < transpose.height(); p++) {
//                transpose.set(i, p, original.get(p, i));
//            }
//        }
//
//        this.picture = transpose;
//        transpose = null;
//        original = null;
//
//        removeVerticalSeam(seam);
//
//        original = picture;
//        transpose = new Picture(original.height(), original.width());
//
//        for (int i = 0; i < transpose.width(); i++) {
//            for (int p = 0; p < transpose.height(); p++) {
//                transpose.set(i, p, original.get(p, i));
//            }
//        }
//
//        this.picture = transpose;
//        transpose = null;
//        original = null;
    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {
//        if (seam.length  != height()) {
//            throw new IllegalArgumentException("you messed up here pls");
//        }
//
//        if (seam == null) {
//            throw new NullPointerException();
//        }
//
//        Picture original = this.picture;
//        Picture newPic = new Picture(original.width() - 1, original.height());
//
//        for (int i = 0; i < newPic.width(); i++) {
//            for (int p = 0; p < newPic.height(); p++) {
//                newPic.set(i, p, original.get(p, i));
//            }
//
//            for (int p = seam[i]; p < newPic.width(); p++) {
//                newPic.set(p, i, original.get(p + 1, i));
//            }
//        }
//
//        this.picture = newPic;
    }
}
