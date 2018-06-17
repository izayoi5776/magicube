package application;

import java.util.Arrays;

import javafx.scene.paint.Color;
import javafx.util.Pair;

/** ２つTticker含むブロック。各辺の中心、各面４つ、計１２個　 */
public class Block2 extends Block implements Cloneable{
	/** ２つFaceは順番ある、T, L, Right, F, Rear, Bの順で０番を取る */
	//private Face[] ss;
	
	public Block2() {
		ss = new Face[2];
		ss[0] = new Face();
		ss[1] = new Face();
	}

	public Face[] getFace() {return ss;}
	public void init(Face s1, Face s2) {
		ss = new Face[2];
		ss[0] = s1;
		ss[1] = s2;
	}
	public Color getColorNotDirection(DIRECTION dir) {
		return Arrays.stream(ss).filter(o -> !o.isDirection(dir)).findFirst().get().getColor();
	}
	
	// 2つの色を取得、先頭は指定方向
	public Pair<Color, Color> getColorPair(DIRECTION firstDir){
		Pair<Color, Color> ret;
		Color c1 = ss[0].getColor();
		Color c2 = ss[1].getColor();
		if(ss[0].isDirection(firstDir)) {
			ret = new Pair<>(c1, c2);
		}else {
			ret = new Pair<>(c2, c1);
		}
		return ret;
	}
	// 2つの色をセット、先頭は指定方向
	public void setColorPair(DIRECTION firstDir, Pair<Color, Color> cp) {
		if(ss[0].isDirection(firstDir)) {
			ss[0].setColor(cp.getKey());
			ss[1].setColor(cp.getValue());
		}else {
			ss[1].setColor(cp.getKey());
			ss[0].setColor(cp.getValue());
		}
	}
	/** 回転する。実際color移動していて、blockは変えない
	 * @param clockwise=trueなら時計まわり、
	 * @param dirは回転層の方向
	 * @param b2は回転の元。そこから色をもらう。
	 * 
	 **/
	public Block2 rotate(DIRECTION dir, Block2 b2) {
		Block2 ret = null;
		try {
			ret = (Block2) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Face b20 = Arrays.stream(b2.getFace()).filter(o -> o.isDirection(dir)).findFirst().get();	// 方向がdirの面
		Face b21 = Arrays.stream(b2.getFace()).filter(o -> !o.isDirection(dir)).findFirst().get();
		Face c20 = Arrays.stream(ss).filter(o -> o.isDirection(dir)).findFirst().get();				// 方向がdirの面
		Face c21 = Arrays.stream(ss).filter(o -> !o.isDirection(dir)).findFirst().get();
		c20.setColor(b20.getColor());
		c21.setColor(b21.getColor());

		return ret;
	}
}
