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
    private Peg[] pegs;

    // the range where peg will be randomly generated.
    private double minX = 0;
    private double maxX = 1024;
    private double minY = 100;
    private double maxY = 768;
    private Image background;
    /*
     * ShadowBounce
     */
    public ShadowBounce() {
        Random random = new Random(); // random generator to randomly place Peg
        initPosition = new Point(512, 32); // initial position where Ball will be generated
        background = new Image("res/background.jpg");
        ball = new Ball(initPosition, new Image("res/ball.png"));

        pegs = new Peg[numOfPegs]; // An array of pegs

        // Randomly generate 50 pegs
        for (int i=0;i<numOfPegs;i++){
            Point position = new Point(0, 0);
            boolean isOverlapped = true;
            outer: while (isOverlapped){
                position = new Point(random.nextDouble()*maxX + minX, random.nextDouble()*(maxY-minY)+minY);
                for (int j=0; j<i; j++){
                    double distance = pegs[j].getPosition().asVector().sub(position.asVector()).length();
                    double right = pegs[j].getBoundingBox().right();
                    double left = pegs[j].getBoundingBox().left();
                    if (distance < right - left){
                        isOverlapped = true;
                        continue outer;
                    }
                }
                break;
            }
            pegs[i] = new Peg(position, new Image("res/peg.png"), true);
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
        if (input.isDown(MouseButtons.LEFT) && !ball.getVisibility()) {
            ball.setPosition(initPosition);
            Point mousePosition = input.getMousePosition();
            Vector2 mouseDirection = mousePosition.asVector().sub(initPosition.asVector()).normalised();
            ball.setVelocity(new Velocity(mouseDirection, 10.0));
            ball.setVisibility(true);
        }

        // Quit game if ESCAPE key was pressed
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        // Update the ball if it is visible.
        if (ball.getVisibility()){
            ball.recalculatePosition(); // recalculate position based on velocity
            // increase vertical speed to simulate gravity if the Ball is visible.
            ball.setVelocity(ball.getVelocity().add(new Vector2(0, 0.15)));
        }

        // reverse horizontal movement when the ball reaches the left or right sides.
        if (ball.getPosition().x < 0 || ball.getPosition().x > Window.getWidth()){
            ball.setVelocity(ball.getVelocity().reverseHorizontal());
        }

        // make the ball invisible when it drops out of the window
        if (ball.getPosition().y > Window.getHeight()){
            ball.setVisibility(false);
        }
        // Draw the background image
        background.draw(Window.getWidth()/2.0,Window.getHeight()/2.0);

        // render the ball
        ball.render();

        for (int i=0;i<numOfPegs;i++){
            // make the peg disappear if it was hit by the ball
            if (ball.getBoundingBox().intersects(pegs[i].getBoundingBox())){
                pegs[i].setVisibility(false);
            }
            // render each peg
            pegs[i].render();
        }
    }
}
