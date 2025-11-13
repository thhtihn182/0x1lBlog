package top.blogapi.util;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.util.*;

public class MD5Utils {
    private static final String SALT = "mySecret";

    public static String getMD5(CharSequence str) {
        String base = SALT + str;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
    static int index = 0;
    public static String decodeString(Str str) {
        StringBuilder sb = new StringBuilder();
        char[] ar = "aaaa3[a2[c]]3[b2[d]]aaaaaaaaaaaa".toCharArray();
        while(ar[index] >= 'a' && ar[index] <= 'z' ){
            System.out.println("");
            sb.append(ar[str.index++]);
        }
        return sb.append(gen(str,3-2,ar)).toString();

    }
       static class Str{
        String str;
        int index;
    }
    public static String gen(Str str, int times, char[] ar){
        StringBuilder sb = new StringBuilder();

        System.out.println(ar[str.index]);
        while(str.index< ar.length&&ar[str.index] != ']'){
            if(ar[str.index] >= 'a' && ar[str.index] <= 'z')
                sb.append(ar[str.index]);
            else {
                str.index+=2;
                sb.append(gen(str,ar[str.index-2]-'0', ar));
            }
            str.index++;
        }
        String s = sb.toString();
        System.out.println(s);
        return sb.repeat(s,times-1).toString();
    }


    int t = 4;
    class A{

        int l;
        A(int l){
            this.l = l + t;
        }
    }
    public static boolean hasSameDigits(String s) {
        int leng = s.length();
        int j = 1 ;
        char[] c = s.toCharArray();
        while(j<leng-1){
            for(int i = leng-1 ; i>=j;i--){
                System.out.println(((c[i]+c[i-1])-(2*'0'))%10);
                char c1 = (char)(((c[i]+c[i-1])-(2*'0'))%10+'0');
                char c2 = 65;
                System.out.println(c1);
                c[i] = c1;
            }
            System.out.println(new String(c));
            j++;
        }
        return c[leng-2] ==c[leng-1];
    }
    public  static int findSmallestInteger2(int[] nums, int value) {
        Map<Integer,Integer> map = new HashMap<>();

        boolean[] m = new boolean[nums.length+1];

        for(int i = 0 ; i < nums.length ;i++){

            if(nums[i] < 0){
                nums[i] += value*((-nums[i] / value)+1) ;
            }
            int d = nums[i] % value;
            if(!map.containsKey(d)){
                if(d < m.length){
                    map.put(d,1);
                    System.out.println("d: "+ d + " nums[i]: "+nums[i]);
                    m[d] = true;
                }

            }
            else{
                map.computeIfPresent(d,(k,v)->++v);
                int r = value*map.get(d);
                System.out.println("r: "+ r+ " nums[i]: "+nums[i]);
                if(r < m.length)
                    m[r] = true;
            }

        }

        for(int i = 0 ; i < nums.length;i++){
            if(!m[i]){
                return i;
            }

        }
        return nums.length;
    }
    public static int findSmallestInteger(int[] nums, int value) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<Integer>();
        boolean[] m = new boolean[nums.length+1];

        for(int i = 0 ; i < nums.length ;i++){
            System.out.println(nums[i] + ": ");
            if(nums[i] < 0)
                nums[i] *= -1;
            int d = nums[i] % value;
            while(d < m.length){
                if(!set.contains(d)){
                    set.add(d);
                    m[d] = true;
                    break;
                }
                System.out.print(d +" ");
                d+= value;
            }
            System.out.println();
        }

        for(int i = 0 ; i < nums.length;i++){
            if(!m[i]){
                System.out.println("yeas");
                return i;
            }

        }
        return nums.length;
    }
    public static int ab (int num,int value){
        return num + value*((-num / value)+1);
    }

    public static String RLE(int n){
        StringBuilder sb = new StringBuilder("1");
        for (int i = 2 ; i <= n ; i++){
            int count= 1;
            char digit = sb.charAt(0);
            StringBuilder sbT = new StringBuilder();
            for(int j = 1 ; j < sb.length() ; j++){
                if(digit == sb.charAt(j)){
                    count++;
                }else{
                    sbT.append(count).append(digit);
                    count = 1;
                    digit = sb.charAt(j);
                }
            }
            sbT.append(count).append(digit);
            sb=sbT;
        }
        return sb.toString();
    }

    static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
        Node(int val,Node next){
            this.val = val;
            this.next = next;
        }
        Node(){}
    }


    public static long getRGBbyte(int red, int green, int blue) {
        return (red* 65536L)+(green* 256L)+blue ;
    }


    public static void extendGcd(){
        int[] a = new int[]{2,5,1,2,3,1,1,4,1};
        int[] b = new int[]{1,6};
        int j = 1;
        for(int i = 0 ; i < a.length ; i++){
            b[i&1] = b[(i+1) & 1] * a[i] + b[i&1];
            System.out.print(32321*-b[0]+b[1]*26513 + " == ");
            System.out.print(32321*b[0]-b[1]*26513  + " == ");
            System.out.print(32321*-b[1]+b[0]*26513 + " == ");
            System.out.print(32321*b[1]-b[0]*26513  + " == ");
            System.out.println(b[0] + " | " + b[1]);
        }

        System.out.print(32321L*-b[1]+b[0]*26513L + " == ");
    }

    public static void gcd(){
        int b = 26513;
        int a = 32321;
        while(b != 0){
            int temp = a % b;
            System.out.println(a + " = " + (a/b)+"("+b+") + " + temp);
            a = b;
            b = temp;

        }
        System.out.println(a);
    }

    public static void XOR_you_dont(){
        char[] cipher = "0e0b213f26041e480b26217f27342e175d0e070a3c5b103e2526217f27342e175d0e077e263451150104".toCharArray();
        byte[] crypto = "crypto{".getBytes();
        char[] k = "0e0b213f26041e".toCharArray();
        StringBuilder guestKey = new StringBuilder();
        StringBuilder flag = new StringBuilder();

        for(int i =0 ; i < crypto.length ; i++){
            guestKey.append((char)(crypto[i]^Integer.parseInt(k[i*2]+""+k[i*2+1],16)));
        }
        System.out.println(guestKey.toString()) ;// myXORke -> myXORkey
        byte[] key = guestKey.append("y").toString().getBytes();
        for(int i = 0 ; i < cipher.length ; i+=2 ){
            flag.append((char)(key[i/2%key.length]^Integer.parseInt(cipher[i]+""+cipher[i+1],16)));
        }
        System.out.println(flag.toString()) ;
    }

    public static void Favourite_byte(){
        BigInteger flag = new BigInteger("0e0b213f26041e480b26217f27342e175d0e070a3c5b103e2526217f27342e175d0e077e263451150104",16);
        System.out.println(flag.toString());
        System.out.println(flag.toString(16));
        System.out.println("0e0b213f26041e480b26217f27342e175d0e070a3c5b103e2526217f27342e175d0e077e263451150104".length());
        for(int z = 0 ; z <=255;z++){
//            System.out.println(flag.xor(new BigInteger(Integer.toBinaryString(i))));
            BigInteger x = new BigInteger(z+"");
            char[] c = "0e0b213f26041e480b26217f27342e175d0e070a3c5b103e2526217f27342e175d0e077e263451150104".toCharArray();
//            for(int p = 0 ; p < c.length ; p++)
//                c[p] = (char)(c[p]^z);
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < c.length;i+=2){
                sb.append((char)(Integer.parseInt((c[i])+""+(c[i+1]),16)^z) );
            }
//            c = sb.toString().toCharArray();
//            for(int i = 0 ; i < c.length;i++){
//                c[i] = (char)(c[i]^ z);
//            }
//            System.out.println(z+ ": " +  new String(c));
            System.out.println(sb);
        }
    }

    public static String XOR_Properties(String key1, String key2, String fl){
        BigInteger key1B = new BigInteger(key1,16);
        BigInteger key2B = new BigInteger(key2,16);
        BigInteger flag = new BigInteger(fl,16);

        flag = flag.xor(key1B).xor(key2B);
        System.out.println("xor: " +flag.toString(16));
        System.out.println(flag.toString(16).length());
        char[] c = flag.toString(16).toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < c.length;i+=2){
            sb.append((char)Integer.parseInt(c[i]+""+c[i+1],16) );
        }
        return sb.toString();
    }

    public static String XOR_Starter(String s, int i){
        if(i == s.length()){
            return "";
        }
        return (char)(s.charAt(i)^13)+XOR_Starter(s, i+1);
    }

    public static void de(){
        Node head = null;
        Node temp = null;
        for(int i = 1; i <= 5;i++){
            Node newNode = new Node(i);
            if(i==1){
                head = (temp=newNode);
            }
            temp.next = newNode;
            temp = temp.next;
        }
        temp=head;
        while(temp!=null){
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        Node h = head;
        Node c = null;
        Node prev = (temp=head);
        System.out.println();
        for(int z = 1; z >0 ; z--){
            prev = temp;
            for(int i = 1 ;i <= 3; i++){
                Node next = h.next;
                h.next = temp;
                temp = h;
                h = next;
            }
            if(z==1){
                head.next = h;
                c=head;
                head=temp;
            }else{
                c.next =temp;
                c= prev;
            }
            System.out.print(temp.val + " -> ");
            temp = h;

        }
        System.out.println("/n " + h.val);
        prev.next=h;


        temp=head;
        while(temp!=null){
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
    }
    public static void modular(){
        long x= 1;
        int y = 5;
        for(int i = 1; i <= 16 ; i++){
            x *=y;
        }
        System.out.println(x%17 );
    }
    public static void main(String[] args) {
        modular();
    }
}
