package servlets;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// @WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("doGet()");

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/templates/member/login.jsp");
        rd.forward(req, resp);
    }

    // init() -> 서블릿 객체의 초기 작업 정의
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init()");
        String key1 = config.getInitParameter("key1");
        String key2 = config.getInitParameter("key2");
        System.out.println(key1);
        System.out.println(key2);
    }

    // destroy() -> 서블릿 객체의 자원 반납
    @Override
    public void destroy() {
        System.out.println("destroy()");
    }
}
