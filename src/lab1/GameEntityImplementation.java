package lab1;

abstract class GameEntityImplementation implements GameEntity {

    private final ImageTile imageTile;
    private Position entityPosition;
    private boolean isAlive;

    /**
     * A default constructor.
     *
     * @param imageTile The ImageTile that will be drawn for this object
     */
    public GameEntityImplementation(ImageTile imageTile) {
        this.imageTile = imageTile;
        GameEntity.allGameEntities.add(this);
        isAlive = true;
    }

    /**
     * A default constructor
     *
     * @param imageTile The ImageTile that will be drawn for this object
     * @param entityPosition The position where the object will start
     */
    public GameEntityImplementation(ImageTile imageTile, Position entityPosition) {
        this(imageTile);

        setPos(entityPosition);
    }

    /**
     * Gets the next position where the object should move in next tile.
     * This determines how the object moves in the application and could include
     * logic to enable moving in a certain pattern.
     *
     * @return The next Position for the object
     * @see lab1.Position
     */
    public Position getNextPos(){
        return new Position(entityPosition.getX(), entityPosition.getY());
    }

    /**
     * Returns if the object is alive or not.
     *
     * @return Returns true if the object is alive, false if it's not
     */
    public boolean getIsAlive() {
        return this.isAlive;
    }

    /**
     * Set if the object is alive or not. This is good for determining
     * if the object should be drawn or not, as an example.
     *
     * @param isAlive Set if the object should be alive or not
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * Get the current position of the object.
     *
     * @return Returns the current Position
     * @see lab1.Position
     */
    public Position getPos() {
        return this.entityPosition;
    }

    /**
     * Sets the position for the object using the Position class
     * defined in this package.
     *
     * @param pos The new position to be used
     * @see lab1.Position
     */
    public void setPos(Position pos) {
        this.entityPosition = pos;
    }

    /**
     * A method for returning the ImageTile associated with the object.
     * This is preferably used to dynamically get the matching tile instead
     * of having to hard code it. This makes it possible to change the associated
     * ImageTile in one line of code and make the change happen all over the application.
     *
     * @return The ImageTile used to represent the object
     * @see lab1.ImageTile
     * */
    public ImageTile getTile() {
        return this.imageTile;
    }

}
