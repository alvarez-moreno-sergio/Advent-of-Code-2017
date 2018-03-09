package Day3;

public class Point {
	public int x;
	public int y;
	
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return this.x+","+this.y;
	}
	
//	public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Point point = (Point) o;
//
//        if (x != point.x) return false;
//        if (y != point.y) return false;
//
//        return true;
//    }
//
//    public int hashCode() {
//        int result = x;
//        result = 31 * result + y;
//        return result;
//    }
    
	
	
    public static int getManhattanDistance(Point start, Point end) {
    	int result = 0;
    	int x = start.x - end.x;
    	int y = start.x - end.y;
    	
    	result = Math.abs(x) + Math.abs(y);
    	return result;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
