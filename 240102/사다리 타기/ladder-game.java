import java.util.*;
import java.io.*;

public class Main {

    static class Ladder{
        int lineNum;
        int depth;
        int left;
        int right;

        public Ladder(int lineNum, int depth, int left, int right){
            this.lineNum = lineNum;
            this.depth = depth;
            this.left = left;
            this.right = right;
        }
    }

    static int n, m, min, maxDepth=0;
    static ArrayList<Ladder> ladders = new ArrayList<>();
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] correct;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        min = m;

        for(int i=0; i<m; i++){
            String[] line = br.readLine().split(" ");
            int left = Integer.parseInt(line[0]);
            int depth = Integer.parseInt(line[1]);
            maxDepth = Math.max(maxDepth, depth);
            ladders.add(new Ladder(i+1, depth, left, left+1));
        }

        for(int i=0; i<m; i++)
            list.add(1);
        correct = moveLadder();
        list.clear();

        selectLines(1);
        System.out.println(min);
    }

    public static void selectLines(int num){
        if (num == m+1){
            isPossible();
            return;
        }

        for(int i=0; i<=1; i++){
            list.add(i);
            selectLines(num+1);
            list.remove(list.size() - 1);
        }
    }

    public static void isPossible(){
        boolean check = true;
        int[] temp = moveLadder();

        for(int i=0; i<n; i++){
            if(temp[i] == 0 || temp[i] != correct[i]){
                check = false;
                break;
            }
        }

        if (check)
            countLines();
    }

    public static int[] moveLadder(){
        int[] temp = new int[n];

        for(int i=1; i<=n; i++){
            int curLine = i;

            for(int depth=1; depth<=maxDepth; depth++){
                for(int j=0; j<ladders.size(); j++){
                    if (list.get(ladders.get(j).lineNum - 1) == 0)
                        continue;

                    if (ladders.get(j).depth == depth && ladders.get(j).left == curLine){
                        curLine = ladders.get(j).right;
                        break;
                    }
                    else if (ladders.get(j).depth == depth && ladders.get(j).right == curLine) {
                        curLine = ladders.get(j).left;
                        break;
                    }
                }
            }

            temp[curLine-1] = i;
        }

        return temp;
    }

    public static void countLines(){
        int count = 0;

        for(int i=0; i<list.size(); i++){
            if (list.get(i) == 1)
                count++;
        }

        min = Math.min(min, count);
    }
}