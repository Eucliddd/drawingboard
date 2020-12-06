package File;

import App.ShapeAttribute;
import Shape.MyCanvas;
import UndoManager.RecordStack;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;

/**
 * @see FileInport
 * 图像导入类
 * @version 1.0
 * @author 眭永熙
 */


public class FileInport {
    private Group openingGroup;

    public FileInport() {
    }

    public FileInport(Group gp) {
        openingGroup = gp;
    }

    public void setGroup(Group gp) {
        openingGroup = gp;
    }

    /**
     * 导入png图片
     */
    public void inport() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", "*.png"));
        fc.setTitle("Inport");
        File file = fc.showOpenDialog(null);
        try {
            Image image = new Image(new FileInputStream(file));
            openingGroup.getChildren().clear();
            while (RecordStack.undo()) ;
            MyCanvas canvas = new MyCanvas(ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(image, 0, 0);
            openingGroup.getChildren().addAll(canvas);
        } catch (Exception e) {
/*            MyCanvas canvas = new MyCanvas(ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
            gc.restore();
            openingGroup.getChildren().addAll(canvas);*/
            //e.printStackTrace();
        }
    }

}
