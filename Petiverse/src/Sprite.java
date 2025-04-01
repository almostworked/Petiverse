import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Fin faniyi
 * @verison 1.0
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
        private String currentState;
        private String petName;
        private List<String> spriteImages;
        private int frameIndex;
        private boolean forward = true;
        private final PropertyChangeSupport support;
        
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
	
         public Sprite(String name, int health, int sleep, int happiness, int hunger, boolean alive, String state) {
                super(name, health, sleep, happiness, hunger, alive, state); // Fix constructor
                
                this.petName = name;
                this.currentState = state;
                this.frameIndex = 0;
                this.spriteImages = new ArrayList<>();
                this.support = new PropertyChangeSupport(this);
                
                updateSprite();
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
         * @return the name of the pet
         */
	public String getName() {
		return this.petName;	
	}
        public void setCurrentState(String state) {
                this.currentState = state;                
                updateSprite(); 
        }
            
        public void addPropertyChangeListener(PropertyChangeListener listener) {
                support.addPropertyChangeListener(listener);;
        }

        public String getCurrentState() {
                return currentState;
        }

        public void updateSprite() {
                spriteImages.clear();
                String basePath = "temp_assets/"; // Assuming images are in the 'temp_assets' folder within resources.
            
                switch (currentState) {
                    case "NORMAL":
                        spriteImages.add(basePath + petName + ".png");
                        spriteImages.add(basePath + petName + "-Sprite1.png");
                        spriteImages.add(basePath + petName + "-Sprite2.png");
                        break;
                    case "HUNGRY":
                        spriteImages.add(basePath + petName + "-Hungry.png");
                        break;
                    case "ANGRY":
                        spriteImages.add(basePath + petName + "-Angry.png");
                        break;
                    case "SLEEPING":
                        System.out.println("SLEEPING case entered in updateSprite");
                        spriteImages.add(basePath + petName + "-Sleep.png");
                        spriteImages.add(basePath + petName + "-Sleep1.png");
                        spriteImages.add(basePath + petName + "-Sleep2.png");
                        spriteImages.add(basePath + petName + "-Sleep3.png");
                        break;
                    case "DEAD":
                        spriteImages.add(basePath + petName + "-Dead.png");
                        break;
                    default:
                        spriteImages.add(basePath + petName + ".png");
                        spriteImages.add(basePath + petName + "-Sprite1.png");
                        spriteImages.add(basePath + petName + "-Sprite2.png");
                        break;
                }
            
                this.resetAnimation();
            }
            
            

        public List<String> getSprites() {
                if (spriteImages == null || spriteImages.isEmpty()) {
                    updateSprite(); 
                }
                return spriteImages;
        }
            
        public String getFrame() {
                if (spriteImages.isEmpty()) {
                    System.out.println("Error: No images in spriteImages.");
                }
            
                if (frameIndex >= spriteImages.size()) {
                    frameIndex = 0; 
                }
            
                return spriteImages.get(frameIndex);
            }
            
        public void nextFrame() {
                if (forward) {
                        frameIndex++;
                        if (frameIndex == spriteImages.size() - 1) {
                                forward = false;
                        }
                } else {
                        frameIndex--;
                        if (frameIndex < 0) {
                                frameIndex = 0;
                                forward = true;
                        }
                }
                       
        }
        public void resetAnimation() {
                frameIndex = 0;
        }
       
}
