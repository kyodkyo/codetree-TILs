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
    static int n, m;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Point> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        board = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<m; j++)
                board[i][j] = Integer.parseInt(line[j]);
        }

        q.add(new Point(0, 0));
        visited[0][0] = true;

        System.out.println(bfs());
    }

    public static int bfs(){
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!q.isEmpty()){
            Point p = q.poll();

            if (p.x==n-1 && p.y==m-1)
                return 1;

            for(int i=0; i<4; i++){
                int currX = p.x + dx[i];
                int currY = p.y + dy[i];

                if (checkBoard(currX, currY)){
                    visited[currX][currY] = true;
                    q.add(new Point(currX, currY));
                }
            }
        }
        return 0;
    }

    public static boolean checkBoard(int x, int y){
        if (0<=x && x<n && 0<=y && y<m && !visited[x][y] && board[x][y]==1)
            return true;
        return false;
    }
}