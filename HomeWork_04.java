import java.util.Random;

class HashMap{
    class Entity{
        int key;
        int value;
    }
    class Basket{
        Node head;
        class Node{
            Entity entity;
            Node next;
        }

        public Integer find(int key){ // O(1)
            Node node = head;
            while(node != null){
                if(node.entity.key == key){
                    return node.entity.value;
                }
                node = node.next;
            }
            return null;
        }

        public boolean push(Entity entity){ // O(1)
            Node node = new Node();
            node.entity = entity;

            if(head == null){
                head = node;
            }else{
                if(head.entity.key == entity.key){
                    return false;
                }else {
                    Node cur = head;
                    while (cur.next != null) {
                        if (cur.next.entity.key == entity.key) {
                            return false;
                        }
                        cur = cur.next;
                    }
                    cur.next = node;
                }
            }
            return true;
        }

        public boolean remove(int key){ // O(1)
            if(head != null) {
                if (head.entity.key == key) {
                    head = head.next;
                    return true;
                } else {
                    Node cur = head;
                    while (cur.next != null) {
                        if (cur.next.entity.key == key) {
                            cur.next = cur.next.next;
                            return true;
                        }
                        cur = cur.next;
                    }
                }
            }
            return false;
        }
    }

    static final int INIT_SIZE = 16;

    Basket[] baskets;

    public HashMap(){
        this(INIT_SIZE);
    }

    public HashMap(int size){
        baskets = new Basket[size];
    }

    private int getIndex(int key){
        return key % baskets.length;
    }

    public Integer find(int key){ // O(1)
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket != null){
            return basket.find(key);
        }
        return null;
    }

    public boolean push(int key, int value){
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket == null){
            basket = new Basket();
            baskets[index] = basket;
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        return basket.push(entity);
    }

    public boolean remove(int key){
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket != null){
            return basket.remove(key);
        }
        return false;
    }
}

class Tree{
    Node root;
    class Node{
        int value;
        Node left;
        Node right;
        Color color;
    }
    private enum Color{
        RED, BLACK
    }
    public Node find(int value){
        return find(root, value);
    }

    private Node find(Node node, int value){
        if(node == null){
            return null;
        }
        if(node.value == value){
            return node;
        }
        if(node.value < value){
            return find(node.right, value);
        }else{
            return find(node.left, value);
        }
    }

    public void insert(int value){
        if(root == null){
            root = new Node();
            root.value = value;
        }else {
            insert(root, value);
            root = rebalance(root);
        }

        root.color = Color.BLACK;
    }

    public void insert(Node node, int value){
        if(node.value != value){
            if(node.value < value){
                if(node.right == null){
                    node.right = new Node();
                    node.right.value = value;
                    node.right.color = Color.RED;
                }else{
                    insert(node.right, value);
                    node.right = rebalance(node.right);
                    return;
                }
            }else{
                if(node.left == null){
                    node.left = new Node();
                    node.left.value = value;
                    node.left.color = Color.RED;
                }else{
                    insert(node.left, value);
                    node.left = rebalance(node.left);
                    return;
                }
            }
        }
    }
    private Node rebalance(Node node){
        Node result = node;
        boolean needRebalance;
        do {
            System.out.print("\033[H\033[J");
            printTree();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            needRebalance = false;
            if (result.right != null && result.right.color == Color.RED && 
                (result.left == null || result.left.color == Color.BLACK)) {
                    needRebalance = true;
                    result = rightSwap(result);
                }
            if (result.left != null && result.left.color == Color.RED &&
                result.left.left != null && result.left.left.color == Color.RED){
                    needRebalance = true;
                    result = leftSwap(result);
                }
            if (result.left != null && result.left.color == Color.RED &&
                result.right != null && result.right.color == Color.RED){
                    needRebalance = true;
                    colorSwap(result);
                }
        } while (needRebalance);
        return result;
    }


    private Node rightSwap(Node node){
        Node right = node.right;
        Node between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }
    private Node leftSwap(Node node){
        Node left = node.left;
        Node between = left.right;
        left.right = node;
        node.left = between;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }

    private void colorSwap(Node node){
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }




    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public void printLine(Node[] line){
        StringBuilder sb = new StringBuilder();
        int tab = (int)(128/(2*line.length));
        for (int i = 0; i < line.length; i++) {
            if (line[i] != null) {
                if (line[i].color == Color.RED){
                    sb.append(String.format(ANSI_RED + "%"+tab+"s"+"%"+tab+"s"  + ANSI_RESET,line[i].value,' '));
                } else {
                    sb.append(String.format("%"+tab+"s"+"%"+tab+"s", line[i].value,' '));
                }
            } else {
                sb.append(String.format("%"+tab+"s"+"%"+tab+"s", '.',' '));
            }
        }
        System.out.println(sb);
    }

    public void printTree(){
        int countLine = 1;
        Node line[] = new Node[(int)Math.pow(2, countLine-1)];
        boolean needPrint = false;
        if (root != null){
            line[0] = root;
            needPrint = true;
        }
        while (needPrint) {
            printLine(line);
            needPrint = false;
            countLine++;
            Node nextLine[] = new Node[(int)Math.pow(2, countLine-1)];
            for (int i = 0; i < line.length; i++) {
                if (line[i] != null) {
                    if (line[i].left != null) {
                        nextLine[i*2] = line[i].left;
                        needPrint = true;
                    }
                    if (line[i].right != null) {
                        nextLine[i*2+1] = line[i].right;
                        needPrint = true;
                    }
                }
            }
            line = nextLine;
        }
    }
}

public class HomeWork_04 {
    public static void main(String[] args) {
//        HashMap map = new HashMap();
//
//        map.push(1, 2);
//        map.push(3, 4);
//
//        System.out.println(map.find(3));
//        System.out.println(map.find(2));
//
//        map.remove(3);
//        map.push(2, 5);
//
//        System.out.println(map.find(3));
//        System.out.println(map.find(2));

        Tree tree = new Tree();
        Random rnd = new Random();
        // for(int i=1; i<=30; i++){
        //     tree.insert(rnd.nextInt(99));
        //     System.out.print("\033[H\033[J");
        //     tree.printTree();
        //     try {
        //         Thread.sleep(500);
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }
        for(int i=1; i<=50; i++){
            tree.insert(i);
            System.out.print("\033[H\033[J");
            tree.printTree();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}