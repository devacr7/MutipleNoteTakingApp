package com.dev;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SignupRegexFilter
 */
@WebFilter("/signup")
public class SignupRegexFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest servReq = (HttpServletRequest)request;
		HttpServletResponse servRes = (HttpServletResponse)response;
		HttpSession session = servReq.getSession();
		String password = servReq.getParameter("signupPass");
		
		// Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);
  
        // Return if the password
        // matched the ReGex
        if(!m.matches() || password.length() < 8) {
        	session.setAttribute("ErrorMsg", "Password should contains atleast 8 characters / atleast 1 of each number, lowercase, uppercase and special character");
			servRes.sendRedirect("Alert.jsp");
        }
        else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
        }
	}

}
