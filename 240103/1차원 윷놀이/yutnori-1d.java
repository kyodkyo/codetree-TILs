import java.util.*;

public class Main {
    
    static int n, m, k, max=0;
    static int[] orders, horses;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        orders = new int[n];
        for(int i=0; i<n; i++)
            orders[i] = sc.nextInt();

        selectHorses(1);
        System.out.println(max);
    }

    public static void selectHorses(int num){
        if (num==n+1){
            checkHorses();
            return;
        }

        for(int i=1; i<=k; i++){
            list.add(i);
            selectHorses(num+1);
            list.remove(list.size()-1);
        }
    }

    public static void checkHorses(){
        horses = new int[]{0, 1, 1, 1};

        for(int i=0; i<list.size(); i++){
            horses[list.get(i)] += orders[i];
            
            if (horses[list.get(i)] >= m)
                horses[list.get(i)] = m;
        }

        int sum = 0;
        for(int i=0; i<horses.length; i++){
            if (horses[i] == m)
                sum += 1;
        }

        max = Math.max(max, sum);
    }
}