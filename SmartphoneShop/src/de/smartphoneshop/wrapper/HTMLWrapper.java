package de.smartphoneshop.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @description HTMLWrapper
 */
public class HTMLWrapper extends HttpServletRequestWrapper {

    public HTMLWrapper(HttpServletRequest request) {
	super(request);
    }

    @Override
    public String getParameter(String name) {

	// Regular Expression - HTML Anweisung werden mit "" ersetzt
	return super.getParameter(name) == null ? "" : super.getParameter(name).replaceAll("<(.|\n)*?>", "");
    }
}
