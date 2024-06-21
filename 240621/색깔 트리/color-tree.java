import java.util.*;

public class Main {
    static final int MAX_ID = 100005; 
    static final int COLOR_MAX = 5;

    static class Node {
        int id = 0;
        int color = 0;
        int lastUpdate = 0; 
        int maxDepth = 0; 
        int parentId = 0; 
        ArrayList<Integer> childIds = new ArrayList<>(); 
    }

    static class ColorCount {
        int[] cnt = new int[COLOR_MAX + 1];

        ColorCount add(ColorCount obj) {
            ColorCount res = new ColorCount();
            for (int i = 1; i <= COLOR_MAX; i++) {
                res.cnt[i] = this.cnt[i] + obj.cnt[i];
            }
            return res;
        }

        int score() {
            int result = 0;
            for (int i = 1; i <= COLOR_MAX; i++) {
                if (this.cnt[i] > 0) result++;
            }
            return result * result;
        }
    }

    static Node[] nodes = new Node[MAX_ID];
    static boolean[] isRoot = new boolean[MAX_ID];

    static {
        for (int i = 0; i < MAX_ID; i++)
            nodes[i] = new Node();
    }

    static boolean canMakeChild(Node curr, int needDepth) {
        if (curr.id == 0)
            return true;
        if (curr.maxDepth <= needDepth)
            return false;
        return canMakeChild(nodes[curr.parentId], needDepth + 1);
    }

    static int[] getColor(Node curr) {
        if (curr.id == 0)
            return new int[] { 0, 0 };
        
        int[] info = getColor(nodes[curr.parentId]);
        if (info[1] > curr.lastUpdate) 
            return info;
        else
            return new int[] { curr.color, curr.lastUpdate };
    }

    static Object[] getBeauty(Node curr, int color, int lastUpdate) {
        if (lastUpdate < curr.lastUpdate) {
            lastUpdate = curr.lastUpdate;
            color = curr.color;
        }
        
        int result = 0;
        ColorCount colorCount = new ColorCount();
        colorCount.cnt[color] = 1;
        
        for (int childId : curr.childIds) {
            Node child = nodes[childId];
            Object[] subResult = getBeauty(child, color, lastUpdate);
            colorCount = colorCount.add((ColorCount) subResult[1]);
            result += (Integer) subResult[0];
        }
        result += colorCount.score();
        return new Object[] { result, colorCount };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Q = scanner.nextInt();
        for (int i = 1; i <= Q; i++) {
            int T = scanner.nextInt();
            
            if (T == 100) {
                int mId = scanner.nextInt();
                int pId = scanner.nextInt();
                int color = scanner.nextInt();
                int maxDepth = scanner.nextInt();
                if (pId == -1) 
                    isRoot[mId] = true;
                
                if (isRoot[mId] || canMakeChild(nodes[pId], 1)) {
                    nodes[mId].id = mId;
                    nodes[mId].color = color;
                    nodes[mId].maxDepth = maxDepth;
                    nodes[mId].parentId = isRoot[mId] ? 0 : pId;
                    nodes[mId].lastUpdate = i;
                    if (!isRoot[mId]) {
                        nodes[pId].childIds.add(mId);
                    }
                }
            } else if (T == 200) {
                int mId = scanner.nextInt();
                int color = scanner.nextInt();

                nodes[mId].color = color;
                nodes[mId].lastUpdate = i;
            } else if (T == 300) {
                int mId = scanner.nextInt();
                System.out.println(getColor(nodes[mId])[0]);
            } else if (T == 400) {
                int beauty = 0;
                for (int idx = 1; idx < MAX_ID; idx++) {
                    if (isRoot[idx]) 
                        beauty += (Integer) getBeauty(nodes[idx], nodes[idx].color, nodes[idx].lastUpdate)[0];
                }
                System.out.println(beauty);
            }
        }
        scanner.close();
    }
}