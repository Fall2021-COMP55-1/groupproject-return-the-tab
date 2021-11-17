import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.Timer;

import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.graphics.GRect;

public class playerShip extends ourEntity implements ActionListener{
	/**Our default constructor for playerShip
	 * 
	 * @param entityLocation the starting location of the playerShip
	 */
	playerShip(GPoint entityLocation) {
		fireDelay = 100;
		health = 300;
		speed = 3;
		friendly = true;
		rect = new GRect(entityLocation.getX(), entityLocation.getY(), 30, 30);
		rect.setFilled(true);
		type = EntityType.PLAYER;
	}

	/**This is the move function that playerShip will be using. It mostly just runs through movePolar 
	 * from ourEntity, but this is needed to schedule the timer for the playerShip's movement cooldown
	 * 
	 * @param angle the angle at which you plan on moving the ship
	 * @return
	 */
	public boolean move(float angle) {
		if(movePolar(angle)) {
			moveTimer.schedule(moveTask, DELAY_MS); //starts movement cooldown timer
			return true;
			
		}else {return false;}
	}
	
	public boolean shoot(float angle) {
		if(movePolar(angle)) {
			shootTimer.schedule(shootTask, fireDelay); //starts movement cooldown timer
			return true;
			
		}else {return false;}
	}
	
}
