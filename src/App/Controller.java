package App;

import Shape.*;
import UndoManager.RecordStack;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @see Controller
 * 画板控制器
 * @version 5.0
 * @author 眭永熙
 */
public class Controller {


    @FXML
    private Group group;
    @FXML
    private Label label;

    private App app;
    private double startX, startY;
    private double endX, endY;
    private boolean first = true;
    private GraphicsContext gc;
    private MyCanvas canvas;
    private MyPath path;
    private MyLine line;
    private MyRectangle rectangle;
    private MyEllipse ellipse;
    private MyQuadCurve quadCurve;
    private MyText text;
    private Node component;
    /*    private MyText cloneText;*/

    public Controller() {
    }

    public void setComponent(Node n) {
        component = n;
    }

    @FXML
    private void initialize() {
        if (canvas == null) {
            canvas = new MyCanvas(ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
            group.getChildren().add(canvas);
        }
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
        gc.restore();
        canvas.setOnMouseClicked((e) -> {
            if (ShapeAttribute.getTool().equals("BARREL")) {
                MyCanvas c = (MyCanvas) e.getSource();
                RecordStack.nodeChange(c, c.clone());
                gc = (c.getGraphicsContext2D());
                gc.setFill(ShapeAttribute.getFillColor());
                gc.fillRect(0, 0, ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
                gc.restore();
            }
        });
        label.setFont(Font.font(15));
    }

    public void setMainApp(App appApp) {
        this.app = appApp;
    }

    @FXML
    private void canvasOnMousePressed(MouseEvent event) {

        if (first) {
            startX = event.getX();
            startY = event.getY();
            String content = ShapeAttribute.getTool();
            switch (content) {
                case "LINE":
                    //path = new Path();
                    line = new MyLine();
                    line.paint(startX, startY, startX, startY);
                    group.getChildren().addAll(line);
                    RecordStack.nodeCreate(line);
                    break;
                case "CURVE":
                    if (!MyQuadCurve.isChangeControl()) {
//                        System.out.println("Group1");
                        quadCurve = new MyQuadCurve();
                        quadCurve.paint(startX, startY, startX, startY);
                        group.getChildren().addAll(quadCurve);
                        RecordStack.nodeCreate(quadCurve);
                    }
                    break;
                case "RECT":
                    //path = new Path();
                    rectangle = new MyRectangle();
                    rectangle.paint(startX, startY, startX, startY);
                    group.getChildren().addAll(rectangle);
                    RecordStack.nodeCreate(rectangle);
                    break;
                case "OVAL":
                case "CIRCLE":
                    ellipse = new MyEllipse();
                    ellipse.paint(startX, startY, startX, startY, false);
                    group.getChildren().addAll(ellipse);
                    RecordStack.nodeCreate(ellipse);
                    break;
                case "PEN":
                    path = new MyPath(startX, startY);
                    group.getChildren().addAll(path);
                    RecordStack.nodeCreate(path);
                    break;
                case "TEXT":
                    if (!MyText.isTexting()) {
                        text = new MyText(startX, startY, "Text");
                        group.getChildren().addAll(text);
                        RecordStack.nodeCreate(text);
                    }/*else if(event.getButton().equals(MouseButton.SECONDARY)){
                        try {
                            cloneText=(MyText) text.clone();
                        } catch (CloneNotSupportedException cloneNotSupportedException) {
                            cloneNotSupportedException.printStackTrace();
                        }
                    }*/
                    break;
                case "RUBBER":
                    canvas = new MyCanvas(ShapeAttribute.CANVAS_WIDTH, ShapeAttribute.CANVAS_HEIGHT);
                    canvas.setMouseTransparent(true);
                    group.getChildren().addAll(canvas);
                    gc = canvas.getGraphicsContext2D();
                    gc.setStroke(Color.WHITE);
                    gc.setLineWidth(ShapeAttribute.getSize());
                    RecordStack.nodeCreate(canvas);
                    break;
                case "BARREL":
                    break;
            }
            //path.getElements().add(new MoveTo(startX, startY));
            first = false;
        }
    }


    @FXML
    private void canvasOnMouseDragged(MouseEvent event) {
        //path.getElements().add(new LineTo(event.getX(), event.getY()));
        if (group.getChildren().get(0).contains(event.getX(), event.getY())) {
            switch (ShapeAttribute.getTool()) {
                case "LINE":
                    line.paint(startX, startY, event.getX(), event.getY());
                    break;
                case "CURVE":
                    quadCurve.paint(startX, startY, event.getX(), event.getY());
                    break;
                case "RECT":
                    rectangle.paint(startX, startY, event.getX(), event.getY());
                    break;
                case "OVAL":
                    ellipse.paint(startX, startY, event.getX(), event.getY(), false);
                    break;
                case "CIRCLE":
                    ellipse.paint(startX, startY, event.getX(), event.getY(), true);
                    break;
                case "PEN":
                    path.paint(event.getX(), event.getY());
                    break;
                case "TEXT":
                    boolean rotate = false;
                    if (event.getButton().equals(MouseButton.SECONDARY)) {
                        rotate = true;
                    }
//                    if (!group.getChildren().contains(text))
                        for (Node e : group.getChildren()) {
                            if (e.getClass().equals(MyText.class) && e.isFocused())
                                text = (MyText) e;
                        }
                    text.paint(event.getX(), event.getY(), rotate);
                    break;
                case "RUBBER":
                    gc.lineTo(event.getX(), event.getY());
                    canvas.addPath(event.getX() + "," + event.getY());
                    gc.stroke();
                    break;
            }

        }
    }

    @FXML
    private void canvasOnMouseReleased(MouseEvent event) {
        if (!first) {
            endX = event.getX();
            endY = event.getY();
            first = true;
        }
/*        if(ShapeAttribute.getTool().equals("TEXT")&&event.getButton().equals(MouseButton.SECONDARY)){
            Record rd=new Record(text, Record.Operation.CHANGE.setOldNode(cloneText));
            RecordStack.push(rd);
        }*/
    }

    @FXML
    private void onMouseMoved(MouseEvent event) {
        label.setText(String.format("%.1f, %.1fpx ", event.getX(), event.getY()));
    }

    public Group getGroup() {
        return group;
    }

}
