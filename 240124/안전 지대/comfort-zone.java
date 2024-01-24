import java.io.*;
import java.util.*;

class Town implements Comparable<Town>{
    int k;
    int count;

    public Town(int k, int count){
        this.k = k;
        this.count = count;
    }

    @Override
    public int compareTo(Town town){
        if (town.count == this.count)
            return this.k - town.k;
        return town.count - this.count;
    }
}

public class Main {
    static int n, m, height=0;
    static int[][] board;
    static ArrayList<Town> towns = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        board = new int[n][m];
        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<m; j++){
                board[i][j] = Integer.parseInt(line[j]);
                height = Math.max(height, board[i][j]);
            }
        }

        for(int h=1; h<=height; h++){
            int villiage = 0;
            boolean[][] visited = new boolean[n][m];

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if (!visited[i][j] && board[i][j] > h){
                        villiage++;
                        dfs(i, j, visited, h);
                    }
                }
            }

            towns.add(new Town(h, villiage));
        }

        Collections.sort(towns);
        System.out.printf("%d %d", towns.get(0).k, towns.get(0).count);
    }

    public static void dfs(int x, int y, boolean[][] visited, int h){
        visited[x][y] = true;

        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        for(int i=0; i<4; i++){
            int currX = x + dx[i];
            int currY = y + dy[i];

            if (checkBoard(currX, currY, visited, h))
                dfs(currX, currY, visited, h);
        }
    }

    public static boolean checkBoard(int x, int y, boolean[][] visited, int h){
        if (0<=x && x<n && 0<=y && y<m && !visited[x][y] && board[x][y] > h)
            return true;
        return false;
    }
}