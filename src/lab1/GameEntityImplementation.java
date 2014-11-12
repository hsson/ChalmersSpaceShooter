package lab1;

abstract class GameEntityImplementation implements GameEntity {

    private final ImageTile imageTile;
    private Position entityPosition;
    private boolean isAlive;

    public GameEntityImplementation(ImageTile imageTile) {
        this.imageTile = imageTile;
        GameEntity.allGameEntities.add(this);
        isAlive = true;
    }

    public GameEntityImplementation(ImageTile imageTile, Position entityPosition) {
        this(imageTile);

        setPos(entityPosition);
    }

    public Position getNextPos(){
        return new Position(entityPosition.getX(), entityPosition.getY() + 1);
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public Position getPos() {
        return this.entityPosition;
    }

    public void setPos(Position pos) {
        this.entityPosition = pos;
    }

    public ImageTile getTile() {
        return this.imageTile;
    }

}
