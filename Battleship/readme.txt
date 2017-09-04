The main class is BattleShipGame.java

We’ll play this game on a 20x20 ocean. This is 
larger than the ocean in the traditional battleship game. 

The computer places these 13 ships on the ocean in such a way that no ships are immediately adjacent to each other, either horizontally, vertically, or diagonally.
The human player does not know where the ships are. The initial display of the ocean shows a 20 by 20 array of locations, all the same.
The human player tries to hit the ships, by calling out a row and column number. The computer responds with one bit of information 
saying ”hit” or ”miss.” When a ship is hit but not sunk, the program does not provide any information about what kind of a ship was hit. 
However, when a ship is hit and sinks, the program prints out a message ”You just sank a ship-type.” 
After each shot, the computer redisplays the ocean with the new information.

A ship is ”sunk” when every square of the ship has been hit. 
Thus, it takes 8 hits to sink a battleship but only 6 to sink a 
cruiser. The objective is to sink the fleet with as few shots as 
possible.

When all ships have been sunk, the program prints out a message that the game is over, and tells the user how many shots were required.
