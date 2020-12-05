package Toolbar;

import Main.Main;
import Main.ShapeAttribute;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * @see ToolBarController
 * 工具栏控制器
 * @version 1.0
 * @author 眭永熙
 */
public class ToolBarController {
    @FXML
    private Button PenBtn;
    @FXML
    private Button LineBtn;
    @FXML
    private Button OvalBtn;
    @FXML
    private Button RectBtn;
    @FXML
    private Button MoveBtn;
    @FXML
    private Button RubberBtn;
    @FXML
    private Button BarrelBtn;
    @FXML
    private Button CurveBtn;
    @FXML
    private Button TextBtn;
    @FXML
    private Slider SizeSlider;
    @FXML
    private Label label;
    @FXML
    private ColorPicker StrokeCP;
    @FXML
    private ColorPicker FillCP;
    @FXML
    private Button CircleBtn;


    private Main main;

    public ToolBarController() {
    }

    @FXML
    private void initialize() {
        SizeSlider.setValue(ShapeAttribute.getSize());
        label.setText(String.valueOf(ShapeAttribute.getSize()));
        StrokeCP.setValue(ShapeAttribute.getStrokeColor());
        FillCP.setValue(ShapeAttribute.getFillColor());
        PenBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("PEN");
            System.out.println(ShapeAttribute.getTool());
        });
        LineBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("LINE");
            System.out.println(ShapeAttribute.getTool());
        });
        OvalBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("OVAL");
            System.out.println(ShapeAttribute.getTool());
        });
        RectBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("RECT");
            System.out.println(ShapeAttribute.getTool());
        });
        MoveBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("MOVE");
            System.out.println(ShapeAttribute.getTool());
        });
        RubberBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("RUBBER");
            System.out.println(ShapeAttribute.getTool());
        });
        BarrelBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("BARREL");
            System.out.println(ShapeAttribute.getTool());
        });
        CurveBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("CURVE");
            System.out.println(ShapeAttribute.getTool());
        });
        TextBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("TEXT");
            System.out.println(ShapeAttribute.getTool());
        });
        CircleBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("CIRCLE");
            System.out.println(ShapeAttribute.getTool());
        });
        SizeSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            ShapeAttribute.setSize(SizeSlider.getValue());
            label.setText(String.valueOf(SizeSlider.getValue()));
        });
        StrokeCP.setOnAction((ActionEvent e) -> {
            ShapeAttribute.setStrokeColor(StrokeCP.getValue());
        });
        FillCP.setOnAction((ActionEvent e) -> {
            ShapeAttribute.setFillColor(FillCP.getValue());
        });
    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }


}
