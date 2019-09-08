import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract GameObject class.
 *
 * @author Shuyang Fan
 */

abstract public class GameObject {

    private Point position; // position of object on the screen
    private Image image; // image of the object
    private Point topLeft; // top left point of the object on the screen
    private boolean visibility;
    private Rectangle boundingBox; // bounding box for collision detection

    // constructor for empty GameObject
    @org.jetbrains.annotations.Contract(pure = true)
    public GameObject() {
        this.position = new Point(0, 0); // by default, the position is set to (0,0)
        this.visibility = false;
    }

    // constructor for GameObject where both position and image are provided.
    public GameObject(Point position, @NotNull Image image) {
        this.position = position;
        this.image = image;
        this.visibility = false; // by default, the object is set to invisible

        // Move the bounding box to the correct position.
        this.topLeft = new Point(this.position.x - image.getWidth() / 2, this.position.y - image.getHeight() / 2);
        this.boundingBox = this.image.getBoundingBox();
        this.boundingBox.moveTo(topLeft);
    }

    // constructor for GameObject where position, image and visibility are provided.
    public GameObject(Point position, @NotNull Image image, boolean visible) {
        this.position = position;
        this.image = image;
        this.visibility = visible;

        // Move the bounding box to the correct position.
        this.topLeft = new Point(this.position.x - image.getWidth() / 2, this.position.y - image.getHeight() / 2);
        this.boundingBox = this.image.getBoundingBox();
        this.boundingBox.moveTo(topLeft);
    }

    // copy constructor
    public GameObject(@NotNull GameObject other) {
        this.position = new Point(other.position.x, other.position.y);
        if (other.getImage() != null) {
            this.topLeft = new Point(other.position.x - image.getWidth() / 2, other.position.y - image.getHeight() / 2);
            this.image = other.image;
            this.boundingBox = new Rectangle(other.topLeft, other.image.getWidth(), other.image.getHeight());
        }
        this.visibility = other.visibility;
    }

    /* Return the position of the GameObject as a Point.
     */
    public Point getPosition() {
        return new Point(this.position.x, this.position.y);
    }

    /* Moves the GameObject so that its centre is at the specified point. */
    public void setPosition(Point newPosition) {
        this.position = newPosition;
        updateTopLeft();
        updateBoundingBox();
    }

    /* Update position of TopLeft */
    private void updateTopLeft() {
        this.topLeft = new Point(this.position.x - image.getWidth() / 2, this.position.y - image.getHeight() / 2);
    }

    /* Return the image of the GameObject */
    public Image getImage() {
        return this.image;
    }

    /*
    Set the image of the GameObject
     */
    public void setImage(Image newImage) {
        this.image = newImage;
    }

    /* Return an boolean representing visibility of the GameObject. */
    public boolean getVisibility() {
        return this.visibility;
    }

    /* Set visibility of the GameObject. The GameObject will be rendered if visibility is True. */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /* Return the Bounding Box of the gameObject as a Rectangle*/
    public Rectangle getBoundingBox() {
        return new Rectangle(boundingBox);
    }

    /* Set the Bounding Box of the gameObject with a given Rectangle*/
    public void setBoundingBox(Rectangle bx) {
        this.boundingBox = bx;
    }

    /* Move Bounding box to right position after position has been changed */
    private void updateBoundingBox() {
        this.boundingBox.moveTo(topLeft);
    }

    /* Return distance from this GameObject to another. */
    private double distance(GameObject other){
        return other.getPosition().asVector().sub(this.getPosition().asVector()).length();
    }

    /* Render the GameObject if its visibility is True */
    public void render() {
        if (this.getVisibility()) {
            this.image.draw(this.getPosition().x, this.getPosition().y);
        }
    }
}
