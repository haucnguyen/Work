/**
 * Created by Hau on 4/25/17.
 */
public class SeamCarver {
    public Picture picture;
    public double[][] lol;
    public int[][] poo;

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

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return null;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return null;
    }

    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {

    }
}
