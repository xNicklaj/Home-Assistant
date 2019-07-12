package application.controller;

import org.gitproject.homeassistant.Console;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TimerController extends AppController {

	@FXML
	private AnchorPane background_fill_state;

	@FXML
	private JFXButton prompt_01;

	@FXML
	private JFXButton prompt_02;

	@FXML
	private JFXButton prompt_03;

	@FXML
	private JFXButton prompt_04;

	@FXML
	private JFXButton prompt_05;

	@FXML
	private JFXButton timer_button;

	@FXML
	private OctIconView back_button;

	@FXML
	private TextField timer_min;


	@FXML
	private TextField timer_sec;
	
	private TimerBackgroundRegulatorThread regulator;

	@FXML
	private void setTimer(ActionEvent event) {
		int csec = Integer.parseInt(timer_sec.getText());
		int cmin = Integer.parseInt(timer_min.getText());
		if(event.getSource() == prompt_01)
		{
			timer_sec.setText("" + (csec + 30));
		}
		else if(event.getSource() == prompt_02)
		{
			timer_min.setText("" + (cmin + 1));
		}
		else if(event.getSource() == prompt_03)
		{
			timer_min.setText("" + (cmin + 5));
		}
		else if(event.getSource() == prompt_04)
		{
			timer_min.setText("" + (cmin + 10));
		}
		else if(event.getSource() == prompt_05)
		{
			timer_min.setText("" + (cmin + 20));
		}
		
		csec = Integer.parseInt(timer_sec.getText());
		cmin = Integer.parseInt(timer_min.getText());
		
		if(csec == 60)
		{
			timer_min.setText("" + (cmin + 1));
			timer_sec.setText("00");
		}
		if(timer_sec.getText().length() < 2)
		{
			timer_sec.setText("0" + timer_sec.getText());
		}
		if(timer_min.getText().length() < 2)
		{
			timer_min.setText("0" + timer_min.getText());
		}
	}

	@FXML
	private void timer_start(ActionEvent event) {
		if(timer_button.getText().equals("Start"))
		{
			int delay = 60*Integer.parseInt(timer_min.getText()) + Integer.parseInt(timer_sec.getText());
			if(delay > 0)
			{
				Console.newCommand("timer start delay=" + delay);
				timer_button.setId("start-button-started");
				timer_button.setText("Stop");
				this.background_fill_state.setPrefHeight(this.background_fill_state.getParent().getScene().getHeight());
				regulator = new TimerBackgroundRegulatorThread(delay, this.background_fill_state.getParent().getScene().getHeight());
				regulator.start();
				this.prompt_01.setDisable(true);
				this.prompt_02.setDisable(true);
				this.prompt_03.setDisable(true);
				this.prompt_04.setDisable(true);
				this.prompt_05.setDisable(true);
			}
		}
		else {
			timer_button.setText("Start");
			timer_button.setId("start-button-stopped");
			Console.newCommand("timer stop");
			regulator.toggle();
			regulator = null;
			this.prompt_01.setDisable(false);
			this.prompt_02.setDisable(false);
			this.prompt_03.setDisable(false);
			this.prompt_04.setDisable(false);
			this.prompt_05.setDisable(false);
			this.background_fill_state.setPrefHeight(0);
		}
	}
	
	public JFXButton getPrompt_01() {
		return prompt_01;
	}

	public JFXButton getPrompt_02() {
		return prompt_02;
	}

	public JFXButton getPrompt_03() {
		return prompt_03;
	}

	public JFXButton getPrompt_04() {
		return prompt_04;
	}

	public JFXButton getPrompt_05() {
		return prompt_05;
	}

	public JFXButton getTimer_button() {
		return timer_button;
	}

	public AnchorPane getBackground_fill_state() {
		return background_fill_state;
	}

	public TextField getTimer_sec() {
		return timer_sec;
	}

	public TextField getTimer_min() {
		return timer_min;
	}
}
