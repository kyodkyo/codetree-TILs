import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static final int MAX_M = 100000;
    public static final int MAX_N = 100000;

    public static Scanner sc;

    public static int n, m, q;
    public static int[] prv = new int[MAX_M + 1];
    public static int[] nxt = new int[MAX_M + 1];
    public static int[] head = new int[MAX_N];
    public static int[] tail = new int[MAX_N];
    public static int[] numGift = new int[MAX_N];
    
    public static void buildFactory() {
        n = sc.nextInt();
        m = sc.nextInt();
        
        ArrayList<Integer>[] boxes = new ArrayList[n];
        for(int i = 0; i < n; i++)
            boxes[i] = new ArrayList<>();
        
        for(int id = 1; id <= m; id++) {
            int bNum = sc.nextInt();
            bNum--;
            
            boxes[bNum].add(id);
        }
    
        for(int i = 0; i < n; i++) {
            if(boxes[i].size() == 0)
                continue;
            
            head[i] = boxes[i].get(0);
            tail[i] = boxes[i].get(boxes[i].size() - 1);
    
            numGift[i] = boxes[i].size();
    
            for(int j = 0; j < boxes[i].size() - 1; j++) {
                nxt[boxes[i].get(j)] = boxes[i].get(j + 1);
                prv[boxes[i].get(j + 1)] = boxes[i].get(j);
            }
        }
    }
    
    public static void move() {
        int mSrc = sc.nextInt();
        int mDst = sc.nextInt();
        mSrc--; mDst--;
    
        if(numGift[mSrc] == 0) {
            System.out.println(numGift[mDst]);
            return;
        }
    
        if(numGift[mDst] == 0) {
            head[mDst] = head[mSrc];
            tail[mDst] = tail[mSrc];
        }
        else {
            int origHead = head[mDst];
            head[mDst] = head[mSrc];
            int srcTail = tail[mSrc];
            nxt[srcTail] = origHead;
            prv[origHead] = srcTail;
        }
    
        head[mSrc] = tail[mSrc] = 0;
    
        numGift[mDst] += numGift[mSrc];
        numGift[mSrc] = 0;
    
        System.out.println(numGift[mDst]);
    }
    
    public static int removeHead(int bNum) {
        if(numGift[bNum] == 0)
            return 0;
        
        if(numGift[bNum] == 1) {
            int id = head[bNum];
            head[bNum] = tail[bNum] = 0;
            numGift[bNum] = 0;
            return id;
        }
    
        int hid = head[bNum];
        int nextHead = nxt[hid];
        nxt[hid] = prv[nextHead] = 0;
        numGift[bNum]--;
        head[bNum] = nextHead;
    
        return hid;
    }

    public static void pushHead(int bNum, int hid) {
        if(hid == 0)
            return;
    
        if(numGift[bNum] == 0) {
            head[bNum] = tail[bNum] = hid;
            numGift[bNum] = 1;
        }
        else {
            int origHead = head[bNum];
            nxt[hid] = origHead;
            prv[origHead] = hid;
            head[bNum] = hid;
            numGift[bNum]++;
        }
    }
    
    public static void change() {
        int mSrc = sc.nextInt();
        int mDst = sc.nextInt();
        mSrc--; mDst--;
        
        int srcHead = removeHead(mSrc);
        int dstHead = removeHead(mDst);
        pushHead(mDst, srcHead);
        pushHead(mSrc, dstHead);
        
        System.out.println(numGift[mDst]);
    }
    
    public static void divide() {
        int mSrc = sc.nextInt();
        int mDst = sc.nextInt();
        mSrc--; mDst--;
    
        int cnt = numGift[mSrc];
        ArrayList<Integer> boxIds = new ArrayList<>();
        for(int i = 0; i < cnt / 2; i++)
            boxIds.add(removeHead(mSrc));
        
        for(int i = boxIds.size() - 1; i >= 0; i--)
            pushHead(mDst, boxIds.get(i));
    
        System.out.println(numGift[mDst]);
    }
    
    public static void giftScore() {
        int pNum = sc.nextInt();
    
        int a = prv[pNum] != 0 ? prv[pNum] : -1;
        int b = nxt[pNum] != 0 ? nxt[pNum] : -1;
    
        System.out.println(a + 2 * b);
    }
    
    public static void beltScore() {
        int bNum = sc.nextInt();
        bNum--;
    
        int a = head[bNum] != 0 ? head[bNum] : -1;
        int b = tail[bNum] != 0 ? tail[bNum] : -1;
        int c = numGift[bNum];
    
        System.out.println(a + 2 * b + 3 * c);
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        q = sc.nextInt();
        while(q-- > 0) {
            int qType = sc.nextInt();
            if(qType == 100)
                buildFactory();
            else if(qType == 200)
                move();
            else if(qType == 300)
                change();
            else if(qType == 400)
                divide();
            else if(qType == 500)
                giftScore();
            else
                beltScore();
        }
    }
}