package demos;

/**
 * Created by Serhiy on 10.12.2015.
 */
public class ArrayLocation {

    public double[] coords;
    public ArrayLocation(double[] coords)
    {
        this.coords = coords;
    }

    public static void main(String[] args) {
        double[] c = {2.0, 3.0};
        ArrayLocation afr = new ArrayLocation(c);
        c[0] = 21.0;
        c[1] = 31.0;
        System.out.println(afr.coords[0] + ", " + afr.coords[1]);
    }
}
