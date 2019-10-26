import java.lang.Math;

public class Vec2
{
    public double x, y;

    public Vec2()
    {
        x = 0;
        y = 0;
    }
    
    public Vec2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double mag()
    {
        return Math.sqrt(Vector.dot(this, this));
    }

}