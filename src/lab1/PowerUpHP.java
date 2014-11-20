package lab1;

/**
 * Created by Criticalstone on 20-Nov-14.
 */
public class PowerUpHP extends PowerUp {
    public PowerUpHP(ImageTile imageTile, Position position) {
        super(imageTile, position);
    }

    @Override
    public void collisionAction(Object collideObject) {
        if(collideObject instanceof Player){
            this.setIsAlive(false);
            ((Player) collideObject).increaseHealth(1);
            System.out.println("collided with hp up");
        }else if(collideObject instanceof Bullet){
            this.setIsAlive(false);
            ((Bullet) collideObject).setIsAlive(false);
        }
    }
}
