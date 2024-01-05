import java.util.Scanner;

public class Main {
    
    static int n, count=-1, max=0;
    static int[][] nums, dirs;
    static int[] dx = new int[]{0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[]{0, 0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        nums = new int[n][n];
        dirs = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                nums[i][j] = sc.nextInt();
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                dirs[i][j] = sc.nextInt();
        }

        int currX = sc.nextInt()-1;
        int currY = sc.nextInt()-1;
        moveBoard(currX, currY, nums[currX][currY]);
        System.out.println(max);
    }

    public static void moveBoard(int x, int y, int currN){
        if (!isPossible(x, y) || nums[x][y] < currN){
            max = Math.max(max, count);
            return;
        }

        count++;
        currN = nums[x][y];
        for(int i=1; i<n; i++){
            int currX = x + (dx[dirs[x][y]] * i);
            int currY = y + (dy[dirs[x][y]] * i);
            moveBoard(currX, currY, currN);
        }
        count--;
    }

    public static boolean isPossible(int x, int y){
        if (0<=x && x<n && 0<=y && y<n)
            return true;
        return false;
    }
}