package seng202.team7;

/**
 * Created by mme88 on 4/10/17.
 */
public class PointM {
    private int x;
    private int y;

    public PointM(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public PointM(double x, double y)
    {
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void print()
    {
        System.out.println("X: " + x + " | Y: " + y);
    }
}
