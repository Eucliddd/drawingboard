package UndoManager;

import javafx.scene.Node;

/**
 * @see Record
 * 操作记录类
 * @version 2.0
 * @author 刘荣江
 */
public class Record {
    /**
     * 当前结点
     */
    private Node node;
    /**
     * 旧结点
     */
    private Node oldNode;

    /**
     * 操作类型
     */
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

    /**
     * 获取{@link #node}当前结点
     * @return {@link #node}
     */
    public Node getNode() {
        return node;
    }
    /**
     * 获取{@link #oldNode}旧结点
     * @return {@link #oldNode}
     */
    public Node getOldNode() {
        return oldNode;
    }

    /**
     * 设置{@link #node}当前结点
     * @param nnd 结点
     */
    public void setNode(Node nnd) {
        node = nnd;
    }

    /**
     * 设置{@link #oldNode}旧结点
     * @param nnd 结点
     */
    public void setOldNode(Node nnd) {
        oldNode = nnd;
    }

    /**
     * 获取{@link #operation}
     * @return {@link #operation}
     */
    public Operation getOperation() {
        return operation;
    }

}
