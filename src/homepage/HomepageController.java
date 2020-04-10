package homepage;

import datasource.BudgetData;
import datasource.DbConnect;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomepageController implements Initializable {

    @FXML
    private Label dateDashboard;

    @FXML
    private Label cashDashboard;

    @FXML
    private Label bankDashboard;

    @FXML
    private Label mobileMoneyDashboard;

    @FXML
    private Label totalAssetsDashboard;

    @FXML
    private Label budgetedDashboard;

    @FXML
    private Label bCoverDashboard;
    
    @FXML
    private Label budgetCoverperDashboard;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField cashfield;

    @FXML
    private TextField mpesafield;

    @FXML
    private TextField bankfield;

    @FXML
    private Button postbutton;


    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private Label rentBudget;

    @FXML
    private Label deductionsBudget;

    @FXML
    private Label garbageBudget;

    @FXML
    private Label electricityBudget;

    @FXML
    private Label gasBudget;

    @FXML
    private Label entBudget;

    @FXML
    private Label shoppingBudget;

    @FXML
    private Label airtimeBudget;

    @FXML
    private Label tvBudget;

    @FXML
    private Label transportBudget;

    @FXML
    private Label foodBudget;
    
    @FXML
    private Label budgetTotalSet;

    private ObservableList<BudgetData> budgetDataObservableList;

    DbConnect dbConnect = DbConnect.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadBudgetData();
    }

    @FXML
    void handleEditBudget(ActionEvent event) {
        

        try {
            
            AnchorPane parent = (AnchorPane) FXMLLoader.load(getClass().getResource("editBudget.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("Update Budget");
            stage.setResizable(false);
            stage.show();
           
            
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("handleEditBudget(): " + ex.getMessage());
        }
    }
    
    
    public void loadBudgetData() {
        
        try {
            Connection connection = dbConnect.getConnection();
            String sqlselect = "SELECT * FROM budget WHERE ROWID = (SELECT max(ROWID) from budget)";
            System.out.println(sqlselect);
            ResultSet resultSet = connection.createStatement().executeQuery(sqlselect);

            budgetDataObservableList = FXCollections.observableArrayList();
            while (resultSet.next()) {

                budgetDataObservableList.add(new BudgetData(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), 
                resultSet.getString(10), resultSet.getString(11)));
            }

            connection.close();
        } catch (SQLException e) {

            System.out.println("loadBudgetData(): " + e.getMessage());
        }
        
        int IntRent = Integer.parseInt(budgetDataObservableList.get(0).getRent());
        int intDeductions = Integer.parseInt(budgetDataObservableList.get(0).getDeductions());
        int intGarbage = Integer.parseInt(budgetDataObservableList.get(0).getGarbage());
        var intElectricity = Integer.parseInt(budgetDataObservableList.get(0).getElectricity());
        int intGas = Integer.parseInt(budgetDataObservableList.get(0).getGas());
        int intShopping = Integer.parseInt(budgetDataObservableList.get(0).getShopping());
        int intAirtime = Integer.parseInt(budgetDataObservableList.get(0).getAirtime());
        int intTv = Integer.parseInt(budgetDataObservableList.get(0).getTv());
        int intTransport = Integer.parseInt(budgetDataObservableList.get(0).getTransport());
        int intFood = Integer.parseInt(budgetDataObservableList.get(0).getFood());
        int intEntertainment = Integer.parseInt(budgetDataObservableList.get(0).getEntertainment());
        
        int intTotal = IntRent + intDeductions + intGarbage + intElectricity + intGas + intShopping + intAirtime + intTv + intTransport + intFood + intEntertainment;
        String stringTotal = Integer.toString(intTotal);
        
       
        
        try {
            
           rentBudget.setText(budgetDataObservableList.get(0).getRent());
           deductionsBudget.setText(budgetDataObservableList.get(0).getDeductions());
           garbageBudget.setText(budgetDataObservableList.get(0).getGarbage());
           electricityBudget.setText(budgetDataObservableList.get(0).getElectricity());
           gasBudget.setText(budgetDataObservableList.get(0).getGas());
           shoppingBudget.setText(budgetDataObservableList.get(0).getShopping());
           airtimeBudget.setText(budgetDataObservableList.get(0).getAirtime());
           tvBudget.setText(budgetDataObservableList.get(0).getTv());
           transportBudget.setText(budgetDataObservableList.get(0).getTransport());
           foodBudget.setText(budgetDataObservableList.get(0).getFood());
           entBudget.setText(budgetDataObservableList.get(0).getEntertainment());
            
        } catch (Exception e) {

            System.out.println("setcellvalue: " + e.getMessage());
        }
    }
}
