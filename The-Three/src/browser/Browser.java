package browser;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Browser extends Region {
	static WebView browser=new WebView();
	public Browser() {
		getStyleClass().add("browser");
		browser.getEngine().load(Browser.class.getResource("map.html").toExternalForm());
		getChildren().add(browser);
	}
	private Node createSpacer() {
		Region spacer=new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
	}
	
	@Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 900;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 600;
    }
    public void execute(String s) {
    	Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				browser.getEngine().executeScript(s);
			}
			
		});
    	
    }
}
