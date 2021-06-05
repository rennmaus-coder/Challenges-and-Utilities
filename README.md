# Challenges-and-Utilities
Spigot Plugin for Minecraft 1.16

Commands:

	- /timer:
		/timer resume (starts Timer), /timer pause (stopps Timer), /timer time <seconds> (sets Zeit), /timer reset (set Timer to 0)

	- /position:
		/position add <Name> (Adds a position), /position show <Name> (shows the Position), /position del <Name> (deletes the position)    | alias: /pos
		
	- /sum <Number1> <Number2> <Number3> ... (shows the sum of all numbers)

	- /invsee <Player> (shows Player inventory)

	- /backpack (shows private backpack)	| alias: /bp
  
	  - /challenge <ID> 
	    - (01: If a block gets destroyed, it'll destroyed in the whole chunk (Requires a lot auf memory!!!))
	    - (02: If a player gets damaged, the player gets a random effect)
	    - (03: If a block gets destroyed, it'll drop a random item) + true / false (reset)

	- /world:
		/world new <Name> <Type> (generates new World), /world enter <Name> (enters World)
	- /coins:
		- /coins pay <to> <amount> 
		- /coins show (shows amount of coins)
		- /coins set <amount> (sets to coins to the specified amount (OP only))	
	- /pvp [1.8/1.9] (sets the Combat version to 1.8 or 1.9)

Crafting Recipe:
	- Saddle:

	- L L L
	- L S L
	-   T
		
	L = Leather
	S = String
	T = Tripwirehook
		
Furnace Recipe:
	- Rotten Flesh -> Leather
	
