import java.awt.Color;
import java.util.Random;
import acm.graphics.GPoint;
import acm.graphics.GRect;

/**
 * @author MeganA
 * This class will account for the functionality of both Enemy types, SHOOTERS and SCOOTERS.
 * Objects of this class have specified movement patters, move projectiles, and take damage. 
 * Objects can access projectile objects and will utilize timers to control all types of its movement.
 */

public class Enemy extends ourEntity {
	
	Enemy(int fD, int life, EntityType eT, GPoint entityLocation) {					// easier to have input be EntityType and location
		super(fD, life, eT);														// based on type, assign fD and life value
		
		rect = new GRect(entityLocation.getX(), entityLocation.getY(), 30, 30);
		rect.setFilled(true);														// *********** LOOK TO REPLACE IN OURENTITY 155
																					// *********** LOOK TO REPLACE IN ENEMY 45
		switch(eT) {																// *********** LOOK TO REPLACE IN LEVEL 238
			case PLAYER:
				break;
			case SCOOTER:
				canShoot = false;
				selectSCOOTER();
				break;
			case SHOOTER:
				selectSHOOTER();
				break;
		}
	}
	
	/**These are the functions called by Enemy constructor every time an enemy is created. Depending on the
	 * type of enemy, this function randomly selects from Enemy Sprites and randomly scales them.
	 */
	
	public void selectSCOOTER() {
		int min = 7;
	    int max = 12;
	    int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
	    image.setImage(IMG_FILENAME_PATH + random_int + "Enemy" + IMG_EXTENSION);
	    int scaleMin = 0;
	    int scaleMax = 2;
	    int scaleRand = (int)Math.floor(Math.random()*(scaleMax-scaleMin+1)+scaleMin);
	    switch(scaleRand) {
	    	case 0:
	    		image.scale(0.25);			// might be too small for the speed
	    		break;
	    	case 1:
	    		image.scale(0.5);
	    		break;
	    	case 2:
	    		image.scale(0.75);
	    		break;
	    }
	}
	
	public void selectSHOOTER() {
		int min = 1;
	    int max = 6;
	    int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
	    image.setImage(IMG_FILENAME_PATH + random_int + "Enemy" + IMG_EXTENSION);
	    int scaleMin = 0;
	    int scaleMax = 2;
	    int scaleRand = (int)Math.floor(Math.random()*(scaleMax-scaleMin+1)+scaleMin);
	    switch(scaleRand) {
	    	case 0:
	    		image.scale(0.5);
	    		break;
	    	case 1:
	    		image.scale(0.75);
	    		break;
	    	case 2:
	    		image.scale(1.15);
	    		break;
	    }
	}
		
	/**This is the function called by level every time the clock ticks. It's going to move the enemy
	 * towards the player, increase curFireDelay by 1 tick, and will even fire a bullet if enough time
	 * has passed.
	 * 
	 * @param player the playerShip that enemies are trying to move/shoot towards
	 */
	public void operateEnemy(float towardsPlayer) {
		curFireTime++;
		
		movePolar(towardsPlayer);
	}
	
	/**This is the move function that Enemy will be using. It mostly just runs through movePolar 
	 * from ourEntity, but this is needed to start the moveTimer for the Enemy's movement cooldown
	 * 
	 * @param angle the angle at which you plan on moving the ship
	 * @return true if the ship moved, otherwise false
	 */
	public boolean move(float angle) {				// takes in Type to change move rates
		if(movePolar(angle))
			return true;
		else
			return false;
	}
	
	/**this is the shoot function that playerShip will be using. It essentially runs ourEntity's movePolar
	 * function, only that this one starts the shootTimer once a bullet is fired.
	 * 
	 * @param angle the direction the shot is fired
	 * @return true if a bullet was fired
	 * @return false if no bullet was fired
	 */
	public boolean shoot(float angle) {
		if(movePolar(angle))
			return true;
		else
			return false;
	}

}
