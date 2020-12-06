package File;

import App.ShapeAttribute;
import Shape.*;
import UndoManager.RecordStack;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @see FileOpen
 * 可编辑文件打开类
 * @version 1.0
 * @author 眭永熙
 */
public class FileOpen {
    /**
     * 画图板的图形组
     */
    private Group openingGroup;

    public FileOpen() {
    }

    public FileOpen(Group gp) {
        openingGroup = gp;
    }

    /**
     * 设置组
     * @param gp 设置{@link #openingGroup}
     */
    public void setGroup(Group gp) {
        openingGroup = gp;
    }

    /**
     * 打开可编辑文件edit
     */
    public void open() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Edit", "*.edit"));
        fc.setTitle("Open");
        File file = fc.showOpenDialog(null);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            openingGroup.getChildren().clear();
            while (RecordStack.undo()) ;
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) continue;
                String[] attr = line.split("\\s+");
                Node newNode = createNode(attr);
                openingGroup.getChildren().addAll(newNode);
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    /**
     * 创建Node对象
     * @param arr 字符串数组保存node信息
     * @return 返回创建的Node地址
     * @throws Exception 异常
     */
    protected Node createNode(String[] arr) throws Exception {
        return switch (arr[0]) {
            case "Ellipse" -> new MyEllipse().creator(arr);
            case "Line" -> new MyLine().creator(arr);
            case "Path" -> new MyPath(0, 0).creator(arr);
            case "Curve" -> new MyQuadCurve().creator(arr);
            case "Rectangle" -> new MyRectangle().creator(arr);
            case "Text" -> new MyText(0, 0, "").creator(arr);
            case "Canvas" -> new MyCanvas(ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT).creator(arr);
            default -> null;
        };
    }
}
