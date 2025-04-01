# **Petiverse \- README**

## **Description**

The Petiverse is an interactive experience designed for players aged 7 and above. It allows users to adopt, care for, and interact with a virtual pet. Players must feed, play with, and take care of their pet to maintain its happiness and health. Neglecting the pet can lead to illness or even losing the pet. The game includes features such as various pet choices, and parental controls to manage playtime.

## **Required Libraries and Third-Party Tools**

To build and run this game, the following libraries and tools are required:

* **Java Development Kit (JDK)** \- Version 17+

* **JavaFX** \- Version 17+ (for graphical interface)

* **Gradle** \- Version 8+ (for dependency management and building)

* **JUnit** \- Version 5+ (for testing)

## **Build Guide (Compiling from Source Code)**

To build the Petiverse from source, follow these steps:

1. **Install Java and JavaFX:**

   * Download and install **JDK 17+** from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or use OpenJDK.

   * Download JavaFX SDK 17+ from [GluonHQ](https://gluonhq.com/products/javafx/).

2. **Clone the Repository:**

   git clone ssh://git@gitlab.sci.uwo.ca:7999/courses/2025/01/COMPSCI2212/group30.git  
   cd virtual-pet-game  
3. **Set Up JavaFX in Gradle:**

Ensure `build.gradle` contains the following JavaFX dependencies:

 dependencies {  
    implementation 'org.openjfx:javafx-controls:17'  
    implementation 'org.openjfx:javafx-fxml:17'  
    testImplementation 'org.junit.jupiter:junit-jupiter:5.8.1'  
}

4\.   **Build the Project:**

./gradlew build

## **Run Guide (Executing Compiled Software)**

After building the game, you can run it with:

./gradlew run

If running manually, ensure you pass JavaFX modules:

java \--module-path /path/to/javafx/lib \--add-modules javafx.controls,javafx.fxml \-jar build/libs/virtual-pet-game.jar

## **User Guide**

1. **Launching the Game:**

   * Run the MainMenu.java file to enter the **Main Menu**.

   * Choose from **Start New Game, Load Game, Instructions, Parental Controls, or Exit**.

2. **Starting a New Game:**

   * Select **New Game** and choose from **1** of **3 pets**.

   * Name your pet to create a **new save file**.

3. **Gameplay Mechanics:**

   * **Track Pet Stats**: Monitor health, sleep, fullness, and happiness.

   * **Commands**: Interact with your pet by **feeding, playing, sleeping, gift-giving, exercising, and taking it to the vet**.

   * **Warnings & Penalties**: If a stat falls below 25%, a warning appears; at 0%, penalties occur (e.g., pet illness or loss).

   * **Inventory System**: Items such as food and toys are stored in an inventory.

   * **Scoring System**: Gain points for positive actions; lose points for neglecting your pet.

4. **Saving and Loading:**

   * The game auto-saves at checkpoints.

   * Users can manually save via the **Save Game** option.

   * If a save file is missing or corrupted, a **new game will be initialized**.

## **Account Details**

If required, use the following credentials for testing:

* **Test Account Username**: `test_user`

* **Test Account Password**: `test1234`

## **Parental Controls**

1. Navigate to **Parental Controls** in the main menu.

2. Enter the **parent password** to access settings.

3. Available options:

   * **Set Play Time Limits**: Define when the child can play.

   * **View Play Statistics**: Check total and average playtime.

   * **Revive Pets**: Restore a lost pet to full stats.

   * **Exit Parental Controls** if no changes are needed.

If parental controls require a separate setup:

**Building/Installing Parental Controls Module:**

* ./gradlew buildParentalControls


**Running Parental Controls Module:**

* java \-jar build/libs/parental-controls.jar

## **Additional Notes for TAs**

* The game UI follows **JavaFX guidelines** for a smooth experience.

* Error handling ensures **input validation and clear error messages**.

* If a feature doesn’t work, default values are applied to prevent crashes.

* If you face **sprite issues**, the game falls back to a default pet image.

## **Contributors**

* Daniella Misyura  
* Adrian Caricari  
* Marcus Cameron  
* Fin Faniyi  
* Kayden Jaffer

Thank you for reviewing Petiverse\! 🐾🎮

