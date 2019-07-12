package application.controller;

import org.gitproject.homeassistant.User;
import org.gitproject.homeassistant.Userdata;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class SignupController {
	
	private boolean isValid = false;
	
    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private MaterialDesignIconView lengthCheck;

    @FXML
    private MaterialDesignIconView charactersCheck;

    @FXML
    private MaterialDesignIconView numberCheck;

    @FXML
    private void login(ActionEvent event) {
    	if((Userdata.getLoginType() == User.USERANDPWD && usernameField.getText().isEmpty()) || passwordField.getText().isEmpty())
    	{
    		return;
    	}
    	
    	if(Userdata.getLoginType() == User.USERANDPWD && usernameField.getStyle().equals("-jfx-unfocus-color: #d32f2f;"))
    		usernameField.setStyle("");
    	
    	if(passwordField.getStyle().equals("-jfx-unfocus-color: #d32f2f;"))
    		passwordField.setStyle("");
    	
    	if(isValid)
    	{
    		User profile = new User(usernameField.getText());
    		profile.setPassword(passwordField.getText());
    
    		if(Userdata.compareUsers(profile) == 0)
    			ControllerList.switcher.switchToHomeScreen();
    	}
    }
    
    @FXML
    private void pwdCheck(KeyEvent event) {
    	String text = passwordField.getText() + event.getCharacter();
    	
    	this.isValid = false;
    	boolean upper = false;
    	boolean lower = false;
    	boolean lenValid = false;
    	boolean numValid = false;
    	for(int i = 0; i < text.length(); i++)
    	{
    		if(text.length() >= 8)
    			lenValid = true;
    		if(Character.isUpperCase(text.charAt(i)))
    			upper = true;
    		else if(Character.isLowerCase(text.charAt(i)))
    			lower = true;
    		else if(Character.isDigit(text.charAt(i)))
    			numValid = true;
    	}
    	
    	if(upper && lower){
    		this.charactersCheck.setGlyphName("CHECK");
    		this.charactersCheck.setFill(Color.GREEN);
    	}
    	else{
    		this.charactersCheck.setGlyphName("WINDOW_CLOSE");
    		this.charactersCheck.setFill(Color.RED);
    	}
    	
    	if(lenValid){
    		this.lengthCheck.setGlyphName("CHECK");
    		this.lengthCheck.setFill(Color.GREEN);
    	}
    	else{
    		this.lengthCheck.setGlyphName("WINDOW_CLOSE");
    		this.lengthCheck.setFill(Color.RED);
    	}
    	
    	if(numValid){
    		this.numberCheck.setGlyphName("CHECK");
    		this.numberCheck.setFill(Color.GREEN);
    	}
    	else {
    		this.numberCheck.setGlyphName("WINDOW_CLOSE");
    		this.numberCheck.setFill(Color.RED);
    	}
    	
    	if(upper && lower && lenValid && numValid)
    		isValid = true;
    }
    
    public void rmuser()
    {
    	this.usernameField.setVisible(false);
    }

}