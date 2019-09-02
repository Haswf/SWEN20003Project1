import bagel.*;
import bagel.util.Point;
import bagel.util.Vector2;

/**
 * An example Bagel game.
 *
 * @author Eleanor McMurtry
 */
public class ShadowBounce extends AbstractGame {
    private Point initPosition;
    private Ball ball;
    private Peg[] Pegs = new Peg[50];
    private double minX = 0;
    private double maxX = 1024;
    private double minY = 100;
    private double maxY = 768;


    public ShadowBounce() {
        initPosition = new Point(512, 32);
        ball = new Ball(initPosition, new Image("res/ball.png"));
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowBounce game = new ShadowBounce();
        game.run();
    }

    /**
     * Performs a state update. This simple example shows an image that can be controlled with the arrow keys, and
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {
        if (input.isDown(MouseButtons.LEFT)) {
            Point mousePosition = input.getMousePosition();
            Vector2 direction = new Vector2(mousePosition.x - initPosition.x, mousePosition.y - initPosition.y).normalised();
            ball.setVelocity(new Velocity(direction, 10.0));
            ball.setVisibility(true);
        }

        if (ball.getVisibility()){
            ball.reCalculatedPosition();
            ball.setVelocity(ball.getVelocity().add(new Vector2(0, 0.15)));
        }

        if (ball.getPosition().x < 0 || ball.getPosition().x > Window.getWidth()){
            ball.setVelocity(ball.getVelocity().reverseHorizontal());
        }

        ball.render();

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

    }
}
