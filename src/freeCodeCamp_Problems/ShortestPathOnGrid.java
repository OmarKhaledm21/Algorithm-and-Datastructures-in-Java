package freeCodeCamp_Problems;
//Dungeon Problem https://towardsdatascience.com/graph-theory-bfs-shortest-path-problem-on-a-grid-1437d5cb4023

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// (0 is non-blocked cell) / (-1 is blocked cell) / (1 is visited cell)
public class ShortestPathOnGrid {
    int[][] adj_mat;
    int size;
    int row;
    int col;

    int end_x, end_y;

    class Cell {
        int x, y;
        Cell parent;

        public Cell(int x, int y, Cell parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }
    }

    public ShortestPathOnGrid(int row, int col) {
        this.adj_mat = new int[row][col];
        this.size = row * col;
        this.row = row;
        this.col = col;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                adj_mat[i][j] = 0;
            }
        }
    }

    public void blockCell(int x, int y) {
        this.adj_mat[x][y] = -1;
    }

    public void setEndGoal(int x, int y) {
        this.end_x = x;
        this.end_y = y;
    }

    public boolean hasLeft(int y) {
        return (y > 0);
    }

    public boolean hasRight(int y) {
        return (y < col-1);
    }

    public boolean hasTop(int x) {
        return (x > 0);
    }

    public boolean hasBottom(int x) {
        return (x < row-1);
    }

    public boolean isBlocked(int x, int y) {
        return (this.adj_mat[x][y] == -1);
    }

    public boolean isGoal(int x,int y){
        return (x==this.end_x && y== this.end_y);
    }

    public boolean isVisited(int x,int y){
        return (this.adj_mat[x][y]==1);
    }
    public void BFS(int start_x, int start_y) {
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(start_x,start_y,null));
        Cell backtrackgoal = null;
        while (!queue.isEmpty()){
            Cell current = queue.poll();

            if(isGoal(current.x,current.y)){
                System.out.println("Reached Goal");
                backtrackgoal = current;
                break;
            }
            if(  !isBlocked(current.x,current.y)){
                queue.addAll(getNeighbors(current));
            }
            this.adj_mat[current.x][current.y] = 1;
        }
        StringBuilder path = new StringBuilder();

        if(backtrackgoal!=null) {
            while (backtrackgoal.parent != null) {
                path.append("(").append(backtrackgoal.x).append(",").append(backtrackgoal.y).append(")<-");
                backtrackgoal = backtrackgoal.parent;
            }
            path.append("(0,0)");
            System.out.println("Path: " + path.toString());
        }else{
            System.out.println("Cannot reach end");
        }
    }

    public ArrayList<Cell> getNeighbors(Cell current){
        ArrayList<Cell> neighbors = new ArrayList<>();
        if(hasLeft(current.y)){
            if(! isVisited(current.x, current.y-1) && !isBlocked(current.x, current.y-1)){
                this.adj_mat[current.x][current.y-1] = 1;
                neighbors.add(new Cell(current.x,current.y-1,current));
            }
        }
        if(hasRight(current.y)){
            if(! isVisited(current.x, current.y+1) && !isBlocked(current.x, current.y+1)){
                this.adj_mat[current.x][current.y+1] = 1;
                neighbors.add(new Cell(current.x, current.y+1,current));
            }
        }
        if(hasTop(current.x)){
            if(! isVisited(current.x-1, current.y) && !isBlocked(current.x-1, current.y)){
                this.adj_mat[current.x-1][current.y] = 1;
                neighbors.add(new Cell(current.x-1, current.y, current));
            }
        }
        if(hasBottom(current.x)){
            if(! isVisited(current.x+1, current.y) && !isBlocked(current.x+1, current.y)){
                this.adj_mat[current.x+1][current.y] = 1;
                neighbors.add(new Cell(current.x+1, current.y, current));
            }
        }
        return neighbors;
    }


    public static void main(String[] args) {
        ShortestPathOnGrid s = new ShortestPathOnGrid(5, 7);
        s.blockCell(0, 3);
        s.blockCell(1, 1);
        s.blockCell(2, 1);
        s.blockCell(3, 2);
        s.blockCell(3, 3);
        s.blockCell(4, 0);
        s.blockCell(4, 2);
        s.blockCell(4, 5);

        s.setEndGoal(4, 3);

        s.BFS(0, 0);
    }

}
