package hw3.puzzle;

import edu.princeton.cs.algs4.MinPQ;

/**
 * Created by Hau on 3/22/17.
 */
public class Solver {
    MinPQ<SearchNode> hi;


    public Solver(WorldState initial) {
        hi = new MinPQ<>();

        SearchNode poop = new SearchNode(initial, 0, null);


    }
    private class SearchNode {
        WorldState world;
        int pls;
        SearchNode prev;

        public SearchNode(WorldState a, int b , SearchNode c) {
            world = a;
            pls = b;
            prev = c;
        }

    }
    public int moves() {

    }
    public Iterable<WorldState> solution() {

    }
}
