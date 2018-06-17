package application;

import java.util.Arrays;
import java.util.Timer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

/** Magic Cube main class  */
public class Cube {
	/** block1内は空間位置に従って配置。0=TOP, 1=LEFT, 2=RIGHT, 3=FRONT, 4=REAR, 5=BOTTOM  */
	private Block1[] block1;

	/** block2内は空間位置に従って配置。
	 *  0=TOP2, 1=TOP4, 2=TOP6, 3=TOP8,
	 *  4=LEFT2, 5=LEFT4, 6=LEFT8,
	 *  7=RIGHT2, 8=RIGHT6, 9=RIGHT8,
	 *  10=FRONT2, 
	 *  11=REAR8,
	 **/
	private Block2[] block2;

	/** block3内は空間位置に従って配置。
	 *  0=TOP1, 1=TOP3, 2=TOP7, 3=TOP9,
	 *  4=LEFT1, 5=LEFT7
	 *  6=RIGHT3, 7=RIGHT9
	 **/
	private Block3[] block3;

	/** for auto run */
	private Timer timer = null;
	
	
	public Cube() {
	}

	/**
	 *  初期化
	 *  @param recs　表現用、５４面をTop、Left、Right、Front、Rear、Bottom順に配置。
	 *     面内、左から右、上からしたの順配置。Top以外は展開後平面から見た左右上下。
	 *     面の展開方法は下図参考。面とはCubeの外面のこと。
	 *                   Bottom
	 *                   Front
	 *           Left     Top     Right
	 *                    Rear
	 *     面内の数値配置は数に如く
	 *       1  2  3
	 *       4  5  6
	 *       7  8  9
	 * 
	 * 　 */
	public void init(Rectangle[] recs) {
		assert recs.length == 54 : "face should 54 but " + recs.length;
		
		//block1 = new Block1[6];
		block1 = IntStream.range(0, 6).mapToObj(i -> new Block1()).toArray(Block1[]::new);
		block1[0].init(new Face(recs[      4], DIRECTION.Z));
		block1[1].init(new Face(recs[  9 + 4], DIRECTION.NX));
		block1[2].init(new Face(recs[2*9 + 4], DIRECTION.X));
		block1[3].init(new Face(recs[3*9 + 4], DIRECTION.NY));
		block1[4].init(new Face(recs[4*9 + 4], DIRECTION.Y));
		block1[5].init(new Face(recs[5*9 + 4], DIRECTION.NZ));

		//block2 = new Block2[12];
		block2 = IntStream.range(0, 12).mapToObj(i -> new Block2()).toArray(Block2[]::new);
		block2[0].init(new Face(recs[      1], DIRECTION.Z), new Face(recs[3*9 + 7], DIRECTION.NY));
		block2[1].init(new Face(recs[      3], DIRECTION.Z), new Face(recs[1*9 + 5], DIRECTION.NX));
		block2[2].init(new Face(recs[      5], DIRECTION.Z), new Face(recs[2*9 + 3], DIRECTION.X));
		block2[3].init(new Face(recs[      7], DIRECTION.Z), new Face(recs[4*9 + 1], DIRECTION.Y));
		
		block2[4].init(new Face(recs[1*9 + 1], DIRECTION.NX), new Face(recs[3*9 + 3], DIRECTION.NY));
		block2[5].init(new Face(recs[1*9 + 3], DIRECTION.NX), new Face(recs[5*9 + 3], DIRECTION.NZ));
		block2[6].init(new Face(recs[1*9 + 7], DIRECTION.NX), new Face(recs[4*9 + 3], DIRECTION.Y));
		
		block2[7].init(new Face(recs[2*9 + 1], DIRECTION.X), new Face(recs[3*9 + 5], DIRECTION.NY));
		block2[8].init(new Face(recs[2*9 + 5], DIRECTION.X), new Face(recs[5*9 + 5], DIRECTION.NZ));
		block2[9].init(new Face(recs[2*9 + 7], DIRECTION.X), new Face(recs[4*9 + 5], DIRECTION.Y));

		block2[10].init(new Face(recs[3*9+ 1], DIRECTION.NY),new Face(recs[5*9 + 7], DIRECTION.NZ));
		block2[11].init(new Face(recs[4*9+ 7], DIRECTION.Y), new Face(recs[5*9 + 1], DIRECTION.NZ));

		//block3 = new Block3[8];
		block3 = IntStream.range(0, 8).mapToObj(i -> new Block3()).toArray(Block3[]::new);
		block3[0].init(new Face(recs[      0], DIRECTION.Z), new Face(recs[1*9 + 2], DIRECTION.NX), new Face(recs[3*9 + 6], DIRECTION.NY));
		block3[1].init(new Face(recs[      2], DIRECTION.Z), new Face(recs[2*9 + 0], DIRECTION.X) , new Face(recs[3*9 + 8], DIRECTION.NY));
		block3[2].init(new Face(recs[      6], DIRECTION.Z), new Face(recs[1*9 + 8], DIRECTION.NX), new Face(recs[4*9 + 0], DIRECTION.Y));
		block3[3].init(new Face(recs[      8], DIRECTION.Z), new Face(recs[2*9 + 6], DIRECTION.X) , new Face(recs[4*9 + 2], DIRECTION.Y));

		block3[4].init(new Face(recs[1*9 + 0], DIRECTION.NX), new Face(recs[3*9 + 0], DIRECTION.NY), new Face(recs[5*9 + 6], DIRECTION.NZ));
		block3[5].init(new Face(recs[1*9 + 6], DIRECTION.NX), new Face(recs[4*9 + 6], DIRECTION.Y) , new Face(recs[5*9 + 0], DIRECTION.NZ));

		block3[6].init(new Face(recs[2*9 + 2], DIRECTION.X), new Face(recs[3*9 + 2], DIRECTION.NY), new Face(recs[5*9 + 8], DIRECTION.NZ));
		block3[7].init(new Face(recs[2*9 + 8], DIRECTION.X), new Face(recs[4*9 + 8], DIRECTION.Y),  new Face(recs[5*9 + 2], DIRECTION.NZ));
		
		resetColor();
	}
	// 全部色初期化
	public void resetColor() {
		setColor(Color.LIMEGREEN, DIRECTION.Z);
		setColor(Color.DARKORANGE, DIRECTION.NX);
		setColor(Color.RED, DIRECTION.X);
		setColor(Color.WHITE, DIRECTION.NY);
		setColor(Color.DARKSLATEBLUE, DIRECTION.Y);
		setColor(Color.YELLOW, DIRECTION.NZ);
	}

	// 指定面を全部色ｃにする
	private void setColor(Color c, DIRECTION dir) {
		Arrays.stream(block1).flatMap(ss -> Arrays.stream(ss.getFace())).filter(s -> s.isDirection(dir)).forEach(s -> s.setColor(c));
		Arrays.stream(block2).flatMap(ss -> Arrays.stream(ss.getFace())).filter(s -> s.isDirection(dir)).forEach(s -> s.setColor(c));
		Arrays.stream(block3).flatMap(ss -> Arrays.stream(ss.getFace())).filter(s -> s.isDirection(dir)).forEach(s -> s.setColor(c));
	}
	
	
	/** 
	 * cubeの移動を表す
	 * ブロックは層を成す
	 * 軸は不動と仮定する
	 * ※軸の運動は他の層の複数移動で表現できるため
	 * ※つまりBlock1の６つブロックは不動
	 * <br>
	 * すると、可動の層は６つ、層毎Block1の一つのみ含む
	 * そのBlock1のブロックの名前で層を命名すると、0=TOP層、1=LEFT層、2=RIGHT層、
	 * 3=FRONT層、4=REAR層、5=BOTTOM層なる
	 * <br>
	 * 層の運動は旋転のみ。
	 * 層は時計廻り（つまり１番ブロックが３番の位置に移動するような方向）９０度移動すると１、
	 * 逆に反時計回り９０度は-1と記載
	 * 旋転のため、以下の式常に成立する<br>
	 * rotate(±4) == rotate(0)<br>
	 * rotate(1)  == rotate(-3)<br>
	 * rotate(2)  == rotate(-2)<br>
	 * rotate(3)  == rotate(-1)<br>
	 * 
	 * 
	 **/
	public void rotate(LAYER layer, boolean clockwise) {
		// 指定の層対応のBLOCKｓを取得
		Stream.concat(
			Stream.concat(
				Arrays.stream(block1).filter(o -> o.isLayer(layer)), 
				Arrays.stream(block2).filter(o -> o.isLayer(layer))
			),
			Arrays.stream(block3).filter(o -> o.isLayer(layer))
		);
		// 指定の回転を掛ける
		// block1は回転しない
		// block2は回転
		// １、block2内の保存位置変化。２、対応Faceの方向の内、回転面でない方が変化する
		int[] base2 = {0,1,2,3,
				4,5,1,6,
				7,2,8,9,
				10,4,7,0,
				3,6,9,11,
				11,5,8,10
		};
		int[] pos2 = Arrays.copyOfRange(base2, layer.ordinal()*4 + 0 , layer.ordinal()*4+4);
		rotateColor2(layer.toDIRECTION(), clockwise, pos2);
		
		// block3の回転
		// １、block3内の保存位置変化。２、対応Faceの方向の内、回転面でない方が変化する
		/** 回転面により、回転対象となるblock3対象が変数内の位置 */
		int base3[] = {0,1,2,3,
				4,0,5,2,
				1,6,3,7,
				4,6,0,1,
				2,3,5,7,
				5,7,4,6
		};
		int[] pos3 = Arrays.copyOfRange(base3, layer.ordinal()*4 + 0 , layer.ordinal()*4+4);
		rotateColor3(layer.toDIRECTION(), clockwise, pos3);
	}
	// boock3用回転関数
	// dir=回転軸、clockwise＝時計まわりか、pos3＝0=位置１、1=３、2=７、3=９がblock3配列内の位置
	private void rotateColor3(DIRECTION dir, boolean clockwise, int[] pos3) {
		Color[] ctmp = block3[pos3[0]].getColorXYZ();
		if(clockwise) {
			block3[pos3[0]].setColorXYZ(Block3.rotateColorXYZ(block3[pos3[2]].getColorXYZ(), dir));
			block3[pos3[2]].setColorXYZ(Block3.rotateColorXYZ(block3[pos3[3]].getColorXYZ(), dir));
			block3[pos3[3]].setColorXYZ(Block3.rotateColorXYZ(block3[pos3[1]].getColorXYZ(), dir));
			block3[pos3[1]].setColorXYZ(Block3.rotateColorXYZ(ctmp, dir));
		}else {
			block3[pos3[0]].setColorXYZ(Block3.rotateColorXYZ(block3[pos3[1]].getColorXYZ(), dir));
			block3[pos3[1]].setColorXYZ(Block3.rotateColorXYZ(block3[pos3[3]].getColorXYZ(), dir));
			block3[pos3[3]].setColorXYZ(Block3.rotateColorXYZ(block3[pos3[2]].getColorXYZ(), dir));
			block3[pos3[2]].setColorXYZ(Block3.rotateColorXYZ(ctmp, dir));
		}
	}
	// bolck2用回転関数
	private void rotateColor2(DIRECTION dir, boolean clockwise, int[] pos2) {
		Pair<Color, Color> ctmp = block2[pos2[0]].getColorPair(dir);
		if(clockwise) {
			block2[pos2[0]].setColorPair(dir, block2[pos2[1]].getColorPair(dir));
			block2[pos2[1]].setColorPair(dir, block2[pos2[3]].getColorPair(dir));
			block2[pos2[3]].setColorPair(dir, block2[pos2[2]].getColorPair(dir));
			block2[pos2[2]].setColorPair(dir, ctmp);
		}else {
			block2[pos2[0]].setColorPair(dir, block2[pos2[2]].getColorPair(dir));
			block2[pos2[2]].setColorPair(dir, block2[pos2[3]].getColorPair(dir));
			block2[pos2[3]].setColorPair(dir, block2[pos2[1]].getColorPair(dir));
			block2[pos2[1]].setColorPair(dir, ctmp);
		}
	}
	// 自動で動かす
	public void autoRun() {
		if(timer!=null) {
			timer.cancel();
			timer = null;
		}else {
			timer = new Timer();
			timer.schedule(new MyTask(this), 100, 100);
		}
	}
	class MyTask extends TimerTask{
		private Cube c;
		
		public MyTask(Cube c) {
			this.c= c;
		}
		@Override
		public void run() {
			LAYER[] sa = LAYER.values();
			LAYER layer = sa[(int) (Math.random() * sa.length)];
			boolean clockwise = Math.random() > 0.5;
			System.out.println("autorun layer=" + layer + " clockwise=" + clockwise);
			c.rotate(layer, clockwise);
		}
		
	}
}
