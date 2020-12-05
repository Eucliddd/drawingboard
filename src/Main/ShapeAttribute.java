package Main;

import javafx.scene.paint.Color;

/**
 * @see ShapeAttribute
 * 画笔属性类
 * @version 1.0
 * @author 眭永熙
 */
public class ShapeAttribute {
    private static String tool = "PEN";
    private static Color StrokeColor = Color.BLACK;
    private static Color FillColor = Color.TRANSPARENT;
    private static double size = 1;
    private static String text = "";

    final public static int CANVAS_WIDTH = 818;
    final public static int CANVAS_HEIGHT = 600;

    public static String getTool() {
        return tool;
    }

    public static void setTool(String newTool) {
        tool = newTool;
    }

    public static Color getStrokeColor() {
        return StrokeColor;
    }

    public static void setSize(double size1) {
        size = size1;
    }

    public static double getSize() {
        return size;
    }

    public static void setStrokeColor(Color newColor) {
        StrokeColor = newColor;
    }

    public static Color getFillColor() {
        return FillColor;
    }

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
