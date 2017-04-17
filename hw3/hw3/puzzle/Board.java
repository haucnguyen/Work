package hw3.puzzle;

public class Board {
    int size;

    public Board(int[][] tiles) {
        size = 0;


    }
    public int tileAt(int i, int j) {
        return
    }

    public int size() {
        return size;
    }

    public Iterable<WorldState> neighbors() {

    }
    public int hamming() {

    }
    public int manhattan() {

    }
    public int estimatedDistanceToGoal() {

    }
    public boolean isGoal() {

    }
    public boolean equals(Object y) {

    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
