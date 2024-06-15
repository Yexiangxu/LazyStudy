package lazyxu;

import org.junit.Test;

/**
 * User:Lazy_xu
 * Date:2024/04/16
 * Description:
 * FIXME
 */


public class GenericTest {

    public static class NodeA {

        private Object obj;

        public NodeA(Object obj) {
            this.obj = obj;
        }

    }

    public static class NodeB<T> {

        private T obj;

        public NodeB(T obj) {
            this.obj = obj;
        }

    }

    @Test
    public void main() {
        NodeA nodeA = new NodeA("业志陈");
        NodeB<String> nodeB = new NodeB<String>("业志陈");
        System.out.println(nodeA.obj);
    }

}



