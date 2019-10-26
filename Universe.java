import java.util.Random;
import java.util.*;

public class Universe
{
    private final double G = 6.67 * Math.pow(10, -0.25);

    private GameArena gameArena;
    
    private int width;
    private int height;

    private int numberOfMasses = 250;
    private double mass = 0.5;
    private double radius = 2;
    private Ball[] ball;
    private Body[] body;

    private List<Line> line;

    private long currentTime = System.nanoTime();

    private double dt = 0;
    
    
    public Universe (int width, int height)
    {
        this.width = width;
        this.height = height;
        gameArena = new GameArena(width, height);

        ball = new Ball[numberOfMasses];
        body = new Body[numberOfMasses];

        line = new ArrayList<Line>();

        Random rand = new Random();
        for (int i = 0; i < numberOfMasses; i++)
        {
            body[i] = new Body(mass, radius, new Vec2(rand.nextInt(width), rand.nextInt(height)));
            ball[i] = new Ball(body[i].pos.x, body[i].pos.y, radius, "HOTPINK");
            gameArena.addBall(ball[i]);
        }


        /* Playing with masses for fun. */
        body[0].setMass(15);
        body[0].setRadius(25);
        ball[0].setSize(25);
        ball[0].setColour("PINK");

        body[1].setMass(15);
        body[1].setRadius(25);
        ball[1].setSize(25);
        ball[1].setColour("YELLOW");


        update();
    }

    private void update()
    {
        while(true)
        {
            long elapsedTime = System.nanoTime() - currentTime;
            currentTime = System.nanoTime();
            dt = elapsedTime / Math.pow(10, 9);
            
            calculateForces();
            updateBodies(dt);
            updateBalls();
            
            drawMassDensityGrid();
            gameArena.update();
        }
    }


    private void calculateForces()
    {
        Arrays.asList(body).parallelStream()
        .forEach(a -> {
            for(Body b : body)
            {
                if(a != b)
                {
                    double force = G * a.getMass() * b.getMass() / Math.pow(Vector.dist(b.pos, a.pos), 2);
                    a.applyForce(Vector.scale(Vector.subtract(b.pos, a.pos), force));
                }
            }

        });

    }

    private void updateBalls()
    {
        for(int i = 0; i < numberOfMasses; i++)
        {
            ball[i].setXPosition(body[i].pos.x);
            ball[i].setYPosition(body[i].pos.y);
        }
    }

    private void updateBodies(double dt)
    {
        for(int i = 0; i < numberOfMasses; i++)
        {
            body[i].update(dt);
        }
    }


    private void drawMassDensityGrid()
    {
        for(Line l : line)
        {
            gameArena.removeLine(l);
        }
        line.clear();
        drawBox(0, 0, width, height);
        for(Line l : line)
        {
            gameArena.addLine(l);
        }
    }

    private void drawBox(int x1, int y1, int x2, int y2)
    {
        if( x2 - x1 > 2 && y2 - y1 > 2)
        {
            //Create lines for the box.
            Line l1 = new Line(x1, y1, x2, y1, 1, "DARKGREEN");
            Line l2 = new Line(x2, y1, x2, y2, 1, "DARKGREEN");
            Line l3 = new Line(x2, y2, x1, y2, 1, "DARKGREEN");
            Line l4 = new Line(x1, y2, x1, y1, 1, "DARKGREEN");
            line.add(l1);
            line.add(l2);
            line.add(l3);
            line.add(l4);
            //Calculate the number of masses in this new box.

            int n1 = numberOfPointsInBox(x1, y1, x1 + (x2 - x1)/2, y1 + (y2 - y1)/2);
            int n2 = numberOfPointsInBox(x1 + (x2 - x1)/2, y1, x2, y1 + (y2 - y1)/2);
            int n3 = numberOfPointsInBox(x1, y1 + (y2 - y1)/2, x1 + (x2 - x1)/2, y2);
            int n4 = numberOfPointsInBox(x1 + (x2 - x1)/2, y1 + (y2 - y1)/2, x2 , y2);

            if(n1 + n2 + n3 + n4 > 1)
            {
                drawBox(x1, y1, x1 + (x2 - x1)/2, y1 + (y2 - y1)/2);
                drawBox(x1 + (x2 - x1)/2, y1, x2, y1 + (y2 - y1)/2);
                drawBox(x1, y1 + (y2 - y1)/2, x1 + (x2 - x1)/2, y2);
                drawBox(x1 + (x2 - x1)/2, y1 + (y2 - y1)/2, x2 , y2);
            }

        }
    }

    private int numberOfPointsInBox(int x1, int y1, int x2, int y2)
    {
        int count = 0;
        for(int i = 0; i < numberOfMasses; i++)
        {
            if(pointInBox(body[i].pos, x1, y1, x2, y2))
            {
                count++;
            }
        }
        return count;
    }

    private boolean pointInBox(Vec2 p, int x1, int y1, int x2, int y2)
    {
        if(p.x <= x2 && p.x >= x1 && p.y <= y2 && p.y >= y1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}