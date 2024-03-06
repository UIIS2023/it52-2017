package shapes;

import java.awt.Color;
import java.awt.Graphics;


public class Point extends Shape {
	
	private int x;
    private int y;

    public Point() {
    	
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, Color color) {
        this(x, y);
        setColor(color);
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawLine(x - 2, y, x + 2, y);
        g.drawLine(x, y + 2, x, y - 2);
        if (isSelected()) 
        	selected(g);
    }
    
    @Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point point = (Point) obj;
			return x == point.getX() && y == point.getY();
		}
		return false;
	}
    
    @Override
	public int compareTo(Shape shape) {
		if (shape instanceof Point) {
			Point startCoordinates = new Point(0, 0);
			return (int) (distance(startCoordinates) - ((Point) shape).distance(startCoordinates));
		}
		return 0;
	}
    
    @Override
    public String toString() {
        return "Point: x=" + x + "; y=" + y + "; color=" + getColor().toString().substring(14).replace('=', '-');
    }
    
    public void moveBy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void selected(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(x - 3, y - 3, 6, 6);
    }

    public boolean contains(int x, int y) {
        if (new Point(x, y).distance(this) <= 2) 
        	return true;
        return false;
    }
    
    public Point clone() {
    	return new Point(x, y, getColor());
    }

    public double distance(Point point) {
        return Math.sqrt(Math.pow(x - point.x, 2) + Math.pow(y - point.y, 2));
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
    
}