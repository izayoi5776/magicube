package application;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SampleController {
	@FXML private AnchorPane pane;
	
	@FXML Rectangle c11;
	@FXML Rectangle c12;
	@FXML Rectangle c13;
	@FXML Rectangle c14;
	@FXML Rectangle c15;
	@FXML Rectangle c16;
	@FXML Rectangle c17;
	@FXML Rectangle c18;
	@FXML Rectangle c19;
	@FXML Rectangle c21;
	@FXML Rectangle c22;
	@FXML Rectangle c23;
	@FXML Rectangle c24;
	@FXML Rectangle c25;
	@FXML Rectangle c26;
	@FXML Rectangle c27;
	@FXML Rectangle c28;
	@FXML Rectangle c29;
	@FXML Rectangle c31;
	@FXML Rectangle c32;
	@FXML Rectangle c33;
	@FXML Rectangle c34;
	@FXML Rectangle c35;
	@FXML Rectangle c36;
	@FXML Rectangle c37;
	@FXML Rectangle c38;
	@FXML Rectangle c39;
	@FXML Rectangle c41;
	@FXML Rectangle c42;
	@FXML Rectangle c43;
	@FXML Rectangle c44;
	@FXML Rectangle c45;
	@FXML Rectangle c46;
	@FXML Rectangle c47;
	@FXML Rectangle c48;
	@FXML Rectangle c49;
	@FXML Rectangle c51;
	@FXML Rectangle c52;
	@FXML Rectangle c53;
	@FXML Rectangle c54;
	@FXML Rectangle c55;
	@FXML Rectangle c56;
	@FXML Rectangle c57;
	@FXML Rectangle c58;
	@FXML Rectangle c59;
	@FXML Rectangle c61;
	@FXML Rectangle c62;
	@FXML Rectangle c63;
	@FXML Rectangle c64;
	@FXML Rectangle c65;
	@FXML Rectangle c66;
	@FXML Rectangle c67;
	@FXML Rectangle c68;
	@FXML Rectangle c69;

	Stage stage = null;
	Scene scene = null;
	
	private Cube c = new Cube();
	@FXML
	// @see http://d.hatena.ne.jp/tatsu-no-toshigo/20130502/1367805797
    void initialize() {
        System.out.println("init");
		Rectangle[] recs = {
				// TOP
				c11, c12, c13,
				c14, c15, c16,
				c17, c18, c19,
				// LEFT
				c21, c22, c23,
				c24, c25, c26,
				c27, c28, c29,
				// RIGHT
				c31, c32, c33,
				c34, c35, c36,
				c37, c38, c39,
				// FRONT
				c51, c52, c53,
				c54, c55, c56,
				c57, c58, c59,
				//REAR
				c41, c42, c43,
				c44, c45, c46,
				c47, c48, c49,
				// BOTTOM
				c61, c62, c63,
				c64, c65, c66,
				c67, c68, c69
		};
		c.init(recs);

    }
	public void setStageListener(Stage stage, Scene scene) {
		this.stage = stage;
		this.scene = scene;

        //EventHandler<ActionEvent> p = ( event ) -> { System.out.println( "button push!" );};
        //stage.addEventHandler( ActionEvent.ANY , p );
	}
	private Face[][] mc = new Face[6][9]; 

	@FXML
	public void onMenuNew(ActionEvent event) {
		c.resetColor();
	}
	@FXML
	public void onKeyTyped(KeyEvent event) {
		KeyCode kc = event.getCode();
		if(kc==KeyCode.RIGHT) {
			if(event.isControlDown()) {
				if(event.isShiftDown()) {
					// ctrl + shift for rear
					c.rotate(LAYER.REAR, true);
				}else {
					c.rotate(LAYER.BOTTOM, true);
				}
			}else if(event.isShiftDown()){
				// shift for front
				c.rotate(LAYER.FRONT, false);
			}else {
				c.rotate(LAYER.TOP, true);
			}
		}else if(kc==KeyCode.LEFT) {
			if(event.isControlDown()) {
				if(event.isShiftDown()) {
					// ctrl + shift for rear
					c.rotate(LAYER.REAR, false);
				}else {
					c.rotate(LAYER.BOTTOM, false);
				}
			}else if(event.isShiftDown()){
				// shift for front
				c.rotate(LAYER.FRONT, true);
			}else {
				c.rotate(LAYER.TOP, false);
			}
		}else if(kc==KeyCode.UP) {
			if(event.isControlDown()) {
				c.rotate(LAYER.RIGHT, true);
			}else {
				c.rotate(LAYER.LEFT, false);
			}
		}else if(kc==KeyCode.DOWN) {
			if(event.isControlDown()) {
				c.rotate(LAYER.RIGHT, false);
			}else {
				c.rotate(LAYER.LEFT, true);
			}
		}

		System.out.println("onKeyTyped called KeyCode=" + kc);
	}
	@FXML
	public void onActionAuto(ActionEvent event) {
		System.out.println("onActionAuto event=" + event);
	}
}
