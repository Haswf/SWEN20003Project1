import bagel.*;
import bagel.util.Point;

/**
 * A class representing peg in ShadowBounce.
 * Peg is a child class of GameObject.
 * It currently doesn't have any own attribute.
 * Peg is designed to be a class for future expandability.
 * @author Shuyang Fan
 */


public class Peg extends GameObject{
    public Peg(Point position, Image image, Boolean visible){
        super(position, image, visible);
    }
}
