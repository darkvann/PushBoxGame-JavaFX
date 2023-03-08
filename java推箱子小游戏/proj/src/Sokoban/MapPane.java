package Sokoban;

import javax.swing.text.html.ImageView;

public class MapPane extends Panel {
	// The cell size
	public final static int CELL_SIZE = 64;
	// All images for element displaying
	private Image[] icons;
	// Logical storage space for all elements
	private ImageView[][] map;
	// Controlled object
	private Man man;
	// Mission points
	private List<ImageView> targets;
	// Number of cells
	private int xlength;
	private int ylength;
	// Constructor.
	public MapPane(Image[] iconList, File mapFile) {
		icons = iconList;
		// Load map
		this.loadMap(mapFile);
		// Set background
		Background bg = new Background(
				new BackgroundImage(
						iconList[Element.BACKGROUND],
						BackgroundRepeat.REPEAT,
						BackgroundRepeat.REPEAT,
						BackgroundPosition.DEFAULT,
						new BackgroundSize(
								BackgroundSize.AUTO,
								BackgroundSize.AUTO,
								true, true, false, false)
							)
				);
		setBackground(bg);
	}
		// Clear old data and load new map data
	public void loadMap(File mapFile) {
		try(Scanner input = new Scanner(mapFile)){
			// Get length information from the first line in the map file
			String[] items = input.nextLine().split(",");
			int xlen = Integer.parseInt(items[0]);
			int ylen = Integer.parseInt(items[1]);
			xlength = xlen;
			ylength = ylen;
			// Initialize and clear the map pane
			this.map = new ImageView[xlen][ylen];
			targets = new ArrayList<ImageView>();
			this.getChildren().clear();
			// Read file and add elements to the map pane
			while(input.hasNextLine()) {
				// Get information of a element from string parsing
				items = input.nextLine().split(",");
				int x = Integer.parseInt(items[0]);
				int y = Integer.parseInt(items[1]);
				int type = Integer.parseInt(items[2]);
				// Create an element
				Element e = null;
				switch(type) {
					case Element.MAN: e = this.man = new Man(icons[Element.MAN]);
						break;
					case Element.BOX: ...;break;
					case Element.WALL: ...;break;
					case Element.TARGET: ...;break;
				default:break;
				}
				// Set the position and display the element
				e.setX(x * CELL_SIZE);
				e.setY(y * CELL_SIZE);
				getChildren().add(e);
				// Add element to the logical map
				if(e instanceof Target) {
					targets.add(e);
				}else {
					this.map[x][y] = e;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	// Judge the state of the game
	public boolean judge() {
		boolean win = true;
		for(...) {
			int x = (int) (img.getX() / CELL_SIZE);
			int y = (int) (img.getY() / CELL_SIZE);
			if(...)) {
				win = false;
				break;
			}
		}
		return win;
	}
	// How to move the man and boxes in the upward direction
	public void moveManUp(){
		int manX = (int) (man.getX() / CELL_SIZE);
		int manY = (int) (man.getY() / CELL_SIZE);
		if(manY > 0) {
			if(map[manX][manY - 1] == null) {
				man.up();
				map[manX][manY] = null;
				map[manX][manY - 1] = man;
			}else if(map[manX][manY - 1] instanceof Box) {
				System.out.println("hahah");
				if(manY - 1 > 0 && map[manX][manY - 2] == null) {
					Box b = (Box) map[manX][manY - 1];
					b.up();
					map[manX][manY - 2] = b;
				}
				man.up();
				map[manX][manY] = null;
				map[manX][manY - 1] = man;
			}
		}
	}
	
	// How to move the man and boxes in the downward direction
	public void moveManDown() {
		...
	}
	// How to move the man and boxes in the leftward direction
	public void moveManLeft(){
		...
	}
	// How to move the man and boxes in the rightward direction
	public void moveManRight() {
		...
	}
}