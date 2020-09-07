package VIEW;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class AbstractView {
	protected Stage stage;
	
	
	
	protected Stage getStage() {
		return stage;
	}
	protected void showStage() {
		stage.show();
	}
	protected void closeStage() {
		stage.close();
	}
	protected void setDropShadow(Label label) {

		label.setFont(new Font("Ariel", 20));
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
		label.setEffect(dropShadow);
		label.setCache(true);
	}
	protected GridPane createGridPane() {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10));
		pane.setAlignment(Pos.TOP_CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		return pane;
	}

}
