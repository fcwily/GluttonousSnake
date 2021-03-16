import java.util.LinkedList;

public class Snake {


    private LinkedList<Node> linkedList = null;

    Snake() {
        this.linkedList = new LinkedList<>();
        this.linkedList.addFirst(new Node(2, 0));
        this.linkedList.add(new Node(1, 0));
        this.linkedList.add(new Node(0, 0));
    }

    /**
     * @param x,y 坐标
     * @return 根据返回值来打印界面
     */
    public int getList(Integer x, Integer y) {
        for (int k = 0; k < linkedList.size(); k++) {
            if (k == 0 && linkedList.get(k).getX() == x && linkedList.get(k).getY() == y) {
                return 0;
            } else if (k != 0 && linkedList.get(k).getX() == x && linkedList.get(k).getY() == y)
                return 1;
        }

        return 2;
    }

    /**
     * 蛇的移动
     *
     * @param handle   操作字符串
     * @param foodNode 食物节点
     */
    public void move(String handle, Node foodNode) {
        //保存头部
        Node headNode = new Node(this.linkedList.getFirst().getX(), this.linkedList.getFirst().getY());
        //保存头部后面的第二个节点，用于调头判断
        Node secNode = this.linkedList.get(1);
         //大小写及长度判断
        if ("w".equalsIgnoreCase(handle)) {
            //边缘判断
            if (headNode.getY() == 0)
                return;
            //禁止调头
            if (secNode.getY() < headNode.getY())
                return;
            //判断下一步是否吃到食物
            if ((headNode.getY() == foodNode.getY() + 1) && (headNode.getX() == foodNode.getX())) {
                //将食物添加到头部节点
                this.linkedList.addFirst(foodNode);
                return;
            }
            //更新头部坐标
            headNode.setY(headNode.getY() - 1);
        } else if ("a".equalsIgnoreCase(handle)) {
            if (headNode.getX() == 0) return;
            if (secNode.getX() < headNode.getX()) return;
            if ((headNode.getX() == foodNode.getX() + 1) && (headNode.getY() == foodNode.getY())) {
                this.linkedList.addFirst(foodNode);
                return;
            }

            headNode.setX(headNode.getX() - 1);
        } else if ("s".equalsIgnoreCase(handle)) {

            if (headNode.getY() == Game.HEIGHT_MAX - 1) return;
            if (secNode.getY() > headNode.getY()) return;
            if ((headNode.getY() == foodNode.getY() - 1) && (headNode.getX() == foodNode.getX())) {
                this.linkedList.addFirst(foodNode);
                return;
            }
            headNode.setY(headNode.getY() + 1);
        } else if ("d".equalsIgnoreCase(handle)) {
            if (headNode.getX() == Game.WIDTH_MAX - 1) return;
            if (secNode.getX() > headNode.getX()) return;
            if ((headNode.getX() == foodNode.getX() - 1) && (headNode.getY() == foodNode.getY())) {
                this.linkedList.addFirst(foodNode);
                return;
            }
            headNode.setX(headNode.getX() + 1);
        }else {
            //其他非法输入
            return;
        }

        //更新移动后的坐标坐标
        for (int i = this.linkedList.size() - 1; i >= 0; i--) {
            //储存一个当前循环节点，从末尾开始
            Node nowNode = this.linkedList.get(i);
            if (i > 0) {
                Node beforeNode = this.linkedList.get(i - 1);
                nowNode.setPosition(beforeNode.getX(), beforeNode.getY());
            } else {
                nowNode = headNode;
            }
            this.linkedList.set(i, nowNode);
//            System.out.print(linkedList.get(i).getX());
//            System.out.print(linkedList.get(i).getY());
        }

    }

    public LinkedList<Node> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(LinkedList<Node> linkedList) {
        this.linkedList = linkedList;
    }


}
