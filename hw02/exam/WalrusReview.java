public class WalrusReview {
    public int v;
    public static String name;
    public WalrusReview(int v) {
        this.v = v;
        name = "Scott";
        v = -10;
    }
    public static void main(String[] args) {
        int z = 10;
        WalrusReview wr = new WalrusReview(z);
        System.out.println(z);          //10
        System.out.println(wr.v);       //10
        invertify(wr.v);
        System.out.println(wr.v);       //10
        scrub(WalrusReview.name);       
        System.out.println(WalrusReview.name); //Scott String is immutable
        z = 10;
        wr = new WalrusReview(z);
        feed(wr);
        System.out.println(z);          //10
        System.out.println(wr.v);       //unknown
        System.out.println(WalrusReview.name); //unknown change the name reference
    }
}
