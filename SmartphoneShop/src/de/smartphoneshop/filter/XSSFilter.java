package de.smartphoneshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import de.smartphoneshop.wrapper.XSSRequestWrapper;

/**
 * @description Filter fÃ¼r das abfangen von XSS - aus den Folien vom Learnweb
 *              entnommen
 */
@WebFilter("/*")
public class XSSFilter implements Filter {

    /**
     * Default constructor.
     */
    public XSSFilter() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
	// TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	XSSRequestWrapper wrap = new XSSRequestWrapper((HttpServletRequest) request);
	chain.doFilter(wrap, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
	// TODO Auto-generated method stub
    }

}