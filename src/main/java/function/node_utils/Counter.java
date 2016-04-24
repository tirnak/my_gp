package function.node_utils;

import function.interfaces.Node;

/**
 * Created by kirill on 24.04.16.
 */
public class Counter {

    private Node root;
    private Integer num;

    public static Counter of(Node root) {
        return new Counter(root);
    }

    public Counter(Node root) {
        this.root = root;
    }

    public Counter has(int num) {
        this.num = num;
        return this;
    }

    public boolean childrenTyped(Class classToCount) {
        return num == countChildren(root, classToCount);
    }


    public static int countChildren(Node node, Class classToCount) {
        int count = 0;
        if (node.getClass() == classToCount) {
            ++count;
        }
        for (Node child : node.getChildren()) {
            count += countChildren(child, classToCount);
        }
        return count;
    }
}
