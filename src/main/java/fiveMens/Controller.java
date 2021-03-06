package fiveMens;

import javafx.fxml.FXML;
import javafx.scene.control.IndexRange;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import fiveMens.utils.Board;
import fiveMens.utils.BoardField;
import fiveMens.utils.Pawn;

public class Controller {

	static Board board;

	static int counterWhite = 0;
	static int deleteCounterWhite = 0;
	static int counterBlack = 0;
	static int deleteCounterBlack = 0;
	private int player = 1;
	private boolean isEveryPawnPlaced = false;
	private boolean isSelected = false;
	private BoardField prevField;

	private final int DEFAULTEVALUE = -1;
	private final int MAXNUMOFNODE = 15;
	private final int WHITEPLAYER = 1;
	private final int BLACKPLAYER = 0;
	private final int MINNUMBEROFPAWNS = 3;
	private final int MAXNUMBEROFPAWNS = 5;
	private boolean canDelete = false;

	Image whiteTile = new Image(getClass().getResourceAsStream("/fxml/white_tile.png"));

	void setWhite(ImageView target) {
		target.setImage(whiteTile);
	}

	Image blackTile = new Image(getClass().getResourceAsStream("/fxml/black_tile.png"));

	void setBlack(ImageView target) {
		target.setImage(blackTile);
	}

	Image greenTile = new Image(getClass().getResourceAsStream("/fxml/green_tile.png"));

	void setGreen(ImageView target) {
		target.setImage(greenTile);
	}

	Image redTile = new Image(getClass().getResourceAsStream("/fxml/red_tile.png"));

	void setRed(ImageView target) {
		target.setImage(redTile);
	}

	Image selectedBlackTile = new Image(getClass().getResourceAsStream("/fxml/black_tile_selected.png"));
	Image selectedWhiteTile = new Image(getClass().getResourceAsStream("/fxml/white_tile_selected.png"));

	@FXML
	ImageView NODE0, NODE1, NODE2, NODE3, NODE4, NODE5, NODE6, NODE7, NODE8, NODE9, NODE10, NODE11, NODE12, NODE13,
			NODE14, NODE15;

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

	public void setPlayerTile(MouseEvent event) {
		ImageView target = (ImageView) event.getTarget();
		int x = DEFAULTEVALUE;

		if (canDelete == true) {
			boolean flag = false;

			do {
				int enemy = player == WHITEPLAYER ? WHITEPLAYER : BLACKPLAYER;
				int id = returnNodeIDByPlayer(enemy, target);

				if (id == DEFAULTEVALUE) {
					return;
				} else {
					board.removePawnFrom(id);
					if (isEveryPawnPlaced == false)
						setGreen(board.getField(id).getNode());
					else
						setRed(board.getField(id).getNode());
					flag = true;
					canDelete = false;
					if (enemy == BLACKPLAYER)
						deleteCounterBlack++;
					if (enemy == WHITEPLAYER)
						deleteCounterWhite++;
					if (deleteCounterBlack == 3) {
						try {
							Main.setRoot("/fxml/end_white");
						} catch (Exception e) {

						}
					}
					if (deleteCounterWhite == 3) {
						try {
							Main.setRoot("/fxml/end_black");
						} catch (Exception e) {

						}
					}
				}
			} while (!flag);
			int playerIndent = player == WHITEPLAYER ? BLACKPLAYER : WHITEPLAYER;
			changeTurnGUI(playerIndent);
			return;
		}

		if (isEveryPawnPlaced == false) {
			for (int i = 0; i <= MAXNUMOFNODE; i++) {
				if (target.getId() == board.getField(i).getNode().getId()
						&& board.getField(i).getNode().getImage().getPixelReader().getArgb(25, 25) == greenTile
								.getPixelReader().getArgb(25, 25))
					x = i;
			}

			if (x == DEFAULTEVALUE) {
				return;
			}

			if (player == WHITEPLAYER) {
				Pawn biale = new Pawn(WHITEPLAYER);
				board.getField(x).setPawn(biale);
				setWhite(board.getField(x).getNode());
				counterWhite++;
				removeTileFromSpare(WHITEPLAYER, counterWhite);

				player = changePlayer(player);
			} else {
				Pawn czarne = new Pawn(BLACKPLAYER);
				board.getField(x).setPawn(czarne);
				setBlack(board.getField(x).getNode());
				counterBlack++;
				removeTileFromSpare(BLACKPLAYER, counterBlack);

				player = changePlayer(player);
			}

			if (board.getField(x).pawnsInRow() == true) {
				canDelete = true;
			} else {
				int enemy = player == WHITEPLAYER ? BLACKPLAYER : WHITEPLAYER;
				changeTurnGUI(enemy);
			}

			if (counterBlack == MAXNUMBEROFPAWNS && counterWhite == MAXNUMBEROFPAWNS) {
				isEveryPawnPlaced = true;
				makeRed();
				return;
			}
		}
		if (isEveryPawnPlaced == true) {
			if (isSelected == false) {

				x = returnNodeIDByPlayer(player, target);
				if (x == DEFAULTEVALUE) {
					return;
				}

				prevField = board.getField(x);
				if ((deleteCounterBlack == 2 && prevField.getPawn().getPlayer() == BLACKPLAYER)
						|| (deleteCounterWhite == 2 && prevField.getPawn().getPlayer() == WHITEPLAYER)) {
					for (int i = 0; i <= MAXNUMOFNODE; i++) {
						if (board.getField(i).getPawn() == null) {
							setGreen(board.getField(i).getNode());
						}
					}

				} else {
					checkAvaliableMovesWhenMoreThen3(x);
				}
				isSelected = true;

				if (player == BLACKPLAYER)
					prevField.getNode().setImage(selectedBlackTile);
				if (player == WHITEPLAYER)
					prevField.getNode().setImage(selectedWhiteTile);
			}

			else if (isSelected == true) {
				for (int i = 0; i <= MAXNUMOFNODE; i++) {
					if (target.getId() == board.getField(i).getNode().getId() && board.getField(i).getPawn() == null
							&& board.getField(i).getNode().getImage().getPixelReader().getArgb(25, 25) == greenTile
									.getPixelReader().getArgb(25, 25)) {
						x = i;
						break;
					}
				}

				if (target.getId() == prevField.getNode().getId()) {
					isSelected = false;
					makeRed();

					if (player == BLACKPLAYER)
						prevField.getNode().setImage(blackTile);
					if (player == WHITEPLAYER)
						prevField.getNode().setImage(whiteTile);

					return;
				}

				if (x == DEFAULTEVALUE) {
					return;
				}

				if (deleteCounterBlack == 2 || deleteCounterWhite == 2) {
					board.movePawnToAnyField(prevField.getPawn(), board.getField(x));
					setNodeColor(x);
					setRed(prevField.getNode());

				} else {
					board.movePawnToAdjacentField(prevField.getPawn(), board.getField(x));
					setNodeColor(x);
					setRed(prevField.getNode());
				}

				makeRed();

				if (board.getField(x).pawnsInRow() == true) {
					canDelete = true;
				} else {
					changeTurnGUI(player);
				}

				isSelected = false;
				player = changePlayer(player);
			}
		}
	}

	private int returnNodeIDByPlayer(int player, ImageView target) {
		for (int i = 0; i <= MAXNUMOFNODE; i++) {
			try {
				if (target.getId() == board.getField(i).getNode().getId()
						&& board.getField(i).getPawn().getPlayer() == player) {
					return i;
				}
			} catch (Exception e) {

			}
		}
		return DEFAULTEVALUE;
	}

	private void setNodeColor(int ID) {
		if (player == WHITEPLAYER)
			setWhite(board.getField(ID).getNode());
		if (player == BLACKPLAYER)
			setBlack(board.getField(ID).getNode());
	}

	private void makeRed() {
		for (int i = 0; i <= MAXNUMOFNODE; i++) {
			if (board.getField(i).getNode().getImage().getPixelReader().getArgb(25, 25) == greenTile
					.getPixelReader().getArgb(25, 25)) {
				setRed(board.getField(i).getNode());
			}
		}
	}

	private void checkAvaliableMovesWhenMoreThen3(int x) {

		if (board.getField(x).getDown() != null) {
			if (board.getField(x).getDown().getPawn() == null) {
				setGreen(board.getField(x).getDown().getNode());
			}
		}
		if (board.getField(x).getUp() != null) {
			if (board.getField(x).getUp().getPawn() == null) {
				setGreen(board.getField(x).getUp().getNode());
			}
		}
		if (board.getField(x).getLeft() != null) {
			if (board.getField(x).getLeft().getPawn() == null) {
				setGreen(board.getField(x).getLeft().getNode());
			}
		}
		if (board.getField(x).getRight() != null) {
			if (board.getField(x).getRight().getPawn() == null) {
				setGreen(board.getField(x).getRight().getNode());
			}
		}
	}

	@FXML
	ImageView turnIndet;

	private int changePlayer(int player) {
		if (player == BLACKPLAYER) {
			return WHITEPLAYER;
		}
		if (player == WHITEPLAYER) {
			return BLACKPLAYER;
		}
		return DEFAULTEVALUE;
	}

	private void changeTurnGUI(int player) {
		if (player == BLACKPLAYER) {
			turnIndet.setImage(whiteTile);
		}
		if (player == WHITEPLAYER) {
			turnIndet.setImage(blackTile);
		}
	}

	@FXML
	ImageView nb1, nb2, nb3, nb4, nb5, nw1, nw2, nw3, nw4, nw5;

	private void removeTileFromSpare(int player, int counter) {
		if (player == BLACKPLAYER) {
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
		if (player == WHITEPLAYER) {
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
