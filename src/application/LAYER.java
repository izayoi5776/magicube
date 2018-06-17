package application;

public enum LAYER {
	TOP,
	LEFT,
	RIGHT,
	FRONT,
	REAR,
	BOTTOM;
	
	public DIRECTION toDIRECTION() {
		DIRECTION ret = null;
		switch(this) {
		case TOP:
			ret = DIRECTION.Z;
			break;
		case LEFT:
			ret = DIRECTION.NX;
			break;
		case RIGHT:
			ret = DIRECTION.X;
			break;
		case FRONT:
			ret = DIRECTION.NY;
			break;
		case REAR:
			ret = DIRECTION.Y;
			break;
		case BOTTOM:
			ret = DIRECTION.NZ;
			break;
		default:
			assert(false);
		}
		return ret;
	}
}
