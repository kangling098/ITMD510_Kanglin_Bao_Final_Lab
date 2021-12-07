package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.scene.control.cell.PropertyValueFactory;
import models.AdminModel;
import food.Food;

public class AdminController {

	@FXML
	private Pane addFoodPanel;
	@FXML
	private Pane viewFoodPanel;
	@FXML
	private Pane updateFoodPanel;
	@FXML
	private Pane deleteFoodPanel;
	@FXML
	private TextField name;
	@FXML
	private TextField price;
	@FXML
	private Label addFoodLabel;
	@FXML
	private TableView<Food> viewFoodTable;
	
	@FXML
	private TextField deleteId;
	@FXML
	private Label deleteLabel;
	
	@FXML
	private TextField updateId;
	@FXML
	private TextField updatePrice;
	@FXML
	private Label updateLabel;
	
	
	@FXML 
	private TableColumn pidColumn ;
    @FXML 
    private TableColumn nameColumn ;
    @FXML 
    private TableColumn priceColumn ;
	

	// Declare Admin model
	AdminModel model = null;
	Statement stmt = null;

	public AdminController() {
		model = new AdminModel();
	}
	
	public void viewAccounts() {

	}
	
	public void initialFoodMenu() {
		model.initialFoodMenu();
	}

	public void handleAddPanel() {
		addFoodPanel.setVisible(true);
		deleteFoodPanel.setVisible(false);
		viewFoodPanel.setVisible(false);
		updateFoodPanel.setVisible(false);
	}
	
	public void handleViewPanel() {
		addFoodPanel.setVisible(false);
		deleteFoodPanel.setVisible(false);
		viewFoodPanel.setVisible(true);
		updateFoodPanel.setVisible(false);
		ResultSet rs = model.getView();
		ObservableList<Food> data = FXCollections.observableArrayList();
		
		// instantiate vector objects to hold column/row data for JTable
//		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
//		Vector<String> column = new Vector<String>();
		try {
			while(rs.next()){
		        int pid = rs.getInt("pid");
		        String name = rs.getString("name");
		        double price = rs.getDouble("price");
		        data.add(new Food(pid, name, price));
			}
			viewFoodTable.getItems().setAll(data);
			pidColumn.setCellValueFactory(new PropertyValueFactory("pid"));
			nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
			priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void handleDeletePanel() {
		addFoodPanel.setVisible(false);
		deleteFoodPanel.setVisible(true);
		viewFoodPanel.setVisible(false);
		updateFoodPanel.setVisible(false);
	}
	
	public void handleUpdatePanel() {
		addFoodPanel.setVisible(false);
		deleteFoodPanel.setVisible(false);
		viewFoodPanel.setVisible(false);
		updateFoodPanel.setVisible(true);
	}

	public void submitAddFood() {
		// ADD FOOD
		addFoodLabel.setText("");
		System.out.println("Adding food into the table...");
		model.addFood(name.getText(), Double.parseDouble(price.getText()));
		addFoodLabel.setText("food added");
	}

	// update food	
	public void submitUpdateFood() {

		updateLabel.setText("");
		model.updateFood(updateId.getText(), Double.parseDouble(updatePrice.getText()));
		updateLabel.setText("food price changed");
	}
	
	// delete food
	public void submitDeleteFood() {
		deleteLabel.setText("");
		model.deleteFood(deleteId.getText());
		deleteLabel.setText("food deleted");
	}

}
