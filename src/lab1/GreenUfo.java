package lab1;

import java.util.ArrayList;

public class GreenUfo extends GameEntityImplementation {

    public static ArrayList<GreenUfo> instancesList = new ArrayList<GreenUfo>();
    private int hp = 1;

    public GreenUfo(ImageTile imageTile) {
        super(imageTile);
        instancesList.add(this);
    }

    public GreenUfo(ImageTile imageTile, Position ufoPosition) {
        super(imageTile, ufoPosition);
        instancesList.add(this);
    }

    public void collisionAction(Object collidedWith){
        if(collidedWith instanceof Bullet){
            decreaseHealth(1);
            ((Bullet) collidedWith).setIsAlive(false);
            if(getHp() < 1) {
                setIsAlive(false);
            }
        }else if(collidedWith instanceof Player){
            //TODO: What happens when it collides with the player?
        }
    }

    public int getHp(){
        return hp;
    }

    public void decreaseHealth(int deceasement){
             hp = hp - deceasement;
         }

    @Override
    public Position getNextPos(){
        return new Position(super.getPos().getX(), super.getPos().getY() + 1);
    }
}
