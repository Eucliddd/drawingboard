package UndoManager;

import javafx.scene.Group;
import javafx.scene.Node;

import java.util.Stack;

/**
 * @see RecordStack
 * 操作记录栈类
 * @version 2.0
 * @author 刘荣江
 */
public class RecordStack {

    /**
     * 图形组
     */
    private static Group group;

    /**
     * 操作记录栈
     */
    private static Stack<Record> stack = new Stack<Record>();
    ;

    public RecordStack() {
        stack = new Stack<Record>();
    }

    public RecordStack(Group gp) {
        group = gp;
        stack = new Stack<Record>();
    }

    /**
     * 设置{@link #group}
     * @param gp group
     */
    public static void setGroup(Group gp) {
        group = gp;
    }

    /**
     * 操作记录压入栈
     * @param nd 压入的操作记录
     */
    protected static void push(Record nd) {
        stack.push(nd);
    }

    /**
     * 撤销一步操作
     * @return 是否撤销成功
     */
    public static boolean undo() {
        if (stack.isEmpty() || group.getChildren().isEmpty()) return false;
        if (stack.peek().getOperation() == Record.Operation.CREATE) {
            group.getChildren().removeAll(stack.pop().getNode());
            return true;
        } else if (stack.peek().getOperation() == Record.Operation.CHANGE) {
            Record rd = stack.pop();
            Node cur = rd.getNode(), pre = rd.getOldNode();
//            int index=group.getChildren().indexOf(cur);
//            group.getChildren().removeAll(cur);
            group.getChildren().replaceAll((Node nd) -> {
                if (nd == cur)
                    nd = pre;
                return nd;
            });
            stack.replaceAll((Record nrd) -> {
                if (nrd.getNode() == cur)
                    nrd.setNode(pre);
                return nrd;
            });

            return true;
        }
        return false;
    }

    /**
     * 创建结点操作压栈
     * @param cur 当前结点
     */
    public static void nodeCreate(Node cur) {
        Record record = new Record(cur, Record.Operation.CREATE, null);
        RecordStack.push(record);
    }

    /**
     * 改变结点操作压栈
     * @param cur 当前结点
     * @param pre 当前状态的当前节点的拷贝
     */
    public static void nodeChange(Node cur, Node pre) {
        Record record = new Record(cur, Record.Operation.CHANGE, pre);
        RecordStack.push(record);
    }

}
