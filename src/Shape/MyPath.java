package Shape;

import App.ShapeAttribute;
import UndoManager.RecordStack;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;

import static javafx.scene.Cursor.HAND;
import static javafx.scene.Cursor.MOVE;

/**
 * @see MyPath
 * 自定义画笔类（可拖动、改变颜色、改变粗细）
 * @version 2.0
 * @author 眭永熙
 */
public class MyPath extends Path implements Cloneable {
    private double fromX, fromY, lastTranslateX, lastTranslateY;

    public MyPath(double x, double y) {
        super();
        getElements().add(new MoveTo(x, y));
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
     * 画笔画图器
     * @param x 终点x
     * @param y 终点y
     */
    public void paint(double x, double y) {
        setStrokeWidth(ShapeAttribute.getSize());
        setStroke(ShapeAttribute.getStrokeColor());
        getElements().add(new LineTo(x, y));
    }

    @Override
    public Node clone() {
        MyPath clone = new MyPath(0, 0);
        clone.getElements().clear();
        clone.getElements().addAll(getElements());
        clone.setTranslateX(lastTranslateX);
        clone.setTranslateY(lastTranslateY);
        clone.setStrokeWidth(getStrokeWidth());
        clone.setStroke(getStroke());
        clone.setFill(getFill());
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PathElement e : getElements()) {
            if (e.getClass().getName().equals(MoveTo.class.getName())) {
                MoveTo mt = (MoveTo) e;
                sb.append(mt.getX()).append(",").append(mt.getY()).append(" ");
            } else if (e.getClass().getName().equals(LineTo.class.getName())) {
                LineTo lt = (LineTo) e;
                sb.append(lt.getX()).append(",").append(lt.getY()).append(" ");
            }
        }
        sb.append("@");

        return new StringBuilder("Path")
                .append(" ").append(sb.toString())
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
    public MyPath creator(String[] myML) throws Exception {
        if (myML[0].equals("Path")) {
            int index = 1;
            String str = myML[index];
            boolean isFirst = true;
            while (!str.equals("@")) {
                index++;
                String[] pos = str.split(",");
                double x = Double.parseDouble(pos[0]), y = Double.parseDouble(pos[1]);
                if (isFirst) {
                    isFirst = false;
                    getElements().clear();
                    getElements().add(new MoveTo(x, y));
                } else {
                    getElements().add(new LineTo(x, y));
                }
                str = myML[index];
            }
            setTranslateX(Double.parseDouble(myML[index + 1]));
            setTranslateY(Double.parseDouble(myML[index + 2]));
            setStrokeWidth(Double.parseDouble(myML[index + 3]));
            setStroke(Color.web(myML[index + 4]));
            setFill(Color.web(myML[index + 5]));
            return this;
        } else {
            return null;
        }
    }

}
