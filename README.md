# **Petiverse - README**

## **Description**

Petiverse is an interactive experience designed for players aged 7 and above. It allows users to adopt, care for, and interact with a virtual pet. Players must feed, play with, and take care of their pet to maintain its happiness and health. Neglecting the pet can lead to illness or even losing the pet. The game includes features such as various pet choices, and parental controls to manage playtime.

## **Required Libraries and Third-Party Tools**

To build and run this game, the following libraries and tools are required:

* **Java Development Kit (JDK)** - Version 17+
* **JavaFX** - Version 17+ (for graphical interface)
* **JUnit** - Version 5+ (for testing)

## **Build Guide (Compiling from Source Code)**

To build the Petiverse from source, follow these steps:

1. **Install Java and JavaFX:**

   * Download and install **JDK 17+** from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or use OpenJDK.
   * Download JavaFX SDK 17+ from [GluonHQ](https://gluonhq.com/products/javafx/).

2. **Clone the Repository:**

   Run the following command to clone the repository:
   ```bash
   git clone ssh://git@gitlab.sci.uwo.ca:7999/courses/2025/01/COMPSCI2212/group30.git
   cd group30
   ```

3. **Navigate to the `src` Folder:**

   Change into the `src` directory where the Java files are located:
   ```bash
   cd src
   ```

4. **Compile the Java Files:**

   To compile all Java files, run the following command (ensure you are in the src directory)
   ```bash
   javac *.java
   ```

   This will compile all `.java` files in the `src` folder and generate the corresponding `.class` files.

## **Run Guide (Executing Compiled Software)**

After compiling, you can run the game with the following command:

```bash
java MainMenu
```

This will launch the **Main Menu** of the game, from which you can start a new game, load a previous save, access instructions, parental controls, or exit.

## **User Guide**

1. **Launching the Game:**

   * Run the application to enter the **Main Menu**.
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

Thank you for reviewing Petiverse! 🐾🎮

---

