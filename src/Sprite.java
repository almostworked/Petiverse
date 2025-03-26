import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

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
        private String currentState;
        private String petName;
        private List<String> spriteImages;
        private int frameIndex;
        
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
	
	public Sprite(String name, int health, int sleep, int happiness, int hunger, boolean alive, List<String> spriteImages, String spriteSound) {
		super(name, health, sleep, happiness, hunger, alive, hunger, spriteSound); // fix
		
		this.spriteSound = spriteSound;
                this.petName = name;
                this.currentState = "NORMAL";
                this.frameIndex = 0;
                this.spriteImages = spriteImages;
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
        public void setCurrentState(String state) {
                this.currentState = state;
                updateSprite();
        }

        public String getCurrentState() {
                return currentState;
        }

        private void updateSprite() {
                switch (currentState) {
                        case "NORMAL":
                                spriteImages = new ArrayList<>();
                                spriteImages.add(petName + ".png");
                                spriteImages.add(petName + "-Sprite1" + ".png");
                                spriteImages.add(petName + "-Sprite2" + ".png");
                                break;
                        case "HUNGRY":
                                spriteImages = new ArrayList<>();
                                spriteImages.add(petName + ".png");
                                spriteImages.add(petName + "-Sprite1" + ".png");
                                spriteImages.add(petName + "-Sprite2" + ".png");
                                break;
                        case "ANGRY":
                                spriteImages = new ArrayList<>();
                                spriteImages.add(petName + "-Angry1" + ".png");
                                spriteImages.add(petName + "-Angry2" + ".png");
                                spriteImages.add(petName + "-Angry3" + ".png");
                                break;
                        case "SLEEPING":
                                spriteImages = new ArrayList<>();
                                spriteImages.add(petName + "-Sleeping1" + ".png");
                                spriteImages.add(petName + "-Sleeping2" + ".png");
                                spriteImages.add(petName + "-Sleeping3" + ".png");
                                break;
                        default:
                                spriteImages = new ArrayList<>();
                                spriteImages.add(petName + ".png");
                                spriteImages.add(petName + "-Sprite1" + ".png");
                                spriteImages.add(petName + "-Sprite2" + ".png");
                                break;
                }
        }

        public List<String> getSprites() {
                return spriteImages;
        }
        public String getFrame() {
                return spriteImages.get(frameIndex);
        }
        public void nextFrame() {
                frameIndex = (frameIndex + 1) % spriteImages.size();
        }
}
