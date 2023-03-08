package Sokoban;

import javax.swing.text.html.ImageView;//??

public abstract class Element extends ImageView {
	// Image element types
	public final static int MAN = 0;
	public final static int BOX = 1;
	public final static int WALL = 2;
	public final static int TARGET = 3;
	public final static int BACKGROUND = 4;
	protected Element(Image img) {
		super(img);
	// Set the display size
		this.setFitHeight(MapPane.CELL_SIZE);
		this.setFitWidth(MapPane.CELL_SIZE);
	}
}