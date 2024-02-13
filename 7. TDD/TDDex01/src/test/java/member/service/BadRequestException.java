package member.service;

import java.net.http.HttpRequest;

public class BadRequestException extends RuntimeException {

    BadRequestException(){
    }
    public BadRequestException(String msg){
        super(msg);
    }

}
