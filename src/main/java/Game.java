import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static final Integer HEIGHT_MAX = 10;
    public static final Integer WIDTH_MAX = 10;

    private static Node foodNode;

    public static void playingGame() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Snake snake = new Snake();
        //游戏开始前移动前长度
        Integer moveBeforeLen = snake.getLinkedList().size();
        //游戏开始前移动后长度
        Integer moveAfterLen = snake.getLinkedList().size() + 1;
        boolean isCreatFoodNode = true;
        while (true) {
            //如果移动后长度比移动前的长度大则需要创建新的食物
            isCreatFoodNode = moveAfterLen > moveBeforeLen;
            //每次操作后进行清空dos窗口
            clean();
            //更新游戏界面
            gameInterface(snake, isCreatFoodNode);
            //更新移动前的位置
            moveBeforeLen = snake.getLinkedList().size();

            System.out.println("输入移动方向(WASD):");
            String direction = sc.next();
            snake.move(direction, getFoodNode());
            moveAfterLen = snake.getLinkedList().size();
        }
    }

    public static void gameInterface(Snake snake, boolean isCreatRandNode) {

        Node tempFoodNode = createFoodNode(snake, isCreatRandNode);
        //是否需要刷新食物节点
        foodNode = tempFoodNode != null ? tempFoodNode : foodNode;
        boolean f = false;
        for (int y = 0; y < HEIGHT_MAX; y++) {
            for (int x = 0; x < WIDTH_MAX; x++) {
                int option = snake.getList(x, y);
                if (foodNode!=null&&(foodNode.getX() == x) && (foodNode.getY() == y)){
                    System.out.print("$ ");
                    continue;
                }
                switch (option) {
                    case 0:
                        System.out.print("# ");
                        continue;
                    case 1:
                        System.out.print("0 ");
                        continue;
                    case 2:
                        System.out.print("* ");
                        continue;

                }

            }
            System.out.println();
        }


    }

    //清空cmd控制台
    public static void clean() throws IOException, InterruptedException {
        new ProcessBuilder(new String[]{"cmd", "/c", "cls"}).inheritIO().start().waitFor();
    }

    public static Node getFoodNode() {
        return foodNode;
    }

    public static void setFoodNode(Node foodNode) {
        Game.foodNode = foodNode;
    }

    /**
     * 创建食物节点
     *
     * @param snake           传入蛇的位置
     * @param isCreatFoodNode 是否需要创建食物
     * @return 食物节点
     */
    public static Node createFoodNode(Snake snake, boolean isCreatFoodNode) {
        //判断食物节点是否被吃
        if (!isCreatFoodNode) return null;
        Random rand = new Random();
        boolean isRepeat = false;
        int foodX;
        int foodY;
        do {
            //获取伪随机坐标
            foodX = rand.nextInt(WIDTH_MAX.intValue());
            foodY = rand.nextInt(WIDTH_MAX.intValue());
            //循环判断是否食物坐标与蛇的节点重复
            for (Node node : snake.getLinkedList()) {
                if ((node.getX() == foodX) && (node.getY() == foodY)) {
                    isRepeat = true;
                    break;
                }
                isRepeat = false;
            }
        }
        while (isRepeat);

        return new Node(foodX, foodY);
    }


}
