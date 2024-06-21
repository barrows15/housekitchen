package com.hkitchen.tags;

import java.util.Calendar;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.TagSupport;

public class MyTagHandler extends TagSupport {

	public MyTagHandler() {
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();// returns the instance of JspWriter
		try {
			
			out.println(Calendar.getInstance().getTime());// printing date and time using JspWriter
			out.println("<h3>Welcome to Ashutosh Technology</h3>");
		} catch (Exception e) {
			System.out.println(e);
		}
		return SKIP_BODY;// will not evaluate the body content of the tag
	}

}
