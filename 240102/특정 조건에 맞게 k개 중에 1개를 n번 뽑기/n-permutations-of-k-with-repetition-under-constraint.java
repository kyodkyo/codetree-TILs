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
            if (num>=3 && list.get(num-2)==i && list.get(num-3)==i)
                continue;
            list.add(i);
            selectNum(num+1);
            list.remove(list.size()-1);
        }

    }

    public static void printNums(){
        for(int i=0; i<list.size(); i++)
            System.out.print(list.get(i) + " ");
        System.out.println();
    }
}