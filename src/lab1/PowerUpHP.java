package lab1;

public class PowerUpHP extends PowerUp {
    public PowerUpHP(ImageTile imageTile, Position position) {
        super(imageTile, position);
    }

    @Override
    public void collisionAction(Object collideObject) {
        if(collideObject instanceof Player){
            this.setIsAlive(false);
            ((Player) collideObject).increaseHealth(1);
        }else if(collideObject instanceof Bullet){
            this.setIsAlive(false);
            ((Bullet) collideObject).setIsAlive(false);
        }
    }
}
