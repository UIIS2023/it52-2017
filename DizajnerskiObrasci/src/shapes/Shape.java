package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;


public abstract class Shape implements Moveable, Cloneable, Comparable<Shape>, Serializable {

	private boolean selected;
    private Color color = Color.BLACK;

    public Shape() {
    	
    }

    public abstract void draw(Graphics g);

    public abstract boolean contains(int x, int y);

    public abstract void selected(Graphics g);

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}