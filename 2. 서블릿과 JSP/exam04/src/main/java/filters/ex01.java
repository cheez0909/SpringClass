package filters;

import jakarta.servlet.*;

import java.io.IOException;

public class ex01 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FilterEx1 - 요청 전");
        

        chain.doFilter(request, response);
        System.out.println("FilterEx1 - 요청 후");
    }
}
