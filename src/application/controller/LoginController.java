package application.controller;

import org.gitproject.homeassistant.User;
import org.gitproject.homeassistant.Userdata;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

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
    	if((Userdata.getLoginType() == User.USERANDPWD && usernameField.getText().isEmpty()) || passwordField.getText().isEmpty())
    	{
    		setError(usernameField);
    		setError(passwordField);
    		return;
    	}
    	
    	if(Userdata.getLoginType() == User.USERANDPWD && usernameField.getStyle().equals("-jfx-unfocus-color: #d32f2f;"))
    		usernameField.setStyle("");
    	
    	if(passwordField.getStyle().equals("-jfx-unfocus-color: #d32f2f;"))
    		passwordField.setStyle("");
    	
    	User profile = new User(usernameField.getText());
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
    
    public void rmuser()
    {
    	this.usernameField.setVisible(false);
    }
    
    private boolean setError(JFXTextField field)
    {
    	if(Userdata.getLoginType() == User.USERANDPWD && field.getText().isEmpty())
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
