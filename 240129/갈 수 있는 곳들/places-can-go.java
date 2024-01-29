import java.io.*;
import java.util.*;

class Point{
    int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, k;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Point> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        board = new int[n][n];
        visited = new boolean[n][n];
        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++)
                board[i][j] = Integer.parseInt(line[j]);
        }

        for(int i=0; i<k; i++){
            String[] line = br.readLine().split(" ");
            q.add(new Point(Integer.parseInt(line[0])-1, Integer.parseInt(line[1])-1));
            bfs();
        }

        int count = 0;
        for(boolean[] row : visited){
            for(boolean col : row){
                if (col)
                    count++;
            }
        }
        System.out.println(count);
    }

    public static void bfs(){
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int i=0; i<4; i++){
                int currX = p.x + dx[i];
                int currY = p.y + dy[i];

                if (checkBoard(currX, currY)){
                    visited[currX][currY] = true;
                    q.add(new Point(currX, currY));
                }
            }
        }
    }

    public static boolean checkBoard(int x, int y){
        if (0<=x && x<n && 0<=y && y<n && !visited[x][y] && board[x][y]==0)
            return true;
        return false;
    }
}