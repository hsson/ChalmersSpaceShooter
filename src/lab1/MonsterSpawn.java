package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MonsterSpawn extends GameTile{

    private static final int[] LEVEL = {0, 5, 15, 40, 100, 250};
    private int level = 1;
    private static final Image GREEN_UFO_IMAGE = new ImageIcon("img/tile-greenufo.png").getImage();
    private static final Image RED_UFO_IMAGE = new ImageIcon("img/tile-redufo.png").getImage();
    private static final Image GHOST_IMAGE = new ImageIcon("img/tile-ghost.png").getImage();

    /** The tile representing the monsters */
    private static final ImageTile GREEN_UFO_TILE = new ImageTile(GREEN_UFO_IMAGE);
    private static final ImageTile RED_UFO_TILE = new ImageTile(RED_UFO_IMAGE);
    private static final ImageTile GHOST_TILE = new ImageTile(GHOST_IMAGE);

    private int score;
    private int width;
    private int yPos;

    private double spawnRate;

    private double greenUfoSpawnRate;
    private double redUfoSpawnRate;
    private double ghostSpawnRate;
    private double tankSpawnRate;
    private double smartSpawnRate;

    Random gen = new Random();

    public MonsterSpawn(int width, int yPos){
        this.score = SpaceShooterModel.getScore();
        this.width = width;
        this.yPos = yPos;
    }

    public int getDeltaScore(int score){
        return score-LEVEL[level-1];
    }

    private void setSpawnRate(int score){
        if(level == 1){
            spawnRate = 0.07+0.01*getDeltaScore(score);
            greenUfoSpawnRate = 1;
            redUfoSpawnRate = 0;
            ghostSpawnRate = 0;
            tankSpawnRate = 0;
            smartSpawnRate = 0;
        }else if(level == 2){
            spawnRate = 0.1+0.015*getDeltaScore(score);
            greenUfoSpawnRate = 0.65;
            redUfoSpawnRate = 0.35;
            ghostSpawnRate = 0;
            tankSpawnRate = 0;
            smartSpawnRate = 0;
        }else if(level == 3){
            spawnRate = 0.2+0.01*getDeltaScore(score);
            greenUfoSpawnRate = 0.2;
            redUfoSpawnRate = 0.7;
            ghostSpawnRate = 0.1;
            tankSpawnRate = 0;
            smartSpawnRate = 0;
        }else if(level == 4){
            spawnRate = 0.25+0.005*getDeltaScore(score);
            greenUfoSpawnRate = 0;
            redUfoSpawnRate = 0.7;
            ghostSpawnRate = 0.25;
            tankSpawnRate = 0.05;
            smartSpawnRate = 0;
        }else if(level == 5){
            spawnRate = 0.4+0.0025*getDeltaScore(score);
            greenUfoSpawnRate = 0;
            redUfoSpawnRate = 0.5;
            ghostSpawnRate = 0.3;
            tankSpawnRate = 0.1;
            smartSpawnRate = 0.1;
        }else{
            spawnRate = 0.6+0.001*getDeltaScore(score);
            greenUfoSpawnRate = 0;
            redUfoSpawnRate = 0;
            ghostSpawnRate = 0.5-0.2*getDeltaScore(score);
            tankSpawnRate = 0.3+0.1*getDeltaScore(score);
            smartSpawnRate = 0.2+0.1*getDeltaScore(score);
        }
    }

    public void setLevel(int score){
        if(score > LEVEL[level] && level < LEVEL.length - 1){
            level++;
        }
    }

    public void spawnMonster(){
        this.score = SpaceShooterModel.getScore();
        setLevel(score);
        setSpawnRate(score);
        double chanceToSpawnMonster = gen.nextDouble();
        if(chanceToSpawnMonster < spawnRate){
            int xPosition = gen.nextInt(this.width);
            double chanceToSpawnType = gen.nextDouble();
            if(chanceToSpawnType < greenUfoSpawnRate){
                new GreenUfo(GREEN_UFO_TILE, new Position(xPosition, this.yPos));
            }else if(chanceToSpawnType < redUfoSpawnRate+greenUfoSpawnRate){
                new RedUfo(RED_UFO_TILE, new Position(xPosition, this.yPos));
            }else if(chanceToSpawnType < ghostSpawnRate+redUfoSpawnRate+greenUfoSpawnRate) {
                new Ghost(GHOST_TILE, new Position(xPosition, this.yPos));
            }else if(chanceToSpawnType < tankSpawnRate+ghostSpawnRate+redUfoSpawnRate+greenUfoSpawnRate){
                //TODO: NEW TANK
            }else if(chanceToSpawnType < smartSpawnRate+tankSpawnRate+ghostSpawnRate+redUfoSpawnRate+greenUfoSpawnRate){
                //TODO: NEW SMART
            }
        }

    }

}
