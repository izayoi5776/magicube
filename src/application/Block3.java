package application;

import java.util.Arrays;

import javafx.scene.paint.Color;

/** ３つFace含むブロック。各角、計８個  */
public class Block3 extends Block{

	private Face[] ss;
	
	public Block3(){}
	public Face[] getFace() {return ss;}
	public void init(Face s1, Face s2, Face s3) {
		ss = new Face[3];
		ss[0] = s1;
		ss[1] = s2;
		ss[2] = s3;
	}
	
	/** Color 3つかえす、順は0=X軸、1=Y軸、2=Z軸。軸の正負は無論 */
	public Color[] getColorXYZ() {
		Color[] ret = new Color[3];
		ret[0] = Arrays.stream(ss).filter(o -> (o.isDirection(DIRECTION.X) || o.isDirection(DIRECTION.NX))).findFirst().get().getColor();
		ret[1] = Arrays.stream(ss).filter(o -> (o.isDirection(DIRECTION.Y) || o.isDirection(DIRECTION.NY))).findFirst().get().getColor();
		ret[2] = Arrays.stream(ss).filter(o -> (o.isDirection(DIRECTION.Z) || o.isDirection(DIRECTION.NZ))).findFirst().get().getColor();
		return ret;
	}
	/** XYZ色配列csをを回転する、軸axisは不変、他交換 */
	public static Color[] rotateColorXYZ(Color[] cs, DIRECTION axis) {
		if(axis.equals(DIRECTION.X) || axis.equals(DIRECTION.NX)) {
			Color tmp = cs[1];
			cs[1] = cs[2];
			cs[2] = tmp;
		}else if(axis.equals(DIRECTION.Y) || axis.equals(DIRECTION.NY)) {
			Color tmp = cs[0];
			cs[0] = cs[2];
			cs[2] = tmp;
		}else { // Z or NZ
			Color tmp = cs[0];
			cs[0] = cs[1];
			cs[1] = tmp;
		}
		return cs;
	}
	/** 対応色を設定 */
	public void setColorXYZ(Color[] cs) {
		Arrays.stream(ss).filter(o -> (o.isDirection(DIRECTION.X) || o.isDirection(DIRECTION.NX))).findFirst().get().setColor(cs[0]);
		Arrays.stream(ss).filter(o -> (o.isDirection(DIRECTION.Y) || o.isDirection(DIRECTION.NY))).findFirst().get().setColor(cs[1]);
		Arrays.stream(ss).filter(o -> (o.isDirection(DIRECTION.Z) || o.isDirection(DIRECTION.NZ))).findFirst().get().setColor(cs[2]);
	}

}
