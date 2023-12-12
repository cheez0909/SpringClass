package Ex01;

public class BadRequestException extends RuntimeException{
    BadRequestException(String msg){
        super(msg);
    }
}
