package application.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import it.edu.majoranapa.Profile;
import it.edu.majoranapa.Userdata;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton loginButton;
    
    @FXML
    public void login(ActionEvent event) {
    	if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
    	{
    		setError(usernameField);
    		setError(passwordField);
    		return;
    	}
    	
    	if(usernameField.getStyle().equals("-jfx-unfocus-color: #d32f2f;"))
    		usernameField.setStyle("");
    	
    	if(passwordField.getStyle().equals("-jfx-unfocus-color: #d32f2f;"))
    		passwordField.setStyle("");
    	
    	Profile profile = new Profile(usernameField.getText());
    	profile.setPassword(passwordField.getText());
    	switch(Userdata.compareUsers(profile))
    	{
    	case 0:
    		ControllerList.switcher.switchToHomeScreen();
    		break;
    	case 1:
    		setError(usernameField);
    		break;
    	case 2:
    		setError(passwordField);
    		break;
    	case 3:
    		setError(usernameField);
    		setError(passwordField);
    	}
    			
    }
    
    private boolean setError(JFXTextField field)
    {
    	if(field.getText().isEmpty())
    	{
    		field.setStyle("-jfx-unfocus-color: #d32f2f;");
    		return true;
    	}
    	else
    		field.setStyle("-jfx-unfocus-color: #4d4d4d;");
    	
    	return false;
    }
    
    private boolean setError(JFXPasswordField field)
    {
    	if(field.getText().isEmpty())
    	{
    		field.setStyle("-jfx-unfocus-color: #d32f2f;");
    		return true;
    	}
    	else
    		field.setStyle("-jfx-unfocus-color: #4d4d4d;");
    	
    	return false;
    }

}
