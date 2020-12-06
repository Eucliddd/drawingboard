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

import java.io.*;

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

    /**
     * 保存选项卡
     */
    @FXML
    private MenuItem saveItem;
    /**
     * 导出选项卡
     */
    @FXML
    private MenuItem exportItem;
    /**
     * 打开选项卡
     */
    @FXML
    private MenuItem openItem;
    /**
     * 导入选项卡
     */
    @FXML
    private MenuItem inportItem;
    /**
     * 撤销选项卡
     */
    @FXML
    private MenuItem undoItem;
    /**
     * 重做选项卡
     */
    @FXML
    private MenuItem clearItem;
    /**
     * 关于选项卡
     */
    @FXML
    private MenuItem aboutItem;
    @FXML
    private MenuItem helpItem;


    /**
     * 初始化各选项卡的功能
     */
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
        helpItem.setOnAction(e -> {
            Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
            aboutAlert.setTitle("help");
            aboutAlert.setHeaderText("如何使用");
            aboutAlert.initStyle(StageStyle.UTILITY);
            File file=new File("src/helptext.txt");
            StringBuilder text= new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = br.readLine()) != null) {
                    assert false;
                    text.append(line).append('\n');
                }
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            assert false;
            aboutAlert.setContentText(text.toString());
            aboutAlert.showAndWait();
        });
    }

}
