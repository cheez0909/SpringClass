package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class BoardServlet extends HelloServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html; charset=UTF-8");
//        resp.setCharacterEncoding("UTF-8");
        out.println("<form method='post' action ='/exam04/board'>");
        out.println("취미: <input type='checkbox' name='hobby' value='hobby1'>취미1<br>");
        out.println("<input type='checkbox' name='hobby' value='hobby2'>취미2<br>");
        out.println("<input type='checkbox' name='hobby' value='hobby3'>취미3<br>");
        out.println("<input type='checkbox' name='hobby' value='hobby4'>취미4<br>");
        out.println("제목:<input type='text' name='title'><br>");
        out.println("내용:<textarea name='content'></textarea><br>");
        out.println("<button type='subject'>작성하기</button>");
        out.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String[] hobby = req.getParameterValues("hobby");

        System.out.printf("title : %s, content : %s", title, content);
        System.out.printf(" hobby : %s", Arrays.toString(hobby));
    }
}
