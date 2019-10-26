
public class Body
{
    private float mass;
    private float radius;
    private Vec2 acc, netForce;

    public Vec2 pos, vel;
    

    public Body(float mass, float radius)
    {
        this.mass = mass;
        this.radius = radius;
    }

    public void update(float dt)
    {
        acc = Vector.scale(netForce, 1/mass);
        vel = Vector.add(vel, Vector.scale(acc, dt));
        pos = Vector.add(pos, Vector.scale(vel, dt));
    }

    public void applyForce(Vec2 a)
    {
        netForce = Vector.add(a, netForce);
    }

    public float getMass()
    {
        return mass;
    }
    
    public float getRadius()
    {
        return radius;
    }

}