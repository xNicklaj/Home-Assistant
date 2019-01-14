package application.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TimerAppController {

    @FXML
    private JFXButton JFXTimerStartButton;

    @FXML
    private JFXTimePicker JFXTimePicker;
    
    public String getTimePickerTime(ActionEvent event)
    {
    	return JFXTimePicker.getPromptText();
    }

}
