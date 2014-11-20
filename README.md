Chalmers Space Shooter
======================

This is an exercise in object oriented programming at Chalmers University of Technology
where we took a very simple game framework for Java and made a simple game in it.

##Summary

###Framework changes
- Changes the size of the game
- Changes the update interval
- Adds a background image

###Features
This is a space game where enemies spawn from the top and scroll down - a so called
top-down scroller game.

There are currently three types of enemies
- Green Ufo, a very basic unit with 1 HP
- Red Ufo, a tougher enemy with 5 HP
- Ghost, a unit that moves towards the player and has 50% dodge chance

There are also two types of powerups in the game
- Health, increase the player health by 1 HP
- Damage Upgrade, upgrades the players bullets to do more damage

As the game progresses it will become harder and harder. This is based on the score
that the player collects by shooting enemies. Each enemy give a different amount of
score depending on their toughness.
