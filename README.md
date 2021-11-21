!"Hadences" is my in game name.

Description :
This project is a game developed in a game called Minecraft. This plugin essentially modifies the game Minecraft so that it functions as if its a different game.
This plugin/modification allows players to utilize this plugin by hosting a server and play with their friends in a player vs player game (Work in Progress hence
no gamemodes are created at the moment). The plugin manages map creation, game creation, player class/role selection, custom skill slots, and manages the game itself. 

How to access files:
  click src | click main | click java/hadences/projectdemonslayer

Usage :
For this plugin to work, a player must know how to host a server and must have their network port forwarded. The game will work locally but not globally if not
port forwarded.

When a player first joins the game with this plugin active, the game is not playable until an arena is created. Arena is created through building in the game. After
creating an arena, the player can then press play and select their own made map in the game. This plugin also has a config file, utilizing its data to save all player
based data and allows any players to customize skill values such as "damage", "cooldown", and "stamina cost" (there are more properties a player can customize).

In Depth:

  Player Abilities:
    Abilities are created through programming and the visual effects of the abilities are created through Math and programming in a 3D environment.
    Used Vectors and rotation matrix to correct the visual effects based on the player's yaw and pitch.
    Since this is currently work in progress, Water is the only thing created at the moment. Water is a breathing type or an element (for clarity) where after selecting
    their elements, players are able to customize their own abilities that is derived from the element they chose. Utilizes Class manipulation to allow players to
    customize their own abilities.
    
  Game Manager:
    Game is managed by OOP. A class Game Manager manages the game such as if the game is in lobby or in game. If the game is in lobby then it checks for all players
    ready/locked in (ready to play) and if a gamemode is selected. Afterwards the game starts and the game manager checks for the specific selected gamemode class for
    its win conditions and loss conditions (This allows me to make multiple gamemodes in the game).
    
  Player Manager:
    The Player Manager stores all player data values in a hashmap when server is on. Player data is stored in a config file and read when the server is on by this class
    file. If a new player joins, then the new player is added to the config.yml file.
  
  Arena Manager:
    Similar to the Player Manager but has all data values related to arenas.
  
  Commands:
    Custom commands are created which allow players in game to use those commands and they'll run live in game.
    
  GUI:
    GUI folder allows custom guis utilizing Minecraft's API to create custom game guis in game for more clarity to player experience.
    
  ___Event:
    files that have events at the end of the file names are listeners that modifies the normal minecraft player experience to a more fitting theme for in this case
    based on a show called "Demon Slayer" so players are experiencing a "samurai" theme.
  
  Utils:
    The Utils folder have Class files created by me and some created by public (Vector Utils for example). Utilizes these files to allow me to easily create new 
    abilities and visual effects such as ray casting and rotating vectors to a desired position.
  
  GameItems:
    The GameItems class file is simply a file with all the game items necessary for the game to function. Such as ability items and katanas. The items are actually not
    katanas; rather a sword but modeled by me using a thrid party program.
