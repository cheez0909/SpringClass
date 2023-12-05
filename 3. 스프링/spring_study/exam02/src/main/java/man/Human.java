package man;

public class Human extends Animal{
    @Override
    public void move(){
        System.out.println("사람이 걷는다");
    }

    public void read(){
        System.out.println("책을 읽는다.");
    }

}
