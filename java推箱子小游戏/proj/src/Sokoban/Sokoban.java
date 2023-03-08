package Sokoban;

import java.awt.Button;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sokoban extends Application {
	// File root paths
	private final String mapDir = "maps";
	private final String imgDir = "imgs";
	// All map files
	private List<File> mapFiles;
	// Current game level
	private int currentLevel = 0;
	// Current map
	private MapPane currentMap = null;
	// Load all map files in the application
	public void loadMapFiles() {
		mapFiles = new ArrayList<File>();
		File dir = new File(mapDir);
		for(File f:dir.listFiles()) {
			mapFiles.add(f);
		}
	}
	public void start(Stage primaryStage) throws Exception {
		loadMapFiles();
		VBox vb = new VBox();
		ToolBar tb = new ToolBar();
		Label label = new Label("Current Level:" + currentLevel);
		// Click button for changing to prevoies level map
		Button preBtn = new Button("Previous");
		preBtn.setOnAction(e -> {
			if(currentLevel > 0) {
				currentLevel--;
				currentMap.loadMap(mapFiles.get(currentLevel));
				label.setText("Current Level:" + currentLevel);
				primaryStage.sizeToScene();
			}
		});
	// Click button for changing to next level map
		Button nextBtn = new Button("Next");
		nextBtn.setOnAction(e -> {
			if(currentLevel<3){
				currentLevel++;
				currentMap.loadMap(mapFiles.get(currentLevel));
				label.setText("Current Level:" + currentLevel);
				primaryStage.sizeToScene();
			}
		});
	// Click button for resetting this map
		Button resetBtn = new Button("Reset");
		resetBtn.setOnAction(e -> {
			currentMap.loadMap(mapFiles.get(currentLevel));
			primaryStage.sizeToScene();
		});
		tb.getItems().addAll(label, preBtn, nextBtn, resetBtn);
	// Add an alert dialog box
	Alert a = new Alert(Alert.AlertType.CONFIRMATION);
	a.setHeaderText("You have won");
	a.setContentText("Next level?");
	a.setOnCloseRequest(e -> {
		if(currentLevel < mapFiles.size() - 1) {
			currentLevel++;
			currentMap.loadMap(mapFiles.get(currentLevel));
			label.setText("Current Level:" + currentLevel);
			primaryStage.sizeToScene();
		}
	});
	// Add a map pane and load the first level map data
	Image[] icons = new Image[5];
	icons[Element.MAN] = new Image("file:" + imgDir + "/man.png");
	icons[Element.BOX] = new Image("file:" + imgDir + "/box.png");
	icons[Element.WALL] = new Image("file:" + imgDir + "/wall.png");
	icons[Element.TARGET] = new Image("file:" + imgDir + "/target.png");
	icons[Element.BACKGROUND] = new Image("file:" + imgDir + "/floor.png");
	currentMap = new MapPane(icons, mapFiles.get(currentLevel));
	vb.getChildren().addAll(tb, currentMap);
	Scene scene = new Scene(vb);
	scene.setOnKeyPressed(e -> {
	switch(e.getCode()) {
	case LEFT: currentMap.moveManLeft();break;
	case RIGHT: ...;break;
	case UP: ...;break;
	case DOWN: ...;break;
	default: break;
	}
	if(currentMap.judge()) {
	a.show();
	}
	});
	primaryStage.sizeToScene();
	primaryStage.setResizable(false);
	primaryStage.setScene(scene);
	primaryStage.getIcons().add(new Image("file:" + imgDir + "/man.png"));
	primaryStage.setTitle("Sokoban");
	primaryStage.show();
	}
	}
