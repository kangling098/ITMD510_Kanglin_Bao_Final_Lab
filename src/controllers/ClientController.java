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
import javafx.scene.control.cell.PropertyValueFactory;
import models.ClientModel;
import food.Food;

public class ClientController {

	@FXML
	private TextField orderIds;
	@FXML
	private TableView<Food> viewFoodTable;
	
	@FXML
	private Label orderLabel;
	
	
	@FXML 
	private TableColumn pidColumn ;
    @FXML 
    private TableColumn nameColumn ;
    @FXML 
    private TableColumn priceColumn ;
    
    private ObservableList<Food> data;
	

	// Declare Admin model
    ClientModel model = null;
	Statement stmt = null;

	public ClientController() {
		model = new ClientModel();
//		viewFood();
	}
	
	public void viewFood() {
		ResultSet rs = model.getView();
		data = FXCollections.observableArrayList();
		
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
	
	// delete food
	public void orderFood() {
		String cuOrderIds = orderIds.getText();
		String[] idsArr = cuOrderIds.split(",");
		Double num = 0.0;
//		data.forEach(null);
		for(Food food: data) {
			for (String str: idsArr) {
				if (str.equals("" + food.getPid())) {
					num += food.getPrice();
				}
			}
		}
		orderLabel.setText("You should pay: " + num + "$");
	}

}
