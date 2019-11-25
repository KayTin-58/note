package zhang.com;

import java.util.Map;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2019/11/22
 */
public class DAG {

    private final Integer v;
    private Node[] nodes;

    /**
     * 初始化DAG
     * 
     * @param v
     */
    public DAG(Integer v) {
        this.v = v;
        nodes = new Node[v];
        for (int i = 0; i < v; i++) {
            nodes[i] = new Node();
        }
    }



    /**
     * 节点信息
     */
    class Node {
        /**
         * 节点信息
         */
        private Object nodeInfo;
        /**
         * 边 key:领接点 value: 1、入边/出边
         */
        private Map<Node, Integer> edges;

    }
}
