package application;

import java.util.Arrays;

public class Block {
	protected Face[] ss;

	/** layer指定の層に属ならtrueを返す */
	//Block[] getLayer(LAYER layer);

	/** 当ブロックは任意の面が */
	public boolean isLayer(LAYER layer) {
		DIRECTION dir = layer.toDIRECTION();
		return Arrays.stream(ss).anyMatch(o -> o.isDirection(dir));
	}

}
