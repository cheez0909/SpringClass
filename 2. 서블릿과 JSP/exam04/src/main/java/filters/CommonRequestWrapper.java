package filters;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class CommonRequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @throws IllegalArgumentException if the request is null
     */
    public CommonRequestWrapper(HttpServletRequest request) {
        super(request);

        // 요청 시 공통 처리 부분
        request.setAttribute("siteConfig", "사이트 설정.....");
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);

        value = value == null ? value : value.toUpperCase();

        return value;
    }


}
