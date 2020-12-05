package Shape;

import Main.ShapeAttribute;
import UndoManager.RecordStack;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;

import static javafx.scene.Cursor.HAND;
import static javafx.scene.Cursor.MOVE;

/**
 * @see MyQuadCurve
 * 自定义曲线类（可拖动、改变颜色、改变粗细）
 * @version 2.0
 * @author 眭永熙
 */
public class MyQuadCurve extends QuadCurve implements Cloneable {
    private double fromX, fromY, lastTranslateX, lastTranslateY;
    private static boolean changeControl = false;

    public MyQuadCurve() {
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
            } else if (ShapeAttribute.getTool().equals("CURVE")) {
                changeControl = true;
                RecordStack.nodeChange(this, clone());
            }
        });
        setOnMouseDragged((MouseEvent e) -> {
            if (ShapeAttribute.getTool().equals("MOVE")) {
                double deltaX = e.getSceneX() - fromX;
                double deltaY = e.getSceneY() - fromY;
                setTranslateX(deltaX + lastTranslateX);
                setTranslateY(deltaY + lastTranslateY);
            } else if (ShapeAttribute.getTool().equals("CURVE")) {
                changeControl = true;
                setControlX(e.getX());
                setControlY(e.getY());
            }
        });
        setOnMouseReleased((MouseEvent e) -> {
            setCursor(HAND);
            changeControl = false;
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
        if (!changeControl) {
            setStrokeWidth(ShapeAttribute.getSize());
            setStroke(ShapeAttribute.getStrokeColor());
            setStartX(x1);
            setStartY(y1);
            setEndX(x2);
            setEndY(y2);
            setControlX((x1 + x2) / 2);
            setControlY((y1 + y2) / 2);
        }
    }

    public static boolean isChangeControl() {
        return changeControl;
    }

    @Override
    public Node clone() {
        MyQuadCurve clone = new MyQuadCurve();
        clone.setStartX(getStartX());
        clone.setStartY(getStartY());
        clone.setEndX(getEndX());
        clone.setEndY(getEndY());
        clone.setControlX(getControlX());
        clone.setControlY(getControlY());
        clone.setTranslateX(lastTranslateX);
        clone.setTranslateY(lastTranslateY);
        clone.setStrokeWidth(getStrokeWidth());
        clone.setStroke(getStroke());
        clone.setFill(getFill());
        return clone;
    }

    @Override
    public String toString() {
        return new StringBuilder("Curve")
                .append(" ").append(getStartX())
                .append(" ").append(getStartY())
                .append(" ").append(getEndX())
                .append(" ").append(getEndY())
                .append(" ").append(getControlX())
                .append(" ").append(getControlY())
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
    public MyQuadCurve creator(String[] myML) throws Exception {
        if (myML[0].equals("Curve")) {
            setStartX(Double.parseDouble(myML[1]));
            setStartY(Double.parseDouble(myML[2]));
            setEndX(Double.parseDouble(myML[3]));
            setEndY(Double.parseDouble(myML[4]));
            setControlX(Double.parseDouble(myML[5]));
            setControlY(Double.parseDouble(myML[6]));
            setTranslateX(Double.parseDouble(myML[7]));
            setTranslateY(Double.parseDouble(myML[8]));
            setStrokeWidth(Double.parseDouble(myML[9]));
            setStroke(Color.web(myML[10]));
            setFill(Color.web(myML[11]));
            return this;
        } else return null;
    }
}
