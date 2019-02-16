public class MrSnakey
{
    private GameArena arena = new GameArena(1000,1000);
    private Snake snake;
    private Food food;

    public MrSnakey()
    {
        boolean playing = true;

        while(true)
        {
            snake = new Snake(250,250,arena);
            food = new Food(arena);       
            playing = true;

            while(playing)
            {
                if (arena.upPressed())
                    snake.goUp();
                if (arena.downPressed())
                    snake.goDown();
                if (arena.leftPressed())
                    snake.goLeft();
                if (arena.rightPressed())
                    snake.goRight();

                snake.move();

                if (snake.collides(food))
                {
                    snake.grow(food.getPoints());
                    food.removeFromScreen();
                    food = new Food(arena);
                }

                if (snake.hasCrashed())
                    playing = false;

                arena.update();
            }

            System.out.println("******** GAME OVER ********");
            System.out.println("-------- " + snake.getLength() + "-------- ");

            snake.removeFromScreen();
            food.removeFromScreen();
        }
    }

    public static void main(String[] args)
    {
        MrSnakey s = new MrSnakey();
    }
}