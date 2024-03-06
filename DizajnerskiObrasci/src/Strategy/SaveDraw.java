package Strategy;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import view.DrawingFrame;


public class SaveDraw implements SaveOpenFile { 
	
	private DrawingFrame frame;
	
	public SaveDraw(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void save(File file) {
		BufferedImage imagebuffer = null;
	    try {
	        imagebuffer = new Robot().createScreenCapture(frame.getView().getBounds());
	        frame.getView().paint(imagebuffer.createGraphics());
	        ImageIO.write(imagebuffer,"jpeg", new File(file + ".jpeg"));
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	public void open(File file) {
		// TODO Auto-generated method stub
	}

}
