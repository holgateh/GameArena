import java.lang.Math;

public static class Vector
{
    public static Vec2 add(Vec2 a, Vec2 b)
    {
        return new Vec2(a.x + b.x, a.y + b.y);
    }
    
    public static Vec2 scale(Vec2 a, float s)
    {
        return new Vec2(a.x * s, a.y * s);
    }

    public static float dot(Vec2 a, Vec2 b)
    {
        return a.x * b.x + a.y * b.y;
    }

    public static Vec2 normalise(Vec2 a)
    {
        return new Vec2(a.x / a.mag(), a.y / a.mag());
    }

    public static Vec2 perp(Vec2 a)
    {
        return new Vec2(-a.y , a.x);
    }

    public static Vec2 perpNorm(Vec2 a)
    {
        return normalise(perp(a));
    }

    public static float angle (Vec2 a, Vec2 b)
    {
        return acos(dot(a,b)/(a.mag() * b.mag()));
    }


}