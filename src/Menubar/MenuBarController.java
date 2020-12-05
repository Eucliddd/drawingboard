package Menubar;

import File.FileExport;
import File.FileInport;
import File.FileOpen;
import File.FileSave;
import Main.Main;
import UndoManager.RecordStack;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.StageStyle;

/**
 * @see MenuBarController
 * 菜单栏控制器
 * @version 1.0
 * @author 眭永熙
 */
public class MenuBarController {
    private Main main;

    public MenuBarController() {
    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
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
    @FXML
    private MenuItem newItem;


    @FXML
    private void initialize() {
        saveItem.setOnAction(e -> {
            FileSave fileSave = new FileSave(main.getGroup());
            fileSave.save();
        });
        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        openItem.setOnAction(e -> {
            FileOpen fileOpen = new FileOpen(main.getGroup());
            fileOpen.open();
        });
        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        exportItem.setOnAction(e -> {
            FileExport fileExport = new FileExport(main.getGroup());
            fileExport.export();
        });
        exportItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+s"));
        inportItem.setOnAction(e -> {
            FileInport fileInport = new FileInport(main.getGroup());
            fileInport.inport();
        });
        inportItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+o"));
        undoItem.setOnAction(e -> {
            RecordStack.undo();
        });
        undoItem.setAccelerator(KeyCombination.keyCombination("Ctrl+z"));
        clearItem.setOnAction(e -> {
            while (RecordStack.undo()) ;
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
/*        newItem.setOnAction(e->{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new Main().start(new Stage());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        });*/
    }

}
