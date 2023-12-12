package controllers.member;

/**
 * 데이터를 받을 수 있는 형태의 클래스 : record
 * 상수로 설정되기 때문에 값 변경이 불가함
 */
public record RequestLogin(String id, String pw) {
}
