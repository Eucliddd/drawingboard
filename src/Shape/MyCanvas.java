package Shape;

import App.ShapeAttribute;
import UndoManager.RecordStack;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
/**
 * @see MyCanvas
 * 自定义画布类
 * @version 4.0
 * @author 眭永熙
 */
public class MyCanvas extends Canvas implements Cloneable {
    /**
     * 橡皮路径
     */
    private ArrayList<String> path;

    public MyCanvas(double width, double height) {
        super(width, height);
        path = new ArrayList<String>();
        getGraphicsContext2D().setFill(Color.TRANSPARENT);
    }

    /**
     * 新增将橡皮路径点
     * @param to 新的路径点
     */
    public void addPath(String to) {
        path.add(to);
    }

    /**
     * 重载克隆函数
     * @return 当前Node的克隆
     */
    @Override
    public Node clone() {
        MyCanvas clone = new MyCanvas(getWidth(), getHeight());
        GraphicsContext gc = clone.getGraphicsContext2D();
        gc.setLineWidth(getGraphicsContext2D().getLineWidth());
        gc.setFill(getGraphicsContext2D().getFill());
        gc.setStroke(getGraphicsContext2D().getStroke());
        gc.fillRect(0, 0, ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
        clone.setMouseTransparent(isMouseTransparent());
        clone.setOnMouseClicked(getOnMouseClicked());
        for (String str : path) {
            String[] pos = str.split(",");
            double x = Double.parseDouble(pos[0]), y = Double.parseDouble(pos[1]);
            gc.lineTo(x, y);
            gc.stroke();
        }

        return clone;
    }

    /**
     * 重载toString函数，用于保存信息持久化
     * @return 属性信息
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Canvas");
        sb.append(" ").append(getGraphicsContext2D().getLineWidth());
        for (String str : path) {
            sb.append(" ").append(str);
        }
        sb.append(" ").append("@").append(" ").append(getGraphicsContext2D().getFill())
                .append(" ").append(getGraphicsContext2D().getStroke());
        return sb.toString();
    }

    /**
     * 自定义画布创建器
     * @param myML Node信息数组
     * @return 创建的Node
     * @throws Exception 异常
     */
    public MyCanvas creator(String[] myML) throws Exception {
        if (myML[0].equals("Canvas")) {
            int index = 1;
            GraphicsContext gc = getGraphicsContext2D();
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(Double.parseDouble(myML[index++]));
            String str = myML[index];
            boolean isFirst = true;
            while (!str.equals("@")) {
                index++;
                String[] pos = str.split(",");
                double x = Double.parseDouble(pos[0]), y = Double.parseDouble(pos[1]);
                if (isFirst) {
                    isFirst = false;
                    path.clear();
                }
                path.add(str);
                gc.lineTo(x, y);
                gc.stroke();
                str = myML[index];
            }
            gc.setFill(Color.web(myML[index + 1]));
            gc.setStroke(Color.web(myML[index + 2]));
            gc.fillRect(0, 0, ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
            if (!path.isEmpty()) setMouseTransparent(true);
            else setOnMouseClicked((e) -> {
                if (ShapeAttribute.getTool().equals("BARREL")) {
                    MyCanvas c = (MyCanvas) e.getSource();
                    RecordStack.nodeChange(c, c.clone());
                    GraphicsContext ngc = (c.getGraphicsContext2D());
                    ngc.setFill(ShapeAttribute.getFillColor());
                    ngc.fillRect(0, 0, ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
                    ngc.restore();
                }
            });
            return this;
        } else {
            return null;
        }
    }

}
