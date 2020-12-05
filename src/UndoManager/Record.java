package UndoManager;

import javafx.scene.Node;

/**
 * @see Record
 * 操作记录类
 * @version 2.0
 * @author 刘荣江
 */
public class Record {
    private Node node;
    private Node oldNode;

    public enum Operation {
        /**创建操作*/
        CREATE,
        /**更改操作*/
        CHANGE;
    }

    private final Operation operation;

    public Record(Node nn, Operation no, Node ond) {
        node = nn;
        operation = no;
        oldNode = ond;
    }

    public Node getNode() {
        return node;
    }

    public Node getOldNode() {
        return oldNode;
    }

    public void setNode(Node nnd) {
        node = nnd;
    }

    public void setOldNode(Node nnd) {
        oldNode = nnd;
    }

    public Operation getOperation() {
        return operation;
    }

}
