
public class Body
{
    private double mass;
    private double radius;
    private Vec2 acc, netForce;

    public Vec2 pos, vel;
    

    public Body(double mass, double radius, Vec2 pos)
    {
        this.mass = mass;
        this.radius = radius;
        this.pos = pos;
        vel = new Vec2();
        netForce = new Vec2();
    }

    public Body(double mass, double radius, Vec2 pos, Vec2 vel)
    {
        this(mass, radius, pos);
        this.vel = vel;
    }

    public void update(double dt)
    {
        acc = Vector.scale(netForce, 1/mass);
        vel = Vector.add(vel, Vector.scale(acc, dt));
        pos = Vector.add(pos, Vector.scale(vel, dt));
        // Reset netForce
        netForce = new Vec2();
    }

    public void applyForce(Vec2 a)
    {
        netForce = Vector.add(a, netForce);
    }

    public void setMass(double m)
    {
        this.mass = m;
    }

    public void setRadius(double r)
    {
        this.radius = r;
    }

    public double getMass()
    {
        return mass;
    }
    
    public double getRadius()
    {
        return radius;
    }

}