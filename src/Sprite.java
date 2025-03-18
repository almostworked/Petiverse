/**
 * 
 * @author faniyi
 * These are the attributes for the class; spriteImage, spriteAnimation, and spriteSound
 * The {@code Sprite} class extends the {@code Pet} class and represents a visual and animated pet with additional attributes.
 * This class includes additional sprite-specific properties like image, animation, and sound effects
 */
public class Sprite extends Pet{
    
        /**
         * These are the attributes for the class; spriteImage, spriteAnimation, and spriteSound
         * The {@code Sprite} class extends the {@code Pet} class and represents a visual and animated pet with additional attributes.
         * This class includes sprite-specific properties such as image, animation, and sound
         */
	private String spriteImage;
	private String spriteAnimation;
	private String spriteSound;
        
        /**
         * 
         * @param name
         * @param health
         * @param sleep
         * @param happiness
         * @param hunger
         * @param alive
         * @param spriteImage
         * @param spriteAnimation
         * @param spriteSound 
         * 
         * 
         * This is a constructor which construct a {@code Sprite} object with theirs above attributes.
         * The constructor uses the super method because the class extends pet class
         */
	
	public Sprite(String name, int health, int sleep, int happiness, int hunger, boolean alive, String spriteImage, String spriteAnimation, String spriteSound) {
		super(name, health, sleep, happiness, hunger, alive);
		
		this.spriteAnimation = spriteAnimation;
		this.spriteImage = spriteImage;
		this.spriteSound = spriteSound;
	}
	
        /**
         * 
         * @param spriteImage set the image of the sprite/pet
         * This class is to set an image for a specific pet
         */
	public void setSpriteImage(String spriteImage) {
		this.spriteImage = spriteImage;
	}
	
        /**
         *                                         
         * @return the current sprite image
         */
	public String displaySpriteImage() {
		return spriteImage;                                 
	}
        
        /**
         * 
         * @param spriteAnimation set the animation of the sprite/pet
         */
	
	public void setSpriteAnimation(String spriteAnimation) {
		this.spriteAnimation = spriteAnimation;
	}
        
        /**
         * 
         * @return the current sprite animation
         */
	
	public String displaySpriteAnimation() {
		return spriteAnimation;
	}
	
        /**
         * 
         * @param spriteSound set the sprite sound effect 
         */
	public void setSpritesound(String spriteSound){
		this.spriteSound = spriteSound;
	}
	
        /**
         * 
         * @return the current sprite sound
         */
	public String displaySpritesound(){
		return spriteSound;	
	}
	
        /**
         * 
         * @return the name of the pet
         */
	public String getName() {
		return this.getName();	
	}
}
