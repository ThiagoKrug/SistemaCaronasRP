/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class AuthChecker {
    
    public void authenticate(HttpSession session, HttpServletResponse response,
            String[] types) throws IOException {
        if (session.getAttribute("Username") == null) {
                response.sendRedirect("index.jsp");
            }
        boolean redir = true;
        for (String type: types) {
            if (session.getAttribute("Clearance").equals(type)) {
                redir = false;
            }
        }
        if (redir) {
            response.sendRedirect("index.jsp");
        }
    }
    
    public boolean authAjax(HttpSession session, String[] types, PrintWriter output) throws IOException {
        if (session.getAttribute("Username") == null) {
                output.print("Erro de autenticação");
                return false;
            }
        boolean redir = true;
        for (String type: types) {
            if (session.getAttribute("Clearance").equals(type)) {
                redir = false;
            }
        }
        if (redir) {
            output.print("Erro de autenticação");
            return false;
        }
        return true;
    }
    
}