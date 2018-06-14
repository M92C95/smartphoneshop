package de.smartphoneshop.wrapper;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @description XXS abfangen - diese Klasse wurde aus dem Learnwebfolien
 *              entnommen
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    public XSSRequestWrapper(HttpServletRequest request) {
	super(request);
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getParameter(String name) {
	String value = super.getParameter(name);
	return stripXSS(value);
    }

    @Override
    public String[] getParameterValues(String parameter) {
	String[] values = super.getParameterValues(parameter);

	if (values == null) {
	    return null;
	}

	int count = values.length;
	String[] encodedValues = new String[count];
	for (int i = 0; i < count; i++) {
	    encodedValues[i] = stripXSS(values[i]);
	}
	return encodedValues;
    }

    @Override
    public String getHeader(String name) {
	String value = super.getHeader(name);
	return stripXSS(value);
    }

    private String stripXSS(String value) {
	if (value != null) {
	    Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
	    value = scriptPattern.matcher(value).replaceAll(" ");
	    scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
	    value = scriptPattern.matcher(value).replaceAll(" ");
	    scriptPattern = Pattern.compile("<script>(.*?)>", Pattern.CASE_INSENSITIVE);
	    value = scriptPattern.matcher(value).replaceAll(" ");
	    scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
	    value = scriptPattern.matcher(value).replaceAll(" ");
	    scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE);
	    value = scriptPattern.matcher(value).replaceAll(" ");
	}
	return value;
    }
}
