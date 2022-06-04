package fiveMens;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.nio.channels.SelectableChannel;

import fiveMens.utils.Board;
import fiveMens.utils.BoardField;
import fiveMens.utils.Pawn;

public class Controller {

	static Board board;

	static Pawn biale = new Pawn(1);
	static Pawn czarne = new Pawn(0);

	static int counterWhite = 0;
	static int counterBlack = 0;

	Image whiteTile = new Image(getClass().getResourceAsStream("/fxml/white_tile.png"));

	Image blackTile = new Image(getClass().getResourceAsStream("/fxml/black_tile.png"));

	Image greenTile = new Image(getClass().getResourceAsStream("/fxml/green_tile.png"));

	Image redTile = new Image(getClass().getResourceAsStream("/fxml/red_tile.png"));

	void setWhite(ImageView target) {
		target.setImage(whiteTile);
	}

	void setBlack(ImageView target) {
		target.setImage(blackTile);
	}

	void setGreen(ImageView target) {
		target.setImage(greenTile);
	}

	void setRed(ImageView target) {
		target.setImage(redTile);
	}

	@FXML
	ImageView NODE0,NODE1, NODE2, NODE3, NODE4, NODE5, NODE6, NODE7, NODE8, NODE9, NODE10, NODE11, NODE12, NODE13, NODE14, NODE15;

	@FXML
	public void initialize() {

		board = new Board();

		board.getField(0).setImage(NODE0);
		board.getField(1).setImage(NODE1);
		board.getField(2).setImage(NODE2);
		board.getField(3).setImage(NODE3);
		board.getField(4).setImage(NODE4);
		board.getField(5).setImage(NODE5);
		board.getField(6).setImage(NODE6);
		board.getField(7).setImage(NODE7);
		board.getField(8).setImage(NODE8);
		board.getField(9).setImage(NODE9);
		board.getField(10).setImage(NODE10);
		board.getField(11).setImage(NODE11);
		board.getField(12).setImage(NODE12);
		board.getField(13).setImage(NODE13);
		board.getField(14).setImage(NODE14);
		board.getField(15).setImage(NODE15);

	}

	static int player = 0;
	static boolean ENDPLACING = true;
	static boolean SELCETED = false;
	static BoardField prevField;

	public void setPlayerTile(MouseEvent event) {

		ImageView target = (ImageView) event.getTarget();

		// find Node
		int x = -1;
		

		if (ENDPLACING == true) {

	
			for (int i = 0; i <= 15; i++) {
				if (target.getId() == board.getField(i).getImage().getId())
					x = i;
			}
			if (player == 0) {
				board.getField(x).setPawn(biale);
				setWhite(board.getField(x).getImage());
				counterWhite++;
				removeTileFromSpare(player, counterWhite);
				player = changeTurnGUI(player);

			} else {
				board.getField(x).setPawn(czarne);
				setBlack(board.getField(x).getImage());
				counterBlack++;
				removeTileFromSpare(player, counterBlack);
				player = changeTurnGUI(player);
			}

			if (board.getField(x).pawnsInRow() == true) {
				// usuwanie :)

			}

			if (counterBlack == 5 && counterWhite == 5) {
				player = changeTurnGUI(player);
				ENDPLACING = false;
				makeRed();
				return;
			}

		}
		if (ENDPLACING == false) {
			if (SELCETED == false) {
				for (int i = 0; i <= 15; i++) {
					if (target.getId() == board.getField(i).getImage().getId()  && board.getField(i).getPawn().getPawn() == czarne){
						x = i;
						break;
					}	
				}
				if( x == -1){
					System.out.println("Blad");
					return;
				}
			
				prevField = board.getField(x);
				if ((counterBlack == 3 && prevField.getPawn() == czarne)
						|| (counterWhite == 3 && prevField.getPawn() == biale)) {
					for (int i = 0; i <= 15; i++) {
						if (board.getField(i).getPawn() == null) {
							setGreen(board.getField(i).getImage());
						}
					}

				} else {
					checkAvaliableMovesWhenMoreThen3(x);
				}
				SELCETED = true;
			}

			else if (SELCETED  == true ) {

				for (int i = 0; i <= 15; i++) {
					if (target.getId() == board.getField(i).getImage().getId()  && board.getField(i).getPawn() == null ){
						x = i;
						break;
					}	
				}
				if( x == -1){
					System.out.println("Blad");
					return;
				}
				//TO-DO unselect

				if (counterBlack == 3 || counterWhite == 3) {
					board.movePawnToAnyField(prevField.getPawn(), board.getField(x));
					if ( player == 0) setWhite(board.getField(x).getImage());
					if ( player == 1) setBlack(board.getField(x).getImage());
					setRed(prevField.getImage());
				
				} else {
					System.out.println(board.getField(x).getImage().getId());
					System.out.println(prevField.getImage().getId());
					board.movePawnToAdjacentField(prevField.getPawn(), board.getField(x)); 
					if ( player == 0) setWhite(board.getField(x).getImage());
					if ( player == 1) setBlack(board.getField(x).getImage());
					setRed(prevField.getImage());
					
				}

				makeRed();
				SELCETED = false;
				player = changeTurnGUI(player);
			}

			if (board.getField(x).pawnsInRow() == true) {
				// usuwanie :)

			}

		}

	}

	private void makeRed(){
		for (int i = 0; i <= 15; i++) {
			if (board.getField(i).getImage().getImage().getPixelReader().getArgb(25, 25) == greenTile
					.getPixelReader().getArgb(25, 25)) {
				setRed(board.getField(i).getImage());
			}
		}
	}

	private void checkAvaliableMovesWhenMoreThen3(int x) {

		if (board.getField(x).getDown() != null) {
			if (board.getField(x).getDown().getPawn() == null) {
				setGreen(board.getField(x).getDown().getImage());
			}
		}
		if (board.getField(x).getUp() != null) {
			if (board.getField(x).getUp().getPawn() == null) {
				setGreen(board.getField(x).getUp().getImage());
			}
		}
		if (board.getField(x).getLeft() != null) {
			if (board.getField(x).getLeft().getPawn() == null) {
				setGreen(board.getField(x).getLeft().getImage());
			}
		}
		if (board.getField(x).getRight() != null) {
			if (board.getField(x).getRight().getPawn() == null) {
				setGreen(board.getField(x).getRight().getImage());
			}
		}
	}

	@FXML
	ImageView turnIndet;

	public int changeTurnGUI(int player) {

		if (player == 1) {
			turnIndet.setImage(whiteTile);
			return 0;
		}
		if (player == 0) {
			turnIndet.setImage(blackTile);
			return 1;
		}
		return -1;
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

	public void removeTileFromSpare(int player, int counter) {

		if (player == 1) {
			switch (counter) {

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

		if (player == 0) {

			switch (counter) {

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
