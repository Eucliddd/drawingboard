package Shape;

import App.ShapeAttribute;
import UndoManager.RecordStack;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static javafx.scene.Cursor.HAND;
import static javafx.scene.Cursor.MOVE;

/**
 * @see MyEllipse
 * 自定义椭圆类（可拖动、改变颜色、改变粗细）
 * @version 3.0
 * @author 眭永熙
 */
public class MyEllipse extends Ellipse implements Cloneable {
    private double fromX, fromY, lastTranslateX, lastTranslateY;

    /**
     * 无参构造函数
     * 设置图形的事件响应器
     */
    public MyEllipse() {
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
     * 自定义椭圆画图函数
     * @param x1 起点x
     * @param y1 起点y
     * @param x2 终点x
     * @param y2 终点y
     * @param isCircle 是否是画圆
     */
    public void paint(double x1, double y1, double x2, double y2, boolean isCircle) {
        setStrokeWidth(ShapeAttribute.getSize());
        setStroke(ShapeAttribute.getStrokeColor());
        setFill(ShapeAttribute.getFillColor());
        if (isCircle) {
            if (Math.abs(y2 - y1) > Math.abs(x2 - x1))
                x2 = y2 + x1 - y1;
            else
                y2 = x2 + y1 - x1;
        }
        setCenterX((x1 + x2) / 2);
        setCenterY((y1 + y2) / 2);
        setRadiusX(Math.abs(x1 - x2) / 2);
        setRadiusY(Math.abs(y1 - y2) / 2);
    }

    /**
     * 重载克隆函数
     * @return 当前Node的克隆
     */
    @Override
    public Node clone() {
        MyEllipse myClone = new MyEllipse();
        myClone.setCenterX(getCenterX());
        myClone.setCenterY(getCenterY());
        myClone.setRadiusX(getRadiusX());
        myClone.setRadiusY(getRadiusY());
        myClone.setTranslateX(lastTranslateX);
        myClone.setTranslateY(lastTranslateY);
        myClone.setStrokeWidth(getStrokeWidth());
        myClone.setStroke(getStroke());
        myClone.setFill(getFill());
        return myClone;
    }

    /**
     * 重载toString函数，用于保存信息持久化
     * @return 属性信息
     */
    @Override
    public String toString() {
        return new StringBuilder("Ellipse")
                .append(" ").append(getCenterX())
                .append(" ").append(getCenterY())
                .append(" ").append(getRadiusX())
                .append(" ").append(getRadiusY())
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
    public MyEllipse creator(String[] myML) throws Exception {
        if (myML[0].equals("Ellipse")) {
            setCenterX(Double.parseDouble(myML[1]));
            setCenterY(Double.parseDouble(myML[2]));
            setRadiusX(Double.parseDouble(myML[3]));
            setRadiusY(Double.parseDouble(myML[4]));
            setTranslateX(Double.parseDouble(myML[5]));
            setTranslateY(Double.parseDouble(myML[6]));
            setStrokeWidth(Double.parseDouble(myML[7]));
            setStroke(Color.web(myML[8]));
            setFill(Color.web(myML[9]));
            return this;
        } else return null;
    }
}
