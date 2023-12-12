package Ex01;

public interface Checked {
    default void checked(String str, RuntimeException e){
        if(str.isBlank() || str == null){
            throw e;
        }
    }
}
