package application;

import java.util.Arrays;

/** １個Faceだけ含みblock。各面真ん中１個だけ、計６個　 */
public class Block1 extends Block{
	
	public Block1() {
	}
	public void init(Face s) {
		this.ss = new Face[1];
		ss[0] = s;
	}
	public Face[] getFace() {return ss;}
	
}
