package lab4;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;
import lab4.gui.GamePanel;
import lab4.gui.GomokuGUI;

public class GomokuMain {

	public static void main(String[] args) {
		System.out.println("Test");
		GomokuClient GC = new GomokuClient(12345);
		GomokuGameState GGS = new GomokuGameState(GC);
		
		GamePanel GP = new GamePanel(GGS.getGameGrid());

		//GGS.getGameGrid().move(1, 1, 2);
		//GGS.getGameGrid().move(10, 10, 1);
		
		GomokuGUI GG = new GomokuGUI(GGS, GC);
	}

}
