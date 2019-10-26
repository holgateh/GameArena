public class Vec2
{
    public float x, y;

    public Vec2()
    {
        x = 0;
        y = 0;
    }
    
    public Vec2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float mag()
    {
        return sqrt(Vector.dot(this, this));
    }

}