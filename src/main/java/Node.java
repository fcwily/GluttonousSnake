public class Node {
    private Integer x;
    private Integer y;
    Node(){
        super();
    }
    Node(Integer x,Integer y){
        this.x=x;
        this.y=y;
    }

    public void setPosition(Integer x,Integer y){
        this.x=x;
        this.y=y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
