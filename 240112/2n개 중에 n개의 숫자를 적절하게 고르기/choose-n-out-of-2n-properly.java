import java.util.*;

public class Main {
    
    static int n, sum=0, g1=0, g2=0, result=Integer.MAX_VALUE;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        arr = new int[2*n];

        for(int i=0; i<2*n; i++){
            arr[i] = sc.nextInt();
            sum += arr[i];
        }    

        makeGroup(0);
        System.out.println(result);
    }

    public static void makeGroup(int idx){
        if (list.size() == n){
            g2 = sum - g1;
            result = Math.min(result, Math.abs(g1-g2));
            return;
        }

        for(int i=idx; i<2*n; i++){
            list.add(arr[i]);
            g1 += arr[i];
            makeGroup(idx+1);
            g1 -= arr[i];
            list.remove(list.size()-1);
        }
    }
}