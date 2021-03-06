/**
 * @author MeganA
 * This class will be the parent class to all the entities in the game (enemies and player).
 * Objects of this class can move, fire projectiles, and take damage. This class will create the framework 
 * for the game's Enemy ships and the player's playerShip. It will hold an array list of Projectiles 
 * that can access projectile objects.
 */

import acm.graphics.GImage;
import acm.graphics.GPoint;

public class OurEntity {
	//************************************* Variables *************************************//	
	protected int fireDelay;
	protected int curFireTime = 0;
	protected EntityType eType;
	protected boolean canMove = true;
	protected boolean canShoot = true; 							//prevents enemies that cannot fire from firing
	protected int health;
	protected double speed;
	protected boolean friendly;									// IsFriendly should be here instead of logic
	protected GImage image;
	protected Projectile newBullet; 							//used for creating/firing projectiles
	
	public EntityType type = null;								// Entity Type needs to be defined when object is made
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	//************************************* Constructor *************************************//
	
	public OurEntity(int fD, int life, EntityType eT) {
		fireDelay = fD;
		health = life;
		eType = eT;
		switch(eT) {
			case PLAYER:
				image = new GImage("media/oliveship.png");
				speed = 4.5;
				friendly = true;
				setImage(ShipCustomPane.shipColor);
				break;
			case SCOOTER:
				speed = 4.5;
				friendly = false;
				break;
			case SHOOTER:
				speed = 1;
				friendly = false;
				break;
		}
	}
		
	//************************************* Setter & Getters *************************************//
	
	void setHealth(int hp) {
		this.health = hp;
	}
	
	void setSpeed(double spd) {
		this.speed = spd;
	}
	
	void setIsFriendly(boolean isFriendly) {
		this.friendly = isFriendly; 
	}
	
	void setImage(String color) {						// help with graphics
		String selected;
		if (color == null) {
			selected = "green";
		}
		else {
			selected = color;
		}
		image.setImage(IMG_FILENAME_PATH + "Big" + selected + "Ship" + IMG_EXTENSION);		// PLEASE MAKE IMAGE NAMES SAME AS EntityTypes
	}
	
	int getHealth() {
		return this.health;
	}
	
	double getSpeed() {
		return this.speed;
	}
	
	boolean getFriendly() {
		return this.friendly;
	}
	
	GImage getImage() {
		return this.image;
	}
	
	Projectile getNewBullet() {
		return this.newBullet;
	}
	
	public int getFireDelay() {
		return fireDelay;
	}

	public void setFireDelay(int fireDelay) {
		this.fireDelay = fireDelay;
	}
	
	//************************************* Functions *************************************//
	
	public boolean canShoot() {
		return (curFireTime >= fireDelay && canShoot);
	}
	
	/**Checks if the ship's health is less than 1
	 * 
	 * @return true if the ship's health is less than 1
	 */
	public boolean isDead() {
		return health < 1;
	}
	
	/**Creates a copy of the ship that moves out ahead of the ship image to find the ship's 
	 * new position after movePolar. Using this new position, the method checks if the new position
	 * for the ship would be within the bounds of the level. If it is outside, the ship's position is
	 * adjusted. The location of the ship after moving is returned.
	 * 
	 * @param angle the direction you're going to move your ship
	 * @return the next location of the ship
	 */
	protected GPoint moveWithinBounds(float angle) {
		GImage nextPosition = image;				// ******* TO ADD WHEN IMAGE REPLACES RECT
		nextPosition.movePolar(speed, angle);
	
		//if the ship is too far to the right
		if (nextPosition.getX() > Level.LEVEL_BOUNDS_RIGHT - nextPosition.getWidth()) {
			nextPosition.setLocation(Level.LEVEL_BOUNDS_RIGHT - nextPosition.getWidth(), nextPosition.getY());
		}
		//if the ship is too far to the left
		else if (nextPosition.getX() < Level.LEVEL_BOUNDS_LEFT) {
			nextPosition.setLocation(Level.LEVEL_BOUNDS_LEFT, nextPosition.getY());
		}
		//if the ship is below the board
		if(nextPosition.getY() > Level.LEVEL_BOUNDS_BOTTOM - nextPosition.getHeight()){
			nextPosition.setLocation(nextPosition.getX(), Level.LEVEL_BOUNDS_BOTTOM - nextPosition.getHeight());
		}
		//if the ship is above the board
		else if(nextPosition.getY() < Level.LEVEL_BOUNDS_TOP){
			nextPosition.setLocation(nextPosition.getX(), Level.LEVEL_BOUNDS_TOP);
		}
		return new GPoint(nextPosition.getX(), nextPosition.getY());
	}
	
	/**Moves the ship
	 * 
	 */
	protected void movePolar(float angle) {
			image.setLocation(moveWithinBounds(angle));
	}
	
	/**Fires a projectile in the direction of parameter angle. Also adds this new Projectile to the 
	 * ArrayList bullets.
	 * 
	 * @param angle the angle at which the player wants to fire the projectile
	 * @param bullet the bullet object being fired
	 * @return the bullet after it is fired
	 */
	public Projectile shootProjectile(Projectile bullet, float angle) {		
		bullet = new Projectile(new GPoint(image.getX() + (image.getWidth() / 2), 
				image.getY() + (image.getHeight() / 2)), angle, friendly);
		curFireTime = 0;
		return bullet;
	}
}




