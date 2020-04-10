package homepage;

import datasource.DbConnect;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

public class editBudgetController implements Initializable {

    @FXML
    private TextField editRent;

    @FXML
    private TextField editDeductions;

    @FXML
    private TextField editGarbage;

    @FXML
    private TextField editElectricity;

    @FXML
    private TextField editGas;

    @FXML
    private TextField editShopping;

    @FXML
    private TextField editAirtime;

    @FXML
    private TextField editTv;

    @FXML
    private TextField editTransport;

    @FXML
    private TextField editFood;

    @FXML
    private TextField editEntertainment;

    @FXML
    private Label totalLabel;
    DbConnect dbConnect;
    
    @FXML
    private HomepageController homepageController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showTotals();
    }
    

    @FXML
    void handleClearButton() {

        this.editRent.setText(null);
        this.editDeductions.setText(null);
        this.editGarbage.setText(null);
        this.editElectricity.setText(null);
        this.editGas.setText(null);
        this.editShopping.setText(null);
        this.editAirtime.setText(null);
        this.editTv.setText(null);
        this.editTransport.setText(null);
        this.editFood.setText(null);
        this.editEntertainment.setText(null);
    }

    @FXML
    void handlePostButton(ActionEvent event) {

        updateBudget();
        handleClearButton();
    }

    @FXML
    public void showTotals() {

        DoubleProperty rent = new SimpleDoubleProperty();
        DoubleProperty deductions = new SimpleDoubleProperty();
        DoubleProperty garbage = new SimpleDoubleProperty();
        DoubleProperty electricity = new SimpleDoubleProperty();
        DoubleProperty gas = new SimpleDoubleProperty();
        DoubleProperty shopping = new SimpleDoubleProperty();
        DoubleProperty airtime = new SimpleDoubleProperty();
        DoubleProperty tv = new SimpleDoubleProperty();
        DoubleProperty transport = new SimpleDoubleProperty();
        DoubleProperty food = new SimpleDoubleProperty();
        DoubleProperty entertainment = new SimpleDoubleProperty();
        DoubleProperty total = new SimpleDoubleProperty();

        // can also use (add(rent, garbage))
        total.bind(rent.add(deductions.add(garbage.add(electricity.add(gas.add(shopping.add(airtime.add(tv.add(transport.add(food.add(entertainment)))))))))));

        StringConverter<? extends Number> stringConverter = new DoubleStringConverter();

        Bindings.bindBidirectional(editRent.textProperty(), rent, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editDeductions.textProperty(), deductions, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editGarbage.textProperty(), garbage, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editElectricity.textProperty(), electricity, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editGas.textProperty(), gas, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editShopping.textProperty(), shopping, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editAirtime.textProperty(), airtime, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editTv.textProperty(), tv, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editTransport.textProperty(), transport, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editFood.textProperty(), food, (StringConverter<Number>) stringConverter);
        Bindings.bindBidirectional(editEntertainment.textProperty(), entertainment, (StringConverter<Number>) stringConverter);

        // BInd total
        totalLabel.textProperty().bind(total.asString());

    }

    @FXML
    public void updateBudget() {

        String sqlinsert = "INSERT INTO budget(rent, deductions, garbage, electricity, gas, shopping, airtime, tv, transport, food, entertainment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection connection = dbConnect.getConnection();
            PreparedStatement insertPreparedStatement = connection.prepareStatement(sqlinsert);
            insertPreparedStatement.setString(1, this.editRent.getText());
            insertPreparedStatement.setString(2, this.editDeductions.getText());
            insertPreparedStatement.setString(3, this.editGarbage.getText());
            insertPreparedStatement.setString(4, this.editElectricity.getText());
            insertPreparedStatement.setString(5, this.editGas.getText());
            insertPreparedStatement.setString(6, this.editShopping.getText());
            insertPreparedStatement.setString(7, this.editAirtime.getText());
            insertPreparedStatement.setString(8, this.editTv.getText());
            insertPreparedStatement.setString(9, this.editTransport.getText());
            insertPreparedStatement.setString(10, this.editFood.getText());
            insertPreparedStatement.setString(11, this.editEntertainment.getText());

            insertPreparedStatement.execute();

            this.editRent.setText(null);
            this.editDeductions.setText(null);
            this.editGarbage.setText(null);
            this.editElectricity.setText(null);
            this.editGas.setText(null);
            this.editShopping.setText(null);
            this.editAirtime.setText(null);
            this.editTv.setText(null);
            this.editTransport.setText(null);
            this.editFood.setText(null);
            this.editEntertainment.setText(null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success!!");
            alert.showAndWait();
            
            connection.close();

        } catch (SQLException e) {

            System.out.println("updateBudget() " + e.getMessage());

        }
    }
}
