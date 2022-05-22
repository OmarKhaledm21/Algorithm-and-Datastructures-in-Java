package freeCodeCamp_Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 0 -> Valid   -1 -> Blocked  1 -> Visited
public class ShortestPathOnGrid2 {
    class Pair {
        int x, y;
        Pair parent;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Pair target) {
            return (this.x == target.x && this.y == target.y);
        }
    }

    int R, C;
    int[][] m;
    int start_row, start_col;
    int end_row, end_col;

    public ShortestPathOnGrid2(int[][] m, int sr, int sc, int er, int ec) {
        this.m = m;
        this.R = m.length;
        this.C = m[0].length;
        this.start_row = sr;
        this.start_col = sc;
        this.end_row = er;
        this.end_col = ec;
    }

    public void solve() {
        boolean reached_end = false;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        Pair goal = new Pair(end_row, end_col);

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(start_row, start_col));


        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            if (current.equals(goal)) {
                reached_end = true;
                goal = current;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = current.x + dr[i];
                int nc = current.y + dc[i];
                if (nr>=0 && nr < R && nc>=0 && nc<C && this.m[nr][nc] == 0){
                    Pair new_pair = new Pair(nr, nc);
                    new_pair.parent = current;
                    queue.add(new_pair);
                }
            }

            this.m[current.x][current.y] = 1;
        }
        if (reached_end) {
            System.out.println("GOAL REACHED!");
            while (goal.parent!=null){
                System.out.println(goal.x+" "+goal.y);
                goal = goal.parent;
            }
            System.out.println(start_row+" "+start_col);
        }
    }


    public static void main(String[] args) {
        int[][] m = {
                {0, 0, 0, -1, 0, 0, 0},
                {0, -1, 0, 0, 0, -1, 0},
                {0, -1, 0, 0, 0, 0, 0},
                {0, 0, -1, -1, 0, 0, 0},
                {-1, 0, -1, 0, 0, -1, 0}
        };
        new ShortestPathOnGrid2(m, 0, 0, 4, 3).solve();
    }
}
