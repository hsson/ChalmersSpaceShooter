package lab1;

import java.util.ArrayList;

public class RedUfo extends GameEntityImplementation {

    public static ArrayList<RedUfo> instancesList = new ArrayList<RedUfo>();
    private int hp = 5;

    public RedUfo(ImageTile imageTile) {
        super(imageTile);
        instancesList.add(this);
    }

    public RedUfo(ImageTile imageTile, Position ufoPosition) {
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

    public void decreaseHealth(int decreasement){
             hp = hp - decreasement;
         }

    @Override
    public Position getNextPos(){
        return new Position(super.getPos().getX(), super.getPos().getY() + 1);
    }
}
