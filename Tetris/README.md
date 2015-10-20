# Tetris Clone

Tetris clone that I coded to try to understand GUI programming in Java. Very basic so far.

Inputs:

Left arrow key: move piece left<br/>
Right arrow key: move piece right<br/>
Up arrow key: rotate piece<br/>
Down arrow key: move piece down

To do:

- Resolve issues with rotation along right bounds. Current solution is hacked together and restricts rotation.</br>
- Fix issues with "sliding" a piece along the lower boundaries. Current implementation causes pieces to "stick" to the ground.<br/>
- Revise rotation code so the pieces rotation along a central axis rather than the top left coordinate of the piece.<br/>
- Add a scoreboard and a next piece box.<br/>
- Implement some method to speed the game up as score increases.<br/>
- Handle game over/new game conditions.