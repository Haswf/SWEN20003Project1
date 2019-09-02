import bagel.util.Vector2;
import bagel.util.Point;

public class Velocity {
    private Vector2 direction;
    private double speed;

    public Velocity(Vector2 direction, double speed){
        this.direction = direction.normalised();
        this.speed = speed;
    }

    public Velocity(Vector2 newVelocity){
        setDirection(newVelocity.normalised());
        setSpeed(newVelocity.length());
    }

    public Vector2 getDirection(){
        return new Vector2(this.direction.x, this.direction.y);
    }

    public void setDirection(Vector2 newDirection){
        this.direction = newDirection;
    }

    public double getSpeed(){
        return this.speed;
    }

    public void setSpeed(Double newSpeed){
        this.speed = newSpeed;
    }

    public Velocity add(Vector2 offset){
        setDirection(this.direction.mul(this.speed).add(offset).normalised());
        setSpeed(this.direction.mul(this.speed).add(offset).length());
        return this;
    }

    public double getXSpeed(){
        return this.getDirection().x * this.getSpeed();
    }

    public double getYSpeed(){
        return this.getDirection().y * this.getSpeed();
    }

    public Vector2 getVelocity(){
        return this.direction.mul(this.speed);
    }

    public Velocity reverseHorizontal(){
        setDirection(new Vector2(this.getDirection().x * -1, this.getDirection().y));
        return this;
    }

    public Velocity reverseVertical(){
        setDirection(new Vector2(this.getDirection().x, this.getDirection().y * -1));
        return this;
    }
}
