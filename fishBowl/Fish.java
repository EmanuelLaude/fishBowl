package fishBowl;

import java.util.Collection;


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
public class Fish {

    public static int SHY = -1;
    public static int NEUTRAL = 0;
    public static int IN_LOVE = 1;
    //Da man mit einem Computer natürlich nur endlich kleine Zeitintervalle
    //simulieren kann, kann es passieren, dass bei kleinen Abständen zum Rand
    //des Fischglases oder zu einem Nachbarfisch die Änderung des Ortes
    //(dx und dy bzw. die Geschwindigkeit sehr groß werden kann und sogar den
    //Wertebereich eines Integers überschreiten kann. Deshalb ist es notwendig die
    //Geschwindigkeit auf ein Maximum zu begrenzen.
    private static double MAX_SPEED = 1.7;
    private double x;
    private double y;
    private Vector speed = null;
    private int attitude;
    private String name;
    private Collection<Fish> neighbours;
    public static final int C1 = 1300;
    public static final int C2 = 1000000;
    public static final int C3 = 1000000;
    public static final int RADIUS = 10;

    /**
     * Fish
     */
    public Fish(String name, double x, double y, int attitude) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.attitude = attitude;
    }

    public int getAttitude() {
        return attitude;
    }

    private Vector getSpeed() {
        speed = new Vector();

        //Geschwindigkeitskomponente Rand (~ 1/r^4)
        {
            double dx = x - FishBowl.RADIUS;
            double dy = y - FishBowl.RADIUS;
            double r1 = Math.sqrt(dx * dx + dy * dy);
            double r2 = FishBowl.RADIUS - r1;
            double angle = Math.asin(dy / r1);
            if (dx < 0) {
                angle = Math.PI - angle;
            } else if (dy < 0) {
                angle = 2 * Math.PI + angle;
            }
            double v = -C2 * 1 / Math.pow(r2, 4);
            speed.add(new Vector(v * Math.cos(angle),
                    v * Math.sin(angle)));

        }


        //Geschwindigkeitskomponenten Nachbarn
        for (Fish neighbour : neighbours) {
            double dx = neighbour.getX() - this.x;
            double dy = neighbour.getY() - this.y;
            double r = Math.sqrt(dx * dx + dy * dy);

            double angle = Math.asin(dy / r);
            if (dx < 0) {
                angle = Math.PI - angle;
            } else if (dy < 0) {
                angle = 2 * Math.PI + angle;
            }

            double v;
            if (attitude == SHY) {
                v = -C1 * 1 / (r * r);
            } else if (attitude == IN_LOVE) {
                v = (C1 * (1 / (r * r))) - (C3 * (1 / Math.pow(r, 4)));
            } else {
                v = -(C3 * (1 / Math.pow(r, 4)));
            }
            speed.add(new Vector(v * Math.cos(angle),
                    v * Math.sin(angle)));
        }


        if (speed.getLength() > MAX_SPEED) {
            speed = new Vector(MAX_SPEED * Math.cos(speed.getAngle()), MAX_SPEED * Math.sin(speed.getAngle()));
        }

        return speed;
    }

    public void move() {
        Vector speed = this.getSpeed();
        double d = speed.getLength();
        double angle = speed.getAngle();
        this.x = x + Math.cos(angle) * d;
        this.y = y + Math.sin(angle) * d;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setNeighbours(Collection<Fish> neighbours) {
        this.neighbours = neighbours;
    }

    public String toString() {
        return "Fish (name=" + name + ", x=" + x + ", y=" + y + ")";
    }

    public String getName() {
        return name;
    }
}
