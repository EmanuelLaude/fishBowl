package fishBowl;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

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


public class FishBowl extends JApplet {
    ArrayList<Fish> fishes = new ArrayList<Fish>();
    Image buffer = null;
    Graphics2D gBuffer = null;
    public static final int RADIUS = 325;
    private static final int SLEEP = 20;

    public FishBowl() {
        fishes.add(new Fish("Christina", 190, 50, Fish.NEUTRAL));
        fishes.add(new Fish("Edwin", 304, 340, Fish.NEUTRAL));
        fishes.add(new Fish("Lars", 179, 179, Fish.NEUTRAL));
        fishes.add(new Fish("Emanuel", 150, 150, Fish.SHY));
        fishes.add(new Fish("Philipp", 190, 300, Fish.SHY));
        fishes.add(new Fish("Hans", 159, 159, Fish.SHY));
        fishes.add(new Fish("Tanja", 212, 312, Fish.SHY));
        fishes.add(new Fish("Erwin", 60, 150, Fish.IN_LOVE));
        fishes.add(new Fish("Marcus", 260, 400, Fish.IN_LOVE));
        fishes.add(new Fish("Lisa", 400, 400, Fish.IN_LOVE));
        fishes.add(new Fish("Regina", 300, 312, Fish.NEUTRAL));
        fishes.add(new Fish("Johannes", 200, 170, Fish.NEUTRAL));
        fishes.add(new Fish("Martin", 60, 300, Fish.NEUTRAL));
        fishes.add(new Fish("Jakob", 100, 159, Fish.IN_LOVE));
        fishes.add(new Fish("Michael", 400, 312, Fish.SHY));
        fishes.add(new Fish("Ralf", 100, 120, Fish.IN_LOVE));
        fishes.add(new Fish("Anja", 280, 480, Fish.IN_LOVE));
        fishes.add(new Fish("Martina", 480, 300, Fish.IN_LOVE));
        fishes.add(new Fish("Siri", 40, 312, Fish.SHY));
        fishes.add(new Fish("Thomas", 159, 150, Fish.SHY));
        fishes.add(new Fish("Anita", 199, 300, Fish.SHY));
        fishes.add(new Fish("Timo", 168, 159, Fish.SHY));
        fishes.add(new Fish("Christian", 221, 312, Fish.SHY));
        fishes.add(new Fish("Veronika", 107, 150, Fish.IN_LOVE));
        fishes.add(new Fish("Agnes", 269, 400, Fish.IN_LOVE));
        fishes.add(new Fish("Tina", 409, 400, Fish.IN_LOVE));
        fishes.add(new Fish("Antonia", 309, 312, Fish.SHY));
        fishes.add(new Fish("Nina", 290, 170, Fish.IN_LOVE));
        fishes.add(new Fish("Manuela", 69, 300, Fish.IN_LOVE));
        fishes.add(new Fish("Florian", 109, 159, Fish.IN_LOVE));
        fishes.add(new Fish("Ines", 409, 312, Fish.IN_LOVE));
        fishes.add(new Fish("Jonas", 109, 120, Fish.IN_LOVE));
        fishes.add(new Fish("Elias", 289, 480, Fish.IN_LOVE));
        fishes.add(new Fish("Franziska", 489, 300, Fish.IN_LOVE));
        fishes.add(new Fish("Mona", 49, 312, Fish.SHY));

        for(Fish fish : fishes) {
            ArrayList<Fish> neighbours = (ArrayList<Fish>) fishes.clone();
            neighbours.remove(fish);
            fish.setNeighbours(neighbours);
        }
    }

    public void init() {
        this.resize(650, 650);
        setBackground(new Color(255, 255, 255));
    }

    public void paint(Graphics g) {
       //initialisiere Buffer
        if (buffer == null) {
            buffer = createImage(this.getSize().width, this.getSize().height);
            gBuffer = (Graphics2D) buffer.getGraphics();

        }


        long startMillis = System.currentTimeMillis();
        gBuffer.clearRect(0, 0, this.getSize().width, this.getSize().height);

        gBuffer.setColor(Color.BLACK);
        gBuffer.drawOval(0, 0, RADIUS * 2, RADIUS * 2);
        Iterator i = fishes.iterator();
        while (i.hasNext()) {
            Fish fish = (Fish) i.next();
            if(fish.getAttitude() == Fish.SHY)
                gBuffer.setColor(Color.ORANGE);
            else if(fish.getAttitude() == Fish.IN_LOVE)
                gBuffer.setColor(Color.RED);
            else
               gBuffer.setColor(Color.BLACK);


            gBuffer.fillOval((int) Math.ceil(fish.getX()),
                             this.getHeight() - (int) Math.ceil(fish.getY()),
                             Fish.RADIUS, Fish.RADIUS);
            gBuffer.drawString(fish.getName(), (int) fish.getX(),
                               this.getHeight() - (int) Math.ceil(fish.getY()));

            if(fish.getX() < 0 || fish.getX() > RADIUS*2 || fish.getY() < 0 || fish.getY() > RADIUS*2) {
                System.out.println("Error: Fish outside fishbowl ["+ fish + "]");
            }
            fish.move();
        }

        g.drawImage(buffer, 0, 0, this);
        long endMillis = System.currentTimeMillis();
        long sleep = SLEEP - (endMillis-startMillis);
        try {
            Thread.sleep((sleep < 0 ? 0 : sleep));
        } catch (InterruptedException e) {
        }
        repaint();
    }

    public void update(Graphics g) {
        paint(g);
    }
}
