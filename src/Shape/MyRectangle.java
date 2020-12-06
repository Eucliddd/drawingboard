package Shape;

import App.ShapeAttribute;
import UndoManager.RecordStack;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static javafx.scene.Cursor.HAND;
import static javafx.scene.Cursor.MOVE;

/**
 * @see MyRectangle
 * 自定义矩形类（可拖动、改变颜色、改变粗细）
 * @version 2.0
 * @author 眭永熙
 */
public class MyRectangle extends Rectangle implements Cloneable {
    private double fromX, fromY, lastTranslateX, lastTranslateY;

    public MyRectangle() {
        super();
        setCursor(HAND);
        setFill(ShapeAttribute.getFillColor());
        setStroke(ShapeAttribute.getStrokeColor());
        setOnMousePressed((MouseEvent e) -> {
            if (ShapeAttribute.getTool().equals("MOVE")) {
                setCursor(MOVE);
                fromX = e.getSceneX();
                fromY = e.getSceneY();
                lastTranslateX = getTranslateX();
                lastTranslateY = getTranslateY();
                RecordStack.nodeChange(this, clone());
            }
        });
        setOnMouseDragged((MouseEvent e) -> {
            if (ShapeAttribute.getTool().equals("MOVE")) {
                double deltaX = e.getSceneX() - fromX;
                double deltaY = e.getSceneY() - fromY;
                setTranslateX(deltaX + lastTranslateX);
                setTranslateY(deltaY + lastTranslateY);
            }
        });
        setOnMouseReleased((MouseEvent e) -> {
            setCursor(HAND);
        });
        setOnMouseClicked((MouseEvent e) -> {
            if (ShapeAttribute.getTool().equals("BARREL")) {
                RecordStack.nodeChange(this, clone());
                setFill(ShapeAttribute.getFillColor());
            }
        });
    }

    /**
     * 画图器
     * @param x1 起点x
     * @param y1 起点y
     * @param x2 终点x
     * @param y2 终点y
     */
    public void paint(double x1, double y1, double x2, double y2) {
        setStrokeWidth(ShapeAttribute.getSize());
        setStroke(ShapeAttribute.getStrokeColor());
        setFill(ShapeAttribute.getFillColor());
        double x = Math.min(x1, x2);
        double y = Math.min(y1, y2);
        setX(x);
        setY(y);
        setHeight(Math.abs(y1 - y2));
        setWidth(Math.abs(x1 - x2));
    }

    @Override
    public Node clone() {
        MyRectangle clone = new MyRectangle();
        clone.setX(getX());
        clone.setY(getY());
        clone.setWidth(getWidth());
        clone.setHeight(getHeight());
        clone.setTranslateX(lastTranslateX);
        clone.setTranslateY(lastTranslateY);
        clone.setStrokeWidth(getStrokeWidth());
        clone.setStroke(getStroke());
        clone.setFill(getFill());
        return clone;
    }

    @Override
    public String toString() {
        return new StringBuilder("Rectangle")
                .append(" ").append(getX())
                .append(" ").append(getY())
                .append(" ").append(getWidth())
                .append(" ").append(getHeight())
                .append(" ").append(getTranslateX())
                .append(" ").append(getTranslateY())
                .append(" ").append(getStrokeWidth())
                .append(" ").append(getStroke())
                .append(" ").append(getFill())
                .toString();
    }

    /**
     * 创建器
     * @param myML Node信息数组
     * @return 创建的Node
     * @throws Exception 异常
     */
    public MyRectangle creator(String[] myML) throws Exception {
        if (myML[0].equals("Rectangle")) {
            setX(Double.parseDouble(myML[1]));
            setY(Double.parseDouble(myML[2]));
            setWidth(Double.parseDouble(myML[3]));
            setHeight(Double.parseDouble(myML[4]));
            setTranslateX(Double.parseDouble(myML[5]));
            setTranslateY(Double.parseDouble(myML[6]));
            setStrokeWidth(Double.parseDouble(myML[7]));
            setStroke(Color.web(myML[8]));
            setFill(Color.web(myML[9]));
            return this;
        } else return null;
    }
}
