import java.util.*;

public class Main {

    static int n, m, max;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = sc.nextInt();

        comb(0, 0);
        System.out.println(max);
    }

    public static void comb(int idx, int count){
        if (count == m){
            max = Math.max(max, calcXOR());
            return;
        }

        for(int i=idx; i<n; i++){
            list.add(arr[i]);
            comb(i+1, count+1);
            list.remove(list.size()-1);
        }
    }

    public static int calcXOR(){
        ArrayList<Integer> nums = new ArrayList<>();
        
        for(int i=0; i<m; i++){
            int t = list.get(i);
            int size = 0;

            while(t>0){
                if (nums.size() > size){
                    if (nums.get(size) == t%2)
                        nums.set(size, 0);
                    else
                        nums.set(size, 1);
                }
                else
                    nums.add(t % 2);
                
                t /= 2;
                size++;
            }
        }

        int sum = 0;
        for(int i=0; i<nums.size(); i++){
            if (nums.get(i)==1)
                sum += Math.pow(2, i);
        }
        return sum;
    }
}