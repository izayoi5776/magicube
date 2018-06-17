package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** １個小色塊、９個塊が面になるが、このクラスは面ではない */
public class Face implements Cloneable{
	private Color c;
	private Rectangle rec;
	private DIRECTION dir;

	// Magic cube 1 box
	public Face() {}
	public Face(Rectangle rec, DIRECTION dir){
		this.rec = rec;
		this.dir = dir;
	}

	// getter
	public Color getColor(){ return c;}
	public DIRECTION getDir(){ return dir;}
	
	// filter
	public boolean isDirection(DIRECTION dir) {return this.dir.equals(dir);}
	
	// setter
	public void setColor(Color c){ this.c = c; rec.setFill(c);}
	public void setDirection(DIRECTION dir){	this.dir = dir;	}
	public void setRectangle(Rectangle rec){	this.rec = rec;	}
}
