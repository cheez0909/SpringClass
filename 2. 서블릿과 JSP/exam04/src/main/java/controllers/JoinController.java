package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// @WebServlet("/member/join")
public class JoinController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("title", "회원가입");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>forward 테스트 상단</h1>");

        RequestDispatcher rs = req.getRequestDispatcher("/WEB-INF/templates/member/join.jsp");
        // 버퍼를 대체한다.
        // /WEB-INF 폴더내부는 url로 직접 접근이 불가능함
        // rs.include(req, resp);
        rs.forward(req, resp);

        out.println("<h1>forward 테스트 하단</h1>");
    }
}
