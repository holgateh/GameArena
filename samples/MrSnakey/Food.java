public class Food
{
	private Ball[] parts = new Ball[4];

    private int points;
	private GameArena arena;

	public Food(GameArena a)
    {
        int x = (int) (Math.random() * a.getArenaWidth());
        int y = (int) (Math.random() * a.getArenaHeight());

        this.arena = a;
        this.points = (int) ((Math.random() * 5.0) + 1);

		parts[0] = new Ball(x, y, 20, "RED");			    // Apple
		parts[1] = new Ball(x+8, y-8, 4, "TOMATO");			// Highlight
		parts[2] = new Ball(x-4, y-18, 4, "GREEN");		    // Leaf1
        parts[3] = new Ball(x-10, y-15, 4, "GREEN");		// Leaf2

        addToScreen();
	}

    public int getPoints()
    {
        return points;
    }

    public void addToScreen()
    {
        for (int i = 0 ; i < parts.length; i++)
            arena.addBall(parts[i]);
    }

    public void removeFromScreen()
    {
        for (int i = 0 ; i < parts.length; i++)
            arena.removeBall(parts[i]);
    }

    public void sendToFront()
    {
        removeFromScreen();
        addToScreen();
	}
	
    public Ball[] getParts()
    {
        return parts;
    }
    
}
