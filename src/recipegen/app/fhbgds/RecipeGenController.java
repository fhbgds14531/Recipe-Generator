package recipegen.app.fhbgds;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class RecipeGenController {

	private boolean generated = false;
	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button generateButton;
    @FXML
    private TextArea nameArea;
    @FXML
    private Button saveButton;
    @FXML
    private TextArea recipeArea;
    
    @FXML
    void generateRecipe(ActionEvent event) {
    	String[] recipe = RecipeGenerator.generateRecipe();
    	this.nameArea.setText(recipe[0]);
    	this.recipeArea.setText(recipe[1]);
    	this.generated = true;
    }

    @FXML
    void saveRecipe(ActionEvent event) {
    	if(this.generated){
    		int num = 0;
    		File recipe = new File("Saved Recipes/" + this.nameArea.getText().replace(" ", "_") + ".txt");
    		while(recipe.exists()){
    			num++;
    			recipe = new File("Saved Recipes/" + this.nameArea.getText().replace(" ", "_") + "_" + num + ".txt");
    		}
    		try {
				recipe.createNewFile();
				RandomAccessFile raf = new RandomAccessFile(recipe, "rws");
				raf.writeChars(this.recipeArea.getText());
				raf.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }


    @FXML
    void initialize() {
        assert generateButton != null : "fx:id=\"generateButton\" was not injected: check your FXML file 'recipeGen.fxml'.";
        assert nameArea != null : "fx:id=\"nameArea\" was not injected: check your FXML file 'recipeGen.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'recipeGen.fxml'.";
        assert recipeArea != null : "fx:id=\"recipeArea\" was not injected: check your FXML file 'recipeGen.fxml'.";

        File recipeDir = new File("Saved Recipes");
        recipeDir.mkdirs();
    }
}
