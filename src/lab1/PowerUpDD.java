package lab1;

public class PowerUpDD extends PowerUp{
    public PowerUpDD(ImageTile imageTile, Position position) {
        super(imageTile, position);
    }

    @Override
    public void collisionAction(Object collideObject) {
        if(collideObject instanceof Player){
            this.setIsAlive(false);
            ((Player) collideObject).setPlayerBullet(Player.Bullets.DOUBLE);
        }else if(collideObject instanceof Bullet){
            this.setIsAlive(false);
            ((Bullet) collideObject).setIsAlive(false);
        }
    }

}
