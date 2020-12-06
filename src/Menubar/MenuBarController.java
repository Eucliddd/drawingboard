package Menubar;

import File.FileExport;
import File.FileInport;
import File.FileOpen;
import File.FileSave;
import App.App;
import Shape.MyCanvas;
import UndoManager.RecordStack;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import App.ShapeAttribute;
/**
 * @see MenuBarController
 * 菜单栏控制器
 * @version 2.0
 * @author 眭永熙
 */
public class MenuBarController {
    private App app;

    public MenuBarController() {
    }

    public void setMainApp(App appApp) {
        this.app = appApp;
    }

    @FXML
    private MenuItem saveItem;
    @FXML
    private MenuItem exportItem;
    @FXML
    private MenuItem openItem;
    @FXML
    private MenuItem inportItem;
    @FXML
    private MenuItem undoItem;
    @FXML
    private MenuItem clearItem;
    @FXML
    private MenuItem aboutItem;
/*    @FXML
    private MenuItem newItem;*/


    @FXML
    private void initialize() {
        saveItem.setOnAction(e -> {
            FileSave fileSave = new FileSave(app.getGroup());
            fileSave.save();
        });
        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        openItem.setOnAction(e -> {
            FileOpen fileOpen = new FileOpen(app.getGroup());
            fileOpen.open();
        });
        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        exportItem.setOnAction(e -> {
            FileExport fileExport = new FileExport(app.getGroup());
            fileExport.export();
        });
        exportItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+s"));
        inportItem.setOnAction(e -> {
            FileInport fileInport = new FileInport(app.getGroup());
            fileInport.inport();
        });
        inportItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+o"));
        undoItem.setOnAction(e -> RecordStack.undo());
        undoItem.setAccelerator(KeyCombination.keyCombination("Ctrl+z"));
        clearItem.setOnAction(e -> {
            while (RecordStack.undo()) ;
            if(app.getGroup().getChildren().size()>1){
                app.getGroup().getChildren().clear();
                MyCanvas canvas = new MyCanvas(ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
                gc.restore();
                canvas.setOnMouseClicked((ne) -> {
                    if (ShapeAttribute.getTool().equals("BARREL")) {
                        MyCanvas c = (MyCanvas) ne.getSource();
                        RecordStack.nodeChange(c, c.clone());
                        GraphicsContext ngc = (c.getGraphicsContext2D());
                        ngc.setFill(ShapeAttribute.getFillColor());
                        ngc.fillRect(0, 0, ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
                        ngc.restore();
                    }
                });
                app.getGroup().getChildren().addAll(canvas);
            }
        });
        clearItem.setAccelerator(KeyCombination.keyCombination("Ctrl+F5"));
        aboutItem.setOnAction(e -> {
            Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
            aboutAlert.setTitle("about");
            aboutAlert.setHeaderText("java大作业");
            aboutAlert.initStyle(StageStyle.UTILITY);
            aboutAlert.setContentText("东北大学：眭永熙 20184411， 刘荣江 20184539");
            aboutAlert.showAndWait();
        });
    }

}
