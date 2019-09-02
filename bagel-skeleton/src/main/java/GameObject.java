import bagel.*;
import bagel.util.Point;

abstract public class GameObject {

    private Point position;
    private Image image;
    private boolean visibility;

    public GameObject(){
        this.position = new Point(0,0);
    }

    public GameObject(Point position, Image image){
        this.position = position;
        this.image = image;
        this.visibility = false;
    }

    public GameObject(Point position, Image image, boolean visible){
        this.position = position;
        this.image = image;
        this.visibility = visible;
    }

    public GameObject(GameObject other){
        this.position = new Point(other.position.x, other.position.y);
        this.image = other.image;
        this.visibility = other.visibility;
    }

    public Point getPosition(){
        return this.position;
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
}
