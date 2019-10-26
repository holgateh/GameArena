public class Universe
{
    private GameArena gameArena;
    
    private int width;
    private int height;

    private int numberOfMasses = 100;
    private float mass = 10;
    private float radius = 5;
    private Ball[] ball;
    private Body[] body;
    
    
    public Universe (int width, int height)
    {
        this.width = width;
        this.height = height;
        gameArena = new GameArena(width, height);

        ball = new Ball[numberOfMasses];
        body = new Body[numberOfMasses];
    }

    public void update()
    {

    }

    public void draw()
    {

    }

}