# Connect4-With-Minimax-AI
Connect-4 game with AI of different difficulties to play against. Also supports single player.
APCS Final Project

This is my APCS Final Project. It is Connect 4 with an AI. You can also chose to play against another player locally. The AI has 3 difficuly levels, all using the MiniMax algorithm and optimized with alpha-beta pruning. The harder levels make the AI look further into game states, and from those conditions decide where to move next.

The GUI was creating using a 2D Game Java library called Slick 2D. There are 3 stages, the main menu stage, the instruction stage, and finally the playing stage.

I was responsible for implementing the AI using Minimax by traversing through future game states. As you can imagine, this process is computationally expensive so the highest level of game states I could calculate in the future while still allowing the AI to have a playable runtime was 4 levels. I also optimized the AI by using alpha-beta pruning and cutting off branches of game states that would result in the AI losing. In addition to the AI, I also created the game logic, implemented Slick2D, and created the user interface. My team helped improve the project by adding sprites and improving the user interface.
