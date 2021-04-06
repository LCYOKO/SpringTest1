package test.collection;

import org.junit.Test;

/**
 * @Author l
 * @Date 2021/1/13 21:20
 * @Version 1.0
 */





/**
* @Author: lcy
* @Description //TODO
* @Date 14:47 2021/3/13
 *
 *
 * -----------------------------ArrayList--------------------------------
 * 1) arrayList是基于数组实现的，支持随机访问
 * 2）arrayList操作尾端元素，时间复杂度O1
 * 3）arrayList的insert操作，需要移动元素时间复杂度ON
 * 4）arrayList支持动态扩容，容量不足的时候会扩容1.5倍
 *-----------------------------LinkedList--------------------------------
 * 1）linkList是基于链表实现的，所以它有链表的特点 首尾两端操作时间复杂度O1
 * 2) search和insert方法都是ON，但是insert不需要移动元素
 * 3）linkedList同时实现了que和deque接口，一般用作于队列或栈
*/



public class MyLinkedList {
    Node head;
    Node tail;
    int size=0;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head=new Node(-1);
        tail=null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(!check(index)) return -1;
        Node p=head;
        while(index>=0){
            index--;
            p=p.next;
        }
        return p.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node=new Node(val);
        node.next=head.next;
        head.next=node;
        if(tail==null) tail=node;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if(size==0) addAtHead(val);
        System.out.println(tail==null);
        Node node=new Node(val);
        tail.next=node;
        size++;
        tail=tail.next;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index<0||index>size ) return;
        if(index==size) addAtTail(val);
        else {
            Node p=head;
            while(index>0){
                index--;
                p=p.next;
            }
            Node node=new Node(val);
            node.next=p.next;
            p.next=node;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(!check(index)) return ;
        if(index==size-1) deleteTail(index);
        else{
            Node p=head;
            while(index>0){
                index--;
                p=p.next;
            }
            p.next=p.next.next;
            size--;
        }
    }
    private void deleteTail(int index){
        size--;
        if(index==0){
            tail=null;
            head.next=null;
        }
        else{
            Node p=head;
            while(index>0){
                index--;
                p=p.next;
            }
            p.next=null;
            tail=p;
        }

    }
    private boolean check(int idx){
        if(idx<0||idx>=size) return false;
        return true;
    }
    class Node{
        int val;
        Node next;
        Node(int val){
            this.val=val;
        }
    }
     @Test
    public void test(){


         MyLinkedList linkedList = new MyLinkedList();
         linkedList.addAtHead(1);
         linkedList.addAtTail(3);
         linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
         linkedList.get(1);            //返回2
         linkedList.deleteAtIndex(1);  //现在链表是1-> 3
         linkedList.get(1);            //返回3

     }
}
