package Toolbar;

import Main.App;
import Main.ShapeAttribute;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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


    private App app;

    public ToolBarController() {
    }

    @FXML
    private void initialize() {
        SizeSlider.setValue(ShapeAttribute.getSize());
        label.setText(String.valueOf(ShapeAttribute.getSize()));
        StrokeCP.setValue(ShapeAttribute.getStrokeColor());
        FillCP.setValue(ShapeAttribute.getFillColor());
        setIcon("icons/pen.png",PenBtn);
        PenBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("PEN");
            System.out.println(ShapeAttribute.getTool());
        });
        setIcon("icons/linear.png",LineBtn);
        LineBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("LINE");
            System.out.println(ShapeAttribute.getTool());
        });
        setIcon("icons/elliptic.png",OvalBtn);
        OvalBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("OVAL");
            System.out.println(ShapeAttribute.getTool());
        });
        setIcon("icons/rectangular.png",RectBtn);
        RectBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("RECT");
            System.out.println(ShapeAttribute.getTool());
        });
        setIcon("icons/move.png",MoveBtn);
        MoveBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("MOVE");
            System.out.println(ShapeAttribute.getTool());
        });
        setIcon("icons/eraser.png",RubberBtn);
        RubberBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("RUBBER");
            System.out.println(ShapeAttribute.getTool());
        });
        setIcon("icons/filling.png",BarrelBtn);
        BarrelBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("BARREL");
            System.out.println(ShapeAttribute.getTool());
        });
        setIcon("icons/curve.png",CurveBtn);
        CurveBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("CURVE");
            System.out.println(ShapeAttribute.getTool());
        });
        setIcon("icons/text.png",TextBtn);
        TextBtn.setOnMouseClicked((e) -> {
            ShapeAttribute.setTool("TEXT");
            System.out.println(ShapeAttribute.getTool());
        });
        setIcon("icons/circular.png",CircleBtn);
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

    public void setMainApp(App appApp) {
        this.app = appApp;
    }

    private void setIcon(String path,Button btn) {
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(44);
        imageView.setFitHeight(47);
        btn.setGraphic(imageView);
    }

}
