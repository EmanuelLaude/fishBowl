package fishBowl;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Vector {
    private double x;
    private double y;

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        double angle = Math.asin(y / getLength());
        if (x < 0) {
            angle = Math.PI - angle;
        } else if (y < 0) {
            angle = 2 * Math.PI + angle;
        }
        return angle;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public void add(Vector vector) {
        x = x + vector.getX();
        y = y + vector.getY();
    }

    public String toString() {
        return "Vector (x=" + (int) x + ", y=" + (int) y + ")";
    }
}
