import bagel.*;
import bagel.util.Point;
import bagel.util.Vector2;
import java.util.Random;

/**
 * An example Bagel game.
 *
 * @author Eleanor McMurtry
 */
public class ShadowBounce extends AbstractGame {
    private Point initPosition;
    private Ball ball;
    private int numOfPegs = 50;
    private Peg[] pegs = new Peg[numOfPegs];

    private double minX = 0;
    private double maxX = 1024;
    private double minY = 100;
    private double maxY = 768;


    public ShadowBounce() {
        Random random = new Random();
        initPosition = new Point(512, 32);
        ball = new Ball(initPosition, new Image("res/ball.png"));
        for (int i=0;i<numOfPegs;i++){
            pegs[i] = new Peg(new Point(random.nextDouble()*maxX, random.nextDouble()*(maxY-minY)+minY), new Image("res/peg.png"));
            pegs[i].setVisibility(true);
        }
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
            ball.setPosition(initPosition);
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

        for (int i=0;i<numOfPegs;i++){
            pegs[i].render();
            if (ball.getBoundingBox().intersects(pegs[i].getBoundingBox())){
                pegs[i].setVisibility(false);
            }
        }

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

    }
}
