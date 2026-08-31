package disc;


public class Planet {
    double x;
    double y;
    double mass;

    public Planet(double x, double y, double mass) {
        this.x = x;
        this.y = y;
        this.mass = mass;
    }

    public double distanceTo(Planet other) {
        return Math.sqrt(Math.pow((other.x - this.x),2) + Math.pow((other.y - this.y),2));
    }

    public static double totalMass(Planet[] ps){
        double total = 0;
        for (Planet p: ps) {
            total += p.mass;
        }
        return total;
    }
}
