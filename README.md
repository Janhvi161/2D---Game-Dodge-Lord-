🦸‍♂️ Dodge Lord
Dodge Lord is a simple 2D Java game where you control a superhero who dodges falling boxes and flying buildings. The longer you survive, the higher your score!

🎮 Game Overview
Dodge Lord is all about reflexes!
Your mission is to avoid:

🏢 Buildings that scroll horizontally.

📦 Boxes that fall from the sky.

Each time you pass a building without crashing — your score goes up!

💻 How to Play
Press SPACEBAR to jump.

Avoid boxes and buildings.

If you crash, the game ends.

Press SPACEBAR again to restart after a game over.

🧑‍💻 How to Run
Clone this repository:

bash
Copy
Edit
git clone https://github.com/yourusername/dodge-lord.git
cd dodge-lord
Compile the Java file:

nginx
Copy
Edit
javac Dodge_Lord.java
Run the game:

nginx
Copy
Edit
java Dodge_Lord
📂 Resources
Make sure your images and sounds are placed in a resources folder like this:

arduino
Copy
Edit
resources/
├── backg.png
├── smanop.png
├── box.png
├── building.png
├── explosion.png
├── point-smooth-beep-230573.wav
├── explosion-312361.wav
The game uses these assets for backgrounds, obstacles, the player sprite, sounds, and explosion effects.

🏆 Scoring System
You gain 1 point for each building you pass safely.

The score is displayed in the top left corner during gameplay.

The game restarts when you press SPACEBAR after a game over.

💡 Features
Basic jump and gravity physics.

Random obstacle generation.

Explosion animations and sound effects on collision.

Smooth game loop using javax.swing.Timer.

🚀 Future Improvements
Difficulty increases as your score rises.

Power-ups or shields.

Start screen and game-over menu.

High score tracking.

📜 License
This project is for educational and personal use.
You can modify or distribute it freely, but please credit the original author.
