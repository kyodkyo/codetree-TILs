import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static ArrayList<Integer> list = new ArrayList<>();
    static int k, n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        k = sc.nextInt();
        n = sc.nextInt();

        selectNum(1);
    }

    public static void selectNum(int num){
        if (num == n+1){
            printNums();
            return;
        }

        for(int i=1; i<=k; i++){
            if (num>=3){
                if (!isPossible(num-1, i))
                    list.add(i);
                else
                    continue;
            }
            else
                list.add(i);
            selectNum(num+1);
            list.remove(list.size()-1);
        }

    }
    public static boolean isPossible(int pos, int num){
        boolean check = false;

        if (list.get(pos-1) == num && list.get(pos-2)==num)
            check = true;

        return check;
    }
    public static void printNums(){
        for(int i=0; i<list.size(); i++)
            System.out.print(list.get(i) + " ");
        System.out.println();
    }
}