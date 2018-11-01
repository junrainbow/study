package com.jun.study.algorithm.sort;

/**
 * @author junrainbow
 * @Description 数组实现链表
 * @Date:Create in 2018-10-30 11:37
 */
public class LinkTableForLink {


    public static HeadNode head;

    static{
        head = new HeadNode();
        head.startNode = null;
        head.endNode = null;
        head.size=0;
    }


    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //初始化一个链表
        System.out.print("初始化一个链表：");
        for (int i = 1; i <= 5; i++) {
            add(i);
        }
        //打印链表
        printLink();


        //查找第3个元素值
        int seq = 3;
        int value = getBySeq(seq);
        System.out.println(seq+"号位置的值："+value);
        System.out.println();


        //向位置3，插入新节点
        int insertValue = 100;
        System.out.print("向"+seq+"号位置插入，值为："+insertValue+"，插入后：");
        insertBeforeBySeq(3,100);
        //打印链表
        printLink();


        //删除3号位置
        System.out.print("删除"+seq+"位置，删除后：");
        deleteBySeq(seq);
        //打印链表
        printLink();

        //打印链表长度
        System.out.println("链表长度："+head.size);

    }

    /**
     * 打印链表元素
     */
    public static void printLink(){
        //打印初始化链表
        Node node = head.startNode;
        while(node!=null){
            System.out.print(node.value+" ");
            node = node.next;
        }
        System.out.println();
        System.out.println();
    }


    /**
     * 获取位置的值
     * @param seq
     */
    private static int getBySeq(int seq){
        //获取元素个数
        int size = head.size;
        //链表非空
        if(head.size==0){
            System.out.println("size个数："+size+",链表为空");
            throw new RuntimeException();
        }
        //是否越界
        if(seq<1||seq>size){
            System.out.println("size个数："+size+",seq："+seq);
            throw new RuntimeException();
        }
        //获取第一个节点值
        Node currentNode = null;
        if(seq==1){
            currentNode = head.startNode;
            return currentNode.value;
        }
        //引入二分法查找（当查找seq小于一半时，从前向后便利，否则由后向前遍历）
        if((size>>1)>=seq){
            currentNode = head.startNode;
            for (int i = 1; i < seq; i++) {
                currentNode = currentNode.next;
            }
        }else {
            currentNode = head.endNode;
            for (int i = size; i > seq; i--) {
                currentNode = currentNode.prev;
            }
        }
        //返回结果
        return currentNode.value;
    }

    /**
     * 添加元素
     * @param value
     */
    public static void add(int value){
        //构建节点
        Node node = new Node();
        node.value = value;
        //第一个节点
        if(head.size==0){
            head.startNode=node;
            head.endNode=node;
        }
        //非第一个节点
        if(head.size!=0){
            head.endNode.next = node;//维护原节点的后指针
            node.prev = head.endNode;//维护新节点的前指针
            head.endNode = node;//修改头节点的结束指针
        }
        //修改Head节点元素个数
        head.size+=1;
    }


    /**
     * 获取位置节点
     * @param seq
     */
    private static  Node getNode(int seq){
        //获取元素个数
        int size = head.size;
        //获取第一个节点值
        Node currentNode = null;
        if(seq==1){
            return head.startNode;
        }
        //引入二分法查找（当查找seq小于一半时，从前向后便利，否则由后向前遍历）
        if((size>>1)>=seq){
            currentNode = head.startNode;
            for (int i = 1; i < seq; i++) {
                currentNode = currentNode.next;
            }
        }else {
            currentNode = head.endNode;
            for (int i = size; i > seq; i--) {
                currentNode = currentNode.prev;
            }
        }
        //返回结果
        return currentNode;
    }


    /**
     * 插入元素到指定位置前面
     * @param seq
     */
    private static void insertBeforeBySeq(int seq,int value){
        //非空
        if(head.size==0){
            System.out.println("链表为空");
        }
        //越界
        if(seq<1||seq>head.size){
            System.out.println("链表为空");
        }
        //构建节点
        Node newNode = new Node();
        newNode.value = value;
        //目标节点
        Node targetNode = getNode(seq);
        //插入新节点(加塞儿)
        targetNode.prev.next = newNode;
        newNode.prev = targetNode.prev;
        newNode.next = targetNode;
        targetNode.prev = newNode;
        //修改Head节点元素个数
        head.size++;
    }


    /**
     * 根据位置，删除元素
     * @param seq
     */
    private static void deleteBySeq(int seq){
        //非空
        if(head.size==0){
            System.out.println("链表为空");
        }
        //越界
        if(seq<1||seq>head.size){
            System.out.println("链表为空");
        }
        //目标节点
        Node targetNode = getNode(seq);
        //删除节点
        targetNode.prev.next = targetNode.next;
        targetNode.next.prev = targetNode.prev;
        //维护Head节点属性
        head.size--;
    }


    /**
     * 节点机构
     */
    public static class Node{
        /**
         * 前一个节点
         */
        private Node prev;
        /**
         * 下一个节点
         */
        private Node next;
        /**
         * 当前值
         */
        private int value;

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * 头节点结构
     */
    public static class HeadNode{
        /**
         * 元素个数
         */
        private int size;
        /**
         * 起始节点
         */
        private Node startNode;
        /**
         * 结束节点
         */
        private Node endNode;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Node getStartNode() {
            return startNode;
        }

        public void setStartNode(Node startNode) {
            this.startNode = startNode;
        }

        public Node getEndNode() {
            return endNode;
        }

        public void setEndNode(Node endNode) {
            this.endNode = endNode;
        }
    }
}
