import acm.graphics.GPoint;
import acm.graphics.GRect;

/**
 * @author MeganA
 * This class will account for the functionality of both Enemy types, SHOOTERS and SCOOTERS.
 * Objects of this class have specified movement patters, move projectiles, and take damage. 
 * Objects can access projectile objects and will utilize timers to control all types of its movement.
 */

public class Enemy extends ourEntity {
	
	Enemy(GPoint entityLocation, EntityType type) {						// just to get rid of red squiggly
		health = 200;
		friendly = false;
		this.type = type;
		rect = new GRect(entityLocation.getX(), entityLocation.getY(), 30, 30);
		rect.setFilled(true);
		if (type == EntityType.SCOOTER) {
			speed = 3;
			canShoot = false;
		}
		else if (type == EntityType.SHOOTER) {
			speed = 2;
			fireDelay = 50;
			shootTimer.schedule(shootTask, 2000, 600);
		}
		moveTimer.schedule(moveTask, 2000, DELAY_MS); //starts movement cooldown timer
	}
		
	/**This is the function called by level every time the clock ticks
	 * 
	 * @param player the playerShip that enemies are trying to move/shoot towards
	 */
	public void operateEnemy(playerShip player) {
		float towardsPlayer = Logic.getAngle(rect, player.getRect()); //calculates angle towards playerShip
		move(towardsPlayer);
		shoot(towardsPlayer);
	}
	
	/**This is the move function that Enemy will be using. It mostly just runs through movePolar 
	 * from ourEntity, but this is needed to start the moveTimer for the Enemy's movement cooldown
	 * 
	 * @param angle the angle at which you plan on moving the ship
	 * @return true if the ship moved, otherwise false
	 */
	public boolean move(float angle) {				// takes in Type to change move rates
		if(movePolar(angle)) {
			
			return true;
			
		}else {return false;}
	}
	
	/**this is the shoot function that playerShip will be using. It essentially runs ourEntity's movePolar
	 * function, only that this one starts the shootTimer once a bullet is fired.
	 * 
	 * @param angle the direction the shot is fired
	 * @return true if a bullet was fired
	 * @return false if no bullet was fired
	 */
	public boolean shoot(float angle) {
		if(movePolar(angle)) {
			shootTimer.schedule(shootTask, fireDelay); //starts movement cooldown timer
			return true;
			
		}else {return false;}
	}

}
