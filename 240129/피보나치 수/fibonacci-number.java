import java.util.*;

public class Main {
    static int[] memo;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        memo = new int[n+1];
        System.out.println(fibo(n));
    }

    public static int fibo(int n){
        if (memo[n] != 0)
            return memo[n];

        if (n <= 2)
            return 1;
        else
            memo[n] = fibo(n-1) + fibo(n-2);

        return memo[n];
    }
}