import java.util.*;

public class Main {

    static int n, min=Integer.MAX_VALUE;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = sc.nextInt();

        findMaxJump(0);
        if (min == Integer.MAX_VALUE)
            min = -1;
        System.out.println(min);
    }

    public static void findMaxJump(int num){
        if (num==n-1){
            min = Math.min(min, list.size());
            return;
        }

        for(int i=1; i<=arr[num]; i++){
            if (num+i >= n){
                return;
            }
            list.add(arr[num+i]);
            findMaxJump(num+i);
            list.remove(list.size()-1);
        }

    }
}