package Shape;

import App.ShapeAttribute;
import UndoManager.RecordStack;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import static javafx.scene.Cursor.HAND;
import static javafx.scene.Cursor.MOVE;

/**
 * @see MyLine
 * 自定义直线类（可拖动、改变颜色、改变粗细）
 * @version 2.0
 * @author 眭永熙
 */
public class MyLine extends Line implements Cloneable {
    private double fromX, fromY, lastTranslateX, lastTranslateY;
    private String InitTool;

    /**
     * 无参构造函数
     * 设置图形的事件响应器
     */
    public MyLine() {
        super();
        setCursor(HAND);
        setOnMousePressed((MouseEvent e) -> {
            setCursor(MOVE);
            InitTool = ShapeAttribute.getTool();
            ShapeAttribute.setTool("MOVE");
            fromX = e.getSceneX();
            fromY = e.getSceneY();
            lastTranslateX = getTranslateX();
            lastTranslateY = getTranslateY();
            RecordStack.nodeChange(this, clone());

        });
        setOnMouseDragged((MouseEvent e) -> {
            double deltaX = e.getSceneX() - fromX;
            double deltaY = e.getSceneY() - fromY;
            setTranslateX(deltaX + lastTranslateX);
            setTranslateY(deltaY + lastTranslateY);
        });
        setOnMouseReleased((MouseEvent e) -> {
            setCursor(HAND);
            ShapeAttribute.setTool(InitTool);
        });
    }

    /**
     * 自定义直线画图器
     * @param x1 起点x
     * @param y1 起点y
     * @param x2 终点x
     * @param y2 终点y
     */
    public void paint(double x1, double y1, double x2, double y2) {
        setStrokeWidth(ShapeAttribute.getSize());
        setStroke(ShapeAttribute.getStrokeColor());
        setStartX(x1);
        setStartY(y1);
        setEndX(x2);
        setEndY(y2);
    }
    /**
     * 重载克隆函数
     * @return 当前Node的克隆
     */
    @Override
    public Node clone() {
        MyLine clone = new MyLine();
        clone.setStartX(getStartX());
        clone.setStartY(getStartY());
        clone.setEndX(getEndX());
        clone.setEndY(getEndY());
        clone.setTranslateX(lastTranslateX);
        clone.setTranslateY(lastTranslateY);
        clone.setStrokeWidth(getStrokeWidth());
        clone.setStroke(getStroke());
        return clone;
    }
    /**
     * 重载toString函数，用于保存信息持久化
     * @return 属性信息
     */
    @Override
    public String toString() {
        return new StringBuilder("Line")
                .append(" ").append(getStartX())
                .append(" ").append(getStartY())
                .append(" ").append(getEndX())
                .append(" ").append(getEndY())
                .append(" ").append(getTranslateX())
                .append(" ").append(getTranslateY())
                .append(" ").append(getStrokeWidth())
                .append(" ").append(getStroke())
                //.append(" ").append(Integer.toHexString(getFill().hashCode()))
                .toString();
    }
    /**
     * 创建器
     * @param myML Node信息数组
     * @return 创建的Node
     * @throws Exception 异常
     */
    public MyLine creator(String[] myML) throws Exception {
        if (myML[0].equals("Line")) {
            setStartX(Double.parseDouble(myML[1]));
            setStartY(Double.parseDouble(myML[2]));
            setEndX(Double.parseDouble(myML[3]));
            setEndY(Double.parseDouble(myML[4]));
            setTranslateX(Double.parseDouble(myML[5]));
            setTranslateY(Double.parseDouble(myML[6]));
            setStrokeWidth(Double.parseDouble(myML[7]));
            setStroke(Color.web(myML[8]));
            //setFill(Color.web(myML[9]));
            return this;
        } else return null;
    }
}
