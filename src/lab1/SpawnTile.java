package lab1;

public class SpawnTile {

    private MonsterSpawn monsterSpawn;
    private PowerUpSpawn powerUpSpawn;

    private int width, yPos;

    public SpawnTile(int width, int yPos) {
        this.width = width;
        this.yPos = yPos;

        monsterSpawn = new MonsterSpawn(width, yPos);
        powerUpSpawn = new PowerUpSpawn(width, yPos);
    }

    public void spawn() {
        monsterSpawn.spawnMonster();
        powerUpSpawn.spawnPowerUp();
    }
}
