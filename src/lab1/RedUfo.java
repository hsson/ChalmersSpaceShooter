package lab1;

import java.util.ArrayList;

public class RedUfo extends GameEntityImplementation {

    public static ArrayList<RedUfo> instancesList = new ArrayList<RedUfo>();
    private int hp = 5;
    private static final int SCORE = 3;

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
            if(getHp() <= 0) {
                SpaceShooterModel.increaseScore(SCORE);
                setIsAlive(false);
            }
        }else if(collidedWith instanceof Player){
            ((Player) collidedWith).decreaseHealth(1);
            this.decreaseHealth(1);
            if(getHp() <= 0){
                SpaceShooterModel.increaseScore(SCORE);
                this.setIsAlive(false);
            }
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
