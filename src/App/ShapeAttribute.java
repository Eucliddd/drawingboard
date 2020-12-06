package App;

import javafx.scene.paint.Color;

/**
 * @see ShapeAttribute
 * 画笔属性类
 * @version 1.0
 * @author 眭永熙
 */
public class ShapeAttribute {
    /**
     * 工具名称
     */
    private static String tool = "PEN";
    /**
     * 画笔颜色
     */
    private static Color StrokeColor = Color.BLACK;
    /**
     * 填充颜色
     */
    private static Color FillColor = Color.TRANSPARENT;
    /**
     * 画笔粗细
     */
    private static double size = 1;
    /**
     * 文字
     */
    private static String text = "";
    /**
     * 画布宽度
     */
    final public static int CANVAS_WIDTH = 818;
    /**
     * 画布高度
     */
    final public static int CANVAS_HEIGHT = 600;

    /**
     * 获取{@link #tool}工具名称
     * @return 工具名称
     */
    public static String getTool() {
        return tool;
    }

    /**
     * 设置{@link #tool}工具
     * @param newTool 工具名称
     */
    public static void setTool(String newTool) {
        tool = newTool;
    }

    /**
     * 获取{@link #StrokeColor}画笔颜色
     * @return 画笔颜色
     */
    public static Color getStrokeColor() {
        return StrokeColor;
    }

    /**
     * 设置{@link #size}画笔大小
     * @param size1 画笔大小
     */
    public static void setSize(double size1) {
        size = size1;
    }

    /**
     * 获取{@link #size}画笔大小
     * @return 画笔大小
     */
    public static double getSize() {
        return size;
    }

    /**
     * 设置{@link #StrokeColor}画笔颜色
     * @param newColor 画笔颜色
     */
    public static void setStrokeColor(Color newColor) {
        StrokeColor = newColor;
    }

    /**
     * 获取{@link #FillColor}填充颜色
     * @return 填充颜色
     */
    public static Color getFillColor() {
        return FillColor;
    }

    /**
     * 获取{@link #FillColor}填充颜色
     * @param newColor 填充颜色
     */
    public static void setFillColor(Color newColor) {
        FillColor = newColor;
    }

    public static String getText() {
        return text;
    }

    public static void setText(String t) {
        text = t;
    }
}
