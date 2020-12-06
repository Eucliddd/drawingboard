package App;

import Menubar.MenuBarController;
import Toolbar.ToolBarController;
import UndoManager.RecordStack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @see App
 * 主函数类
 * @version 1.0
 * @author 眭永熙
 */

public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private RecordStack recordStack;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initRootLayout();
        RecordStack.setGroup(getGroup());
    }

    /**
     * 加载画板布局
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainstage.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Drawing Board");
            primaryStage.setScene(new Scene(root, 1024, 768));
            rootLayout = (BorderPane) root;
            Controller controller = loader.getController();
            controller.setMainApp(this);

            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../toolbar/toolbar.fxml"));
            root = loader.load();
            rootLayout.setLeft(root);
            ToolBarController TBController = loader.getController();
            TBController.setMainApp(this);

            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../menubar/menubar.fxml"));
            root = loader.load();
            rootLayout.setTop(root);
            MenuBarController MNController = loader.getController();
            MNController.setMainApp(this);

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Group getGroup() {
        return (Group) ((BorderPane) rootLayout.getCenter()).getCenter();
    }

    public RecordStack getRecordStack() {
        return recordStack;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
