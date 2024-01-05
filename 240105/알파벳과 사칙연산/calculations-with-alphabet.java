import java.io.*;
import java.util.*;

public class Main {

    static int max=0;
    static char[] exp;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        exp = br.readLine().toCharArray();
        
        selectNums(1);
        System.out.println(max);
    }

    public static void selectNums(int n){
        if(n==7){
            max = Math.max(max, calcExp());
            return;
        }

        for(int i=1; i<=4; i++){
            list.add(i);
            selectNums(n+1);
            list.remove(list.size()-1);
        }
    }

    public static int calcExp(){
        int result = list.get(exp[0]-'a'); // 첫번째 문자의 숫자값

        for(int i=1; i<exp.length; i+=2){
            if (exp[i]=='+' || exp[i]=='-' || exp[i]=='*')
                result = calcNum(result, exp[i], i+1);
        }

        return result;
    }

    public static int calcNum(int result, char c, int idx){
        if (c=='+')
            return result + list.get(exp[idx]-'a');
        else if (c=='-')
            return result - list.get(exp[idx]-'a');
        else
            return result * list.get(exp[idx]-'a');
    }
}