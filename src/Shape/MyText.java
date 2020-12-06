package Shape;

import App.ShapeAttribute;
import UndoManager.RecordStack;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static javafx.scene.Cursor.*;

/**
 * @see MyText
 * 自定义文本类（可输入、拖动、旋转、改变颜色、调节字号）
 * @version 5.0
 * @author 眭永熙
 */
public class MyText extends Text implements Cloneable {
    private double fromX, fromY, lastTranslateX, lastTranslateY;
    /**
     * {@link #isTexting()}
     */
    private static boolean texting = false;
    private static final double OFFSET = 15;
    /**
     * 构造函数
     * 设置图形的事件响应器
     * @param s 文本
     * @param x 初始位置x
     * @param y 初始位置y
     */
    public MyText(double x, double y, String s) {
        super(x, y, s);
        setCursor(HAND);
        setFill(ShapeAttribute.getFillColor());
        setFont(Font.font("Times New Roman", ShapeAttribute.getSize() + OFFSET));
        setPickOnBounds(true);
        setOnMousePressed((MouseEvent e) -> {
            if (ShapeAttribute.getTool().equals("MOVE")) {
                setCursor(MOVE);
                fromX = e.getSceneX();
                fromY = e.getSceneY();
                lastTranslateX = getTranslateX();
                lastTranslateY = getTranslateY();
                RecordStack.nodeChange(this, clone());
            } else if (ShapeAttribute.getTool().equals("TEXT")) {
                texting = true;
                //System.out.println("Texting");
                setUnderline(true);
                setFill(ShapeAttribute.getFillColor());
                setFont(Font.font("Times New Roman", ShapeAttribute.getSize() + OFFSET));
                requestFocus();
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
            if (ShapeAttribute.getTool().equals("TEXT") && e.getClickCount() == 2) {
                texting = true;
                //System.out.println("Texting");
                requestFocus();
                setCursor(TEXT);
//                setText("");
            }
        });
        setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.BACK_SPACE) {
                String content = getText();
                if (!content.equals(""))
                    setText(content.substring(0, content.length() - 1));
            } else if (e.getCode() != KeyCode.ENTER) {
                setText(getText() + e.getText());
            } else {
                setCursor(HAND);
                setUnderline(false);
                setFocused(false);
                getParent().requestFocus();
                texting = false;
            }
        });
        setOnKeyTyped((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                texting = false;
                setFocused(false);
                getParent().requestFocus();
            }
        });
    }

    /**
     * 是否正在输入
     * @return boolena
     */
    public static boolean isTexting() {
        return texting;
    }

    /**
     * 画图器
     * @param x 终点x
     * @param y 终点y
     * @param isRotate 是否旋转
     */
    public void paint(double x, double y, boolean isRotate) {
        double deltaX, deltaY;
        deltaX = x - getX();
        deltaY = y - getY();
        if (isRotate) {
            deltaX = deltaX - getWrappingWidth() / 2;
            double angle = Math.atan(deltaY / deltaX) * (180 / Math.PI);
            if (deltaX < 0) angle = angle + 180;
            setRotate(angle);
        } else {
            setWrappingWidth(deltaX);
        }
    }
    /**
     * 重载克隆函数
     * @return 当前Node的克隆
     */
    @Override
    public Node clone() {
        MyText mt = new MyText(getX(), getY(), getText());
        mt.setWrappingWidth(getWrappingWidth());
        mt.setTranslateX(lastTranslateX);
        mt.setTranslateY(lastTranslateY);
        mt.setRotate(getRotate());
        mt.setFont(Font.font("Times New Roman", getFont().getSize()));
        mt.setFill(getFill());
        return mt;
    }
    /**
     * 重载toString函数，用于保存信息持久化
     * @return 属性信息
     */
    @Override
    public String toString() {
        return new StringBuilder("Text")
                .append(" ").append(getX())
                .append(" ").append(getY())
                .append(" ").append(getWrappingWidth())
                .append(" ").append(getTranslateX())
                .append(" ").append(getTranslateY())
                .append(" ").append(getRotate())
                .append(" ").append(getFont().getSize())
                .append(" ").append(getFill())
                .append(" ").append(getText().strip().replace(' ', '|'))
                .toString();
    }

    /**
     * 创建器
     * @param myML Node信息数组
     * @return 创建的Node
     * @throws Exception 异常
     */
    public MyText creator(String[] myML) throws Exception {
        if (myML[0].equals("Text")) {
            setX(Double.parseDouble(myML[1]));
            setY(Double.parseDouble(myML[2]));
            setWrappingWidth(Double.parseDouble(myML[3]));
            setTranslateX(Double.parseDouble(myML[4]));
            setTranslateY(Double.parseDouble(myML[5]));
            setRotate(Double.parseDouble(myML[6]));
            setFont(Font.font("Times New Roman", Double.parseDouble(myML[7])));
            setFill(Color.web(myML[8]));
            setText(myML[9].replace('|', ' '));
            return this;
        } else return null;
    }

}
