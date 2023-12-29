import java.io.*;
import java.util.*;

class Point{
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int n, max=0;
    static int[][] board;
    static ArrayList<Point> bombPoint = new ArrayList<>();
    static ArrayList<Integer> bombList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++){
                if (Integer.parseInt(line[j]) == 1)
                    bombPoint.add(new Point(i, j));
            }
        }

        checkLocale(1);
        System.out.println(max);
    }

    public static void checkLocale(int k){
        if (k == bombPoint.size() + 1){
            bomb();
            return;
        }

        for(int i=1; i<=3; i++){
            bombList.add(i);
            checkLocale(k+1);
            bombList.remove(bombList.size()-1);
        }

    }

    public static void bomb(){
        int[] dx = new int[4];
        int[] dy = new int[4];

        board = new int[n][n];

        for(int i=0; i<bombPoint.size(); i++){
            int x = bombPoint.get(i).x;
            int y = bombPoint.get(i).y;
            int bomb = bombList.get(i);

            if (bomb==1){
                dx = new int[]{-2, -1, 1, 2, 0};
                dy = new int[]{0, 0, 0, 0, 0};
            }
            else if (bomb==2){
                dx = new int[]{-1, 0, 1, 0, 0};
                dy = new int[]{0, 1, 0, -1, 0};
            }
            else if (bomb==3){
                dx = new int[]{-1, 1, 1, -1, 0};
                dy = new int[]{1, 1, -1, -1, 0};
            }

            for(int s=0; s<5; s++){
                int tx = x + dx[s];
                int ty = y + dy[s];

                if (0<=tx && tx<n && 0<=ty && ty<n)
                    board[tx][ty] = 1;
            }
        }
        max = Math.max(max, countBomb(board));
    }

    public static int countBomb(int[][] board){
        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if (board[i][j] == 1)
                    count++;
            }
        }

        return count;
    }
}