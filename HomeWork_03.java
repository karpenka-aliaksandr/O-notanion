import java.util.Random;

class List {
    static Node head;

    static class Node {
        int value;
        Node next;
    }

    public static void pushFront(int value) {
        Node node = new Node();
        node.value = value;
        node.next = head;
        head = node;
    }

    public static void popFront() {
        if (head != null) {
            head = head.next;
        }
    }

    public static void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static boolean find(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public static void pushBack(int value) {
        Node node = new Node();
        node.value = value;

        if (head == null) {
            head = node;
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }

    }

    public static void popBack() {
        if (head != null) {
            if (head.next == null) {
                head = null;
            } else {
                Node cur = head;
                while (cur.next.next != null) {
                    cur = cur.next;
                }
                cur.next = null;
            }
        }
    }

}

class dList {
    static Node head;
    static Node tail;

    static class Node {
        int value;
        Node next;
        Node prev;
    }

    public static void pushFront(int value) {
        Node node = new Node();
        node.value = value;

        if (head == null) {
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
        }
        head = node;
    }

    public static void popFront() {
        if (head != null && head.next != null) {
            head = head.next;
        } else {
            tail = null;
            head = null;
        }
    }

    public void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static boolean find(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void pushBack(int value) {
        Node node = new Node();
        node.value = value;

        if (tail == null) {
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
        }
        tail = node;

    }

    public static void popBack() {
        if (tail != null && tail.prev != null) {
            tail = tail.prev;
            tail.next = null;
        } else {
            tail = null;
            head = null;
        }
    }

    public void sort() {
        boolean needSort = true;
        do {
            needSort = false;
            if (head != null) {
                Node node = head;
                while (node != null && node.next != null) {
                    if (node.value > node.next.value) {
                        Node before = node.prev;
                        Node cur = node;
                        Node next = node.next;
                        Node after = next.next;

                        cur.next = after;
                        cur.prev = next;
                        next.next = cur;
                        next.prev = before;

                        if (after != null) {
                            after.prev = cur;
                        } else {
                            tail = cur;
                        }

                        if (before != null) {
                            before.next = next;
                        } else {
                            head = next;
                        }

                        needSort = true;
                    }
                    node = node.next;
                }
            }
        } while (needSort);
    }

    public void reverse(){
        Node cur = head;
        if (cur != null) {
            while (cur.next != null) {     
                cur.prev = cur.next;
                cur = cur.next;
            }
            cur.prev = null;
            cur = head;
            cur.next = null;
            while (cur.prev != null) {
                cur.prev.next = cur;
                cur = cur.prev;
            }
            tail = head;
            head = cur;
        }
    }
}

public class HomeWork_03 {
    public static void main(String[] args) {
        dList list = new dList();
        Random rnd = new Random();
        for (int i = 1; i <= 4; i++)
            list.pushBack(rnd.nextInt(100));

        // list.popFront();
        // list.popFront();

        // list.print();

        // System.out.println(list.find(2));
        // System.out.println(list.find(5));

        // list.pushBack(7);

        // list.print();

        // list.popBack();

        list.print();
        list.reverse();
        list.print();

    }
}