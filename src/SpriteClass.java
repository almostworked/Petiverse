
public class SpriteClass extends PetClass{

	private String spriteImage;
	private String spriteAnimation;
	private String spriteSound;
	
	public SpriteClass(String name, int health, int sleep, int happiness, int hunger, boolean alive, String spriteImage, String spriteAnimation, String spriteSound) {
		super(name, health, sleep, happiness, hunger, alive);
		
		this.spriteAnimation = spriteAnimation;
		this.spriteImage = spriteImage;
		this.spriteSound = spriteSound;
	}
	
	public void setSpriteImage(String spriteImage) {
		this.spriteImage = spriteImage;
	}
	
	public String displaySpriteImage() {
		return spriteImage;
	}
	
	public void setSpriteAnimation(String spriteAnimation) {
		this.spriteAnimation = spriteAnimation;
	}
	
	public String displaySpriteAnimation() {
		return spriteAnimation;
	}
	
	public void displaySpritesound(String spriteSound){
		this.spriteSound = spriteSound;
	}
	
	public String displaySpritesound(){
		return spriteSound;	
	}
	
	public String getName() {
		return this.getName();	
	}
}
