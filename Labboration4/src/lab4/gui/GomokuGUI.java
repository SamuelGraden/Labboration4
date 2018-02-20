package lab4.gui;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lab4.client.GomokuClient;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;

/*
 * The GUI class
 */

public class GomokuGUI implements Observer{

	private GomokuClient client;
	private GomokuGameState gamestate;
	
	JButton connectButton = new JButton("Connect");
	JButton newGameButton = new JButton("New Game");
	JButton disconnectButton = new JButton("Disconnect");
	
	JLabel messageLabel = new JLabel("");
	
	/**
	 * The constructor
	 * 
	 * @param g   The game state that the GUI will visualize
	 * @param c   The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c){
		this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);
		
		JFrame frame = new JFrame("Gomoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
		
		Dimension d = new Dimension(600,600);
		frame.setPreferredSize(d);
		frame.setLocation(0,0);
		frame.setSize(d); 	
		
		
		disconnectButton.setBounds(225, 330, 100, 30);
		connectButton.setBounds(115, 330, 100, 30);
		newGameButton.setBounds(5, 330, 100, 30);	
		
		frame.add(disconnectButton);
		frame.add(connectButton);
		frame.add(newGameButton);
		frame.add(new GamePanel(g.getGameGrid()));
		
		
	}
	
	
	
	
	public void update(Observable arg0, Object arg1) {
		
		
		// Update the buttons if the connection status has changed
		if(arg0 == client){
			if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			}else{
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}
		
		// Update the status text if the gamestate has changed
		if(arg0 == gamestate){
			messageLabel.setText(gamestate.getMessageString());
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
}