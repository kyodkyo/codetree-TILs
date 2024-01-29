import java.util.*;
import java.io.*;

class Point{
    int x,y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int x, y, maxValue;
    static Point max;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Point> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        board = new int[n][n];
        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++)
                board[i][j] = Integer.parseInt(line[j]);
        }

        String[] pos = br.readLine().split(" ");
        x = Integer.parseInt(pos[0])-1;
        y = Integer.parseInt(pos[1])-1;
        max = new Point(x, y);

        for(int i=0; i<k; i++){
            q.add(max);
            visited = new boolean[n][n];
            visited[max.x][max.y] = true;
            maxValue = board[max.x][max.y];
            bfs();
        }
        System.out.printf("%d %d", max.x+1, max.y+1);
    }

    public static void bfs(){
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        int num = 0;

        while(!q.isEmpty()){
            Point p = q.poll();

            if (board[p.x][p.y] != maxValue && board[p.x][p.y] >= num){
                num = getMaxPos(num, p);
            }

            for(int i=0; i<4; i++){
                int currX = p.x + dx[i];
                int currY = p.y + dy[i];

                if (checkBoard(currX, currY)){
                    visited[currX][currY] = true;
                    q.add(new Point(currX, currY));
                }
            }
        }
        maxValue = num;
    }

    public static boolean checkBoard(int i, int j){
        int n = board.length;

        if (0<=i && i<n && 0<=j && j<n && board[i][j]<maxValue && !visited[i][j])
            return true;
        return false;
    }

    public static int getMaxPos(int num, Point p){
        if (board[p.x][p.y] > num){
            max.x = p.x;
            max.y = p.y;
            num = board[p.x][p.y];
        }
        else if (board[p.x][p.y] == num){
            if (p.x < max.x){
                max.x = p.x;
                max.y = p.y;
            }
            else if (p.x == max.x){
                if (p.y < max.y)
                    max.y = p.y;
            }
        }
        return num;
    }

}