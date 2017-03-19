import processing.core.PApplet;
import processing.core.*;

public class UserInterface extends PApplet{
	
	public static void main(String[] args){
		PApplet.main("UserInterface");
		
	}
	
	public PImage vLAD;
	public Boolean searching = false;
	
	public void settings(){
		size(1000,1000);
	}

    public void setup(){
    	background(255);
    }

    public void draw(){
    	while (true){
    		searchBar();
    		fill(255);
    		background(255);
    	}
    }
    
    public void searchBar(){
    	while (searching){
    		String term = "";
        	stroke(10);
        	fill(0);
        	rect(50,100, 900, 50);
    	}


    	
    }
    
    public void mouseClicked(){
    	if (((mouseX>50)&&(mouseX<950))&&((mouseY>100)&&(mouseY<150))){
    		searching = true;
    	}
    	else{
    		searching = false;
    	}
    }
}