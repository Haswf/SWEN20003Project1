import bagel.*;
import bagel.util.Vector2;
import bagel.util.Point;


public class Ball extends GameObject{
    private Velocity velocity;

    public Ball(){
        super();
    }

    public Ball(Point position, Image image){
        super(position, image);
    }

    public Ball(Point position, Image image, Velocity velocity){
        super(position, image);
        this.velocity = velocity;
    }

    public Velocity getVelocity(){
        return new Velocity(new Vector2(this.velocity.getDirection().x, this.velocity.getDirection().y), this.velocity.getSpeed());
    }

    public void setVelocity(Velocity newVelocity){
        this.velocity = newVelocity;
    }

    public void reCalculatedPosition(){
        Point newPosition = (this.getPosition().asVector()).add(this.velocity.getVelocity()).asPoint();
        this.setPosition(newPosition);
    }
}
