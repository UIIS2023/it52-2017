package shapes;

import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends SurfaceShape {

	private Point upLeft;
	private int width;
	private int height;

    public Rectangle() {
    	
    }
    
    public Rectangle(Point upLeft, int width, int height) {
        this.upLeft = upLeft;
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point upLeft, int width, int height, Color edgeColor, Color interiorColor) {
        this.upLeft = upLeft;
        this.width = width;
        this.height = height;
        setColor(edgeColor);
        setInteriorColor(interiorColor);
    }
      
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawRect(upLeft.getX(), upLeft.getY(), height, width);
        fillUpShape(g);
        if (isSelected()) 
        	selected(g);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) obj;
			return upLeft.equals(rectangle.upLeft) && width == rectangle.getWidth() && height == rectangle.getHeight();
		}
		return false;
	}
	
	@Override
	public int compareTo(Shape o) {
		if(o instanceof Rectangle){
			Rectangle r = (Rectangle) o;
			return (int) (this.area() - r.area());
		}
		else
			return 0;
	}

    @Override
    public String toString() {
    	return "Rectangle: x=" + upLeft.getX() + "; y=" + upLeft.getY() + "; height=" + height + "; width=" + width + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInteriorColor().toString().substring(14).replace('=', '-');
    }

	@Override
	public void moveBy(int x, int y) {
		upLeft.moveBy(x, y);
	}
 
    @Override
    public void selected(Graphics g) {
    	g.setColor(Color.BLUE);
        new Line(getUpLeft(), new Point(upLeft.getX() + height, upLeft.getY())).selected(g);
        new Line(getUpLeft(), new Point(upLeft.getX(), upLeft.getY() + width)).selected(g);
        new Line(new Point(getUpLeft().getX() + height, upLeft.getY()), diagonal().getEndPoint()).selected(g);
        new Line(new Point(upLeft.getX(), upLeft.getY() + width), diagonal().getEndPoint()).selected(g);
    }
 
    @Override
    public boolean contains(int x, int y) {
        if (upLeft.getX() <= x && x <= (upLeft.getX() + width) && upLeft.getY() <= y && y <= upLeft.getY() + height)
        	return true;
        return false;
    }
    
    public Rectangle clone() {
    	return new Rectangle(upLeft.clone(), width, height, getColor(), getInteriorColor());
    }

    @Override
    public void fillUpShape(Graphics g) {
        g.setColor(getInteriorColor());
        g.fillRect(upLeft.getX() + 1, upLeft.getY() + 1, height - 1, width - 1);
    }
    
    public int area() {
		return width * height;
	}

    public Line diagonal() {
        return new Line(upLeft, new Point(upLeft.getX() + height, upLeft.getY() + width));
    }
    
    public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Point getUpLeft() {
        return upLeft;
    }

    public void setUpLeft(Point upLeft) {
        this.upLeft = upLeft;
    }
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}