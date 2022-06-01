package fiveMens;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controller {
	

	static int player = 0;
	// 1 - czarne 
	// 0 - biale
	
	static int counterWhite = 0;
	static int counterBlack = 0;
	
	Image whiteTile = new Image(getClass().getResourceAsStream("/fxml/white_tile.png"));
	
	Image blackTile = new Image(getClass().getResourceAsStream("/fxml/black_tile.png"));
	
	public void setPlayerTile(MouseEvent event) {
		
		ImageView target = (ImageView) event.getTarget();
		if ( player == 0 && counterWhite < 5) { 
			target.setImage(whiteTile);
			counterWhite++;
			removeTileFromSpare(player,counterWhite);
			player = 1;
		}
		else if ( player == 1 && counterBlack < 5){ 
			target.setImage(blackTile);
			counterBlack++;
			removeTileFromSpare(player,counterBlack);	
			player = 0 ;
		
		}
		
		changeTurnGUI(player);
	}
	

	@FXML
	ImageView turnIndet;
	
	public void changeTurnGUI(int player) {
		
		if(player == 1) 
			turnIndet.setImage(blackTile);
		if(player == 0) 
			turnIndet.setImage(whiteTile);
	}
	
	
	
	
	@FXML
	ImageView nb1;
	@FXML
	ImageView nb2;
	@FXML
	ImageView nb3;
	@FXML
	ImageView nb4;
	@FXML
	ImageView nb5;
	@FXML
	ImageView nw1;
	@FXML
	ImageView nw2;
	@FXML
	ImageView nw3;
	@FXML
	ImageView nw4;
	@FXML
	ImageView nw5;
	
	public void removeTileFromSpare(int player , int counter) {
		
		if ( player == 1 ) {
			switch(counter) {

				case 1:
					nb1.setVisible(false);
					break;
				case 2:
					nb2.setVisible(false);
					break;
				case 3:
					nb3.setVisible(false);
					break;
				case 4:
					nb4.setVisible(false);
					break;
				case 5:
					nb5.setVisible(false);
					break;
				default:
					break;
			}
		}
		
		if ( player ==  0) {

			switch(counter) {

				case 1:
					nw1.setVisible(false);
					break;
				case 2:
					nw2.setVisible(false);
					break;
				case 3:
					nw3.setVisible(false);
					break;
				case 4:
					nw4.setVisible(false);
					break;
				case 5:
					nw5.setVisible(false);
					break;
				default:
					break;
			}
		}
	}
	
}
