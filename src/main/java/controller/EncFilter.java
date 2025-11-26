package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter({"*.jsp", "*.do"})
public class EncFilter extends HttpFilter implements Filter {
	
	private String encoding;
       
    public EncFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("[로그] 필터 메서드 호출됨");
		request.setCharacterEncoding(this.encoding);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("[로그] 서버가 시작될때 필터 초기화");
		this.encoding = "UTF-8";
	}

}
