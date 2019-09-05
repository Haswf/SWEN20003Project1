import bagel.*;
import bagel.util.Vector2;
import bagel.util.Point;

/**
 * A class representing Ball for ShadowBounce.
 *
 * @author Shuyang Fan
 */
public class Ball extends GameObject{
    private Velocity velocity;

    /*
    Basic constructor for Ball where velocity is not provided.
     */
    public Ball(Point position, Image image){
        super(position, image);
    }

    /* Constructor for Ball with a given velocity
    */
    public Ball(Point position, Image image, Velocity velocity){
        super(position, image);
        this.velocity = velocity;
    }

    /* Return a Velocity object representing current movement of the object. */
    public Velocity getVelocity(){
        return new Velocity(this.velocity);
    }

    /* Set Velocity of the ball with given Velocity. */
    public void setVelocity(Velocity newVelocity){
        this.velocity = newVelocity;
    }

    /* Recalculate the position of the Ball based on its current velocity.
       This method should be called exactly once every frame.
    */
    public void recalculatePosition(){
        Point newPosition = (this.getPosition().asVector()).add(this.velocity.asVector()).asPoint();
        this.setPosition(newPosition);
    }
}
