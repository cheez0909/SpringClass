package controllers.admin;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Data
public class MemberSearch {
    /**
     * 날짜 형식을 설정해줘야함
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate sdate;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate edate;

}
