package controllers;

import common.CommonException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * controllers에 해당하는 범위
 * 톰캣 기본 에러
 */
@ControllerAdvice("controllers")
public class CommonController {
    /**
     * 응답코드를 내보내려면 response 객체가 필요함
     *
     */
    @ExceptionHandler({Exception.class})
    public String errorHandler(Exception e, Model model, HttpServletResponse response){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(e instanceof CommonException){
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus();
        }
        response.setStatus(status.value()); // 상태코드를 응답객체에 담아서 보낸다.
        e.printStackTrace();
        model.addAttribute("message", e.getMessage());
        return "error/common";
    }
}
