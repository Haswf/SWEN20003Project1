import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

abstract public class GameObject {

    private Point position;
    private Image image;
    private Point topLeft;
    private boolean visibility;
    private Rectangle boundingBox;

    public GameObject(){
        this.position = new Point(0,0);
    }

    public GameObject(Point position, Image image){
        this.position = position;
        this.image = image;
        this.visibility = false;
        this.topLeft = new Point(this.position.x - image.getWidth()/2, this.position.y - image.getHeight()/2);
        this.boundingBox = new Rectangle(this.topLeft, this.image.getWidth(), this.image.getHeight());
    }

    public GameObject(Point position, Image image, boolean visible){
        this.position = position;
        this.image = image;
        this.visibility = visible;
        this.topLeft = new Point(this.position.x - image.getWidth()/2, this.position.y - image.getHeight()/2);
        this.boundingBox = new Rectangle(this.topLeft, this.image.getWidth(), this.image.getHeight());
    }

    public GameObject(GameObject other){
        this.position = new Point(other.position.x, other.position.y);
        this.topLeft = new Point(other.position.x - image.getWidth()/2, other.position.y - image.getHeight()/2);
        this.image = other.image;
        this.visibility = other.visibility;
        this.boundingBox = new Rectangle(other.topLeft, other.image.getWidth(), other.image.getHeight());
    }

    public Point getPosition(){
        return new Point(this.position.x, this.position.y);
    }

    public void setPosition(Point newPosition){
        this.position = newPosition;
    }


    public Image getImage(){
        return this.image;
    }

    public void setImage(Image newImage){
        this.image = newImage;
    }


    public boolean getVisibility(){
        return this.visibility;
    }

    public void setVisibility(boolean visibility){
        this.visibility = visibility;
    }

    public void render(){
        if (this.getVisibility()){
            this.image.draw(this.getPosition().x, this.getPosition().y);
        }
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(boundingBox);
    }

    public void setBoundingBox(Rectangle bx){
        this.boundingBox = bx;
    }

    public void updateBoundingBox(){
        this.topLeft = new Point(this.position.x - image.getWidth()/2, this.position.y - image.getHeight()/2);
        this.boundingBox.moveTo(topLeft);
    }
}
