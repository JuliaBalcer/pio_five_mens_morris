
package fiveMens;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
	
	
	
	public ImageView imageView;
	
	Image white = new Image(getClass().getResourceAsStream("/fxml/white_tile.png"));
	
	Image black = new Image(getClass().getResourceAsStream("/fxml/black_tile.png"));
	
	
	public void changeColor() {
		
		imageView.setImage(white);
			
		System.out.println("działa");
		
		 
		
	}
	
}
