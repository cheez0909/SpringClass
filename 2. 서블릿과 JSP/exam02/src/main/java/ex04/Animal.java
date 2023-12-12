package ex04;

public record Animal(    String type,
                         String name,
                         String cry) {
    public Animal{
    if(cry.equals("야옹")){
        System.out.println("야옹이안녕");
    }
}
}