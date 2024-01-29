package chess;
//DONE BY LIAU KAI ZE//

public class Player {
	
	public static Player whiteplayer , blackplayer;
	private boolean playerturn;
	
	Player(boolean checkWhite){
		this.playerturn = checkWhite;
	}
	
	public static void createPlayer() {
		whiteplayer = new Player(true);
		blackplayer = new Player(false);
		
	}

}
