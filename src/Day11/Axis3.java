package Day11;

import java.util.HashMap;
import java.util.Map;

public class Axis3 {
	int x = 0;
	int y = 0;
	int z = 0;
	
	public static enum Vector {
		n,
		ne,
		nw,
		s,
		se,
		sw
	}
	public static Map<String,Vector> axialCoord = new HashMap<String, Vector>();
	
	public Axis3(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Axis3() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public static void initializeAxialCoords() {
		axialCoord.put("n", Axis3.Vector.n);
		axialCoord.put("ne", Axis3.Vector.ne);
		axialCoord.put("nw", Axis3.Vector.nw);
		axialCoord.put("s", Axis3.Vector.s);
		axialCoord.put("se", Axis3.Vector.se);
		axialCoord.put("sw", Axis3.Vector.sw);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}	
	
	public String toString() {
		String result = "";
		result += x+","+y+","+z;
		return result;
	}
	
	public void applyVector(Axis3.Vector vector) {
		Axis3 newPosition = null;
		switch (vector) {
		case n:
			newPosition = new Axis3(0,1,-1);
			break;
		case nw:
			newPosition = new Axis3(-1,1,0);
			break;
		case ne:
			newPosition = new Axis3(1,0,-1);
			break;
		case s:
			newPosition = new Axis3(0,-1,1);
			break;
		case se:
			newPosition = new Axis3(1,-1,0);
			break;
		case sw:
			newPosition = new Axis3(-1,0,1);
			break;			
		}
		
		this.setX(this.x + newPosition.getX());
		this.setY(this.y + newPosition.getY());
		this.setZ(this.z + newPosition.getZ());
	}
	
	public static int distance (Axis3 a, Axis3 b) {
		int result = 0;
		int x = a.x;
		int y = a.y;
		int z = a.z;
		result = ((Math.abs(x - b.x) + Math.abs(y - b.y) + Math.abs(z - b.z)));
		result /= 2;
		return result;
	}
	
	
}
