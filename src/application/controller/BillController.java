package application.controller;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.List;

import org.gitproject.homeassistant.SystemInfo;
import org.gitproject.homeassistant.lists.Bill;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class BillController extends AppController{

	@FXML
	private Text billName;

	@FXML
	private JFXButton backButton_BG;

	@FXML
	private OctIconView backButton_Icon;

	@FXML
	private Text expireDate;

	@FXML
	private Text billType;

	@FXML
	private Text billPrice;

	@FXML
	private JFXButton checkButton;

	public void setBill(Bill bill)
	{
		this.billType.setText(bill.getType().toString());
		this.billName.setText(bill.getName());
		this.billPrice.setText(bill.getName());
	}
}
