/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class AuthChecker {
    
    public void authenticate(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute("Username") == null) {
                response.sendRedirect("index.jsp");
            }
    }
    
}