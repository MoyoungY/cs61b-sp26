package exam;

public class Walrus {
    public static void main(String[] args) {
        int x = 10;
        obliterate(x);
        System.out.println(x); // 10
        int y = 20;
        IntSquasher isq = new IntSquasher(y);
        System.out.println(y); // 20
        int[] z = new int[]{1, 2, 3};
        shamble(z[0]);
        System.out.println(z[0]); //1
        agglutinate(z);
        System.out.println(z[1]); // unknown
    }
}
