import java.util.*;

public class Main {
    
    static int n, max = 0;
    static int[][] board;
    static boolean[] rowCheck, colCheck;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        board = new int[n][n];
        rowCheck = new boolean[n];
        colCheck = new boolean[n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                board[i][j] = sc.nextInt();
        }

        getMax(0);
        System.out.println(max);
    }

    public static void getMax(int row){
        if (row == n){
            max = Math.max(max, getSum());
            return;
        }
        
        for(int i=0; i<n; i++){
            if (rowCheck[row] || colCheck[i])
                continue;
            rowCheck[row] = true;
            colCheck[i] = true;
            list.add(board[row][i]);
            getMax(row+1);
            list.remove(list.size()-1);
            rowCheck[row] = false;
            colCheck[i] = false;
        }
    }

    public static int getSum(){
        int sum = 0;
        
        for(int n : list)
            sum += n;
        
        return sum;
    }
}