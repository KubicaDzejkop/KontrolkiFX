package pl.gornik.kontrolkifx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Slider mySlider;

    @FXML
    private TextField txtSlider;

    @FXML
    private Circle myCircle;

    @FXML
    private ScrollBar myScroll;

    @FXML
    private TextField txtScroll;

    @FXML
    private Rectangle myRectangle;
    @FXML
    private CheckBox chbLeliwa;

    @FXML
    private CheckBox chbFM;

    @FXML
    private CheckBox chbZet;

    @FXML
    private CheckBox chbMax;

    @FXML
    private TextArea myArea;

    @FXML
    private ChoiceBox<String> myChoice;

    @FXML
    private ComboBox<String> myCombo;

    @FXML
    private Label lblChoice;

    @FXML
    private Label lblCombo;

    private String[] radio;

    @FXML
    private ListView<String> myList;

    @FXML
    private Spinner<Integer> spinMin;

    @FXML
    private Spinner<Integer> spinMax;

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblRandom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Slider
        mySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                txtSlider.setText(String.valueOf((int) mySlider.getValue() / 2) + "%");
                myCircle.setRadius(mySlider.getValue());
            }
        });
        txtSlider.setOnAction(event -> {
            try {
                mySlider.setValue(2 * Double.parseDouble(txtSlider.getText()));
            } catch (Exception e){
                System.out.println("To nie jest liczba");
            }

        });

        // ScrollBar
        myScroll.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                myRectangle.setLayoutY(20 + myScroll.getValue());
                txtScroll.setText(String.valueOf((int)myScroll.getValue()));
            }
        });
        txtScroll.setOnAction(event -> {
            myScroll.setValue(Double.parseDouble(txtScroll.getText()));
        });
        // CheckBox
        chbLeliwa.setOnAction(event -> addTextToAreaAndList(chbLeliwa));
        chbFM.setOnAction(event -> addTextToAreaAndList(chbFM));
        chbMax.setOnAction(event -> addTextToAreaAndList(chbMax));
        chbZet.setOnAction(event -> addTextToAreaAndList(chbZet));
//        1 sposob
        radio = new String[]{chbLeliwa.getText(), chbMax.getText(), chbFM.getText(), chbZet.getText()};
        myChoice.getItems().addAll(radio);

        myChoice.setOnAction(event -> lblChoice.setText(myChoice.getValue()));

//        2 sposob
//        myChoice.getItems().add(chbLeliwa.getText());
//        myChoice.getItems().add(chbZet.getText());
//        myChoice.getItems().add(chbFM.getText());
//        myChoice.getItems().add(chbMax.getText());

        myCombo.getItems().addAll(radio);
        myCombo.setOnAction(event -> lblCombo.setText(myCombo.getValue()));

        // Spinner
        SpinnerValueFactory<Integer> spinnerMin =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 150, 100);
        SpinnerValueFactory<Integer> spinnerMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 600, 500);

        spinMin.setValueFactory(spinnerMin);
        spinMax.setValueFactory(spinnerMax);

        spinMin.setOnMouseClicked(mouseEvent -> {
            lblInfo.setText("Losowanie liczb z zakresu " + spinnerMin.getValue() + " do " + spinnerMax.getValue());
        });

        spinMax.setOnMouseClicked(mouseEvent -> {
            lblInfo.setText("Losowanie liczb z zakresu " + spinnerMin.getValue() + " do " + spinnerMax.getValue());
        });

    }
    public void addTextToAreaAndList(CheckBox checkBox){
        if (checkBox.isSelected()){
            myArea.setText(myArea.getText() + checkBox.getText() +"\n");
            myList.getItems().add(checkBox.getText());
        }else{
            myList.getItems().remove(checkBox.getText());
        }
    }

}
