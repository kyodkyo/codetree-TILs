import java.util.*;
import java.io.*;

class Coin implements Comparable<Coin> {
    int num;
    int x;
    int y;

    public Coin(int num, int x, int y){
        this.num = num;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coin c){
        return this.num - c.num;
    }
}

public class Main {
    
    static int n, min = Integer.MAX_VALUE;
    static int sx, sy, ex, ey;
    static ArrayList<Coin> coinList = new ArrayList<>();
    static ArrayList<Coin> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        for(int i=0; i<n; i++){
            char[] line = br.readLine().toCharArray();
            
            for(int j=0; j<n; j++){
                if (line[j] == 'S'){
                    sx = i;
                    sy = j;
                } else if (line[j] == 'E'){
                    ex = i;
                    ey = j;
                } else if ('1' <= line[j] && line[j] <= '9')
                    coinList.add(new Coin(line[j]-'0', i, j));
            }
        }
        Collections.sort(coinList);
        if (coinList.size() < 3)
            System.out.println(-1);
        else{
            selectCoins(0);
            System.out.println(min);
        }
    }

    public static void selectCoins(int idx){
        if (list.size() == 3){
            min = Math.min(min, calcDis());
            return;
        }

        for(int i=idx; i<coinList.size(); i++){
            list.add(coinList.get(i));
            selectCoins(i+1);
            list.remove(list.size()-1);
        }
    }

    public static int calcDis(){
        int dis = Math.abs(sx-list.get(0).x) + Math.abs(sy-list.get(0).y);

        for(int i=0; i<list.size()-1; i++){
            dis += Math.abs(list.get(i).x-list.get(i+1).x);
            dis += Math.abs(list.get(i).y-list.get(i+1).y);
        }
        dis += (Math.abs(ex-list.get(2).x) + Math.abs(ey-list.get(2).y));
        return dis;
    }
}