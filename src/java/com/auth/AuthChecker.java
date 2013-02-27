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
    private String redDir = "";
    
    public AuthChecker() {
        redDir = "index.jsp";
    }
    
    public AuthChecker(String dir) {
        redDir = dir;
    }
    
    public void authenticate(HttpSession session, HttpServletResponse response,
            String[] types) throws IOException {
        if (session.getAttribute("Username") == null) {
                response.sendRedirect(redDir);
                return;
            }
        boolean redir = true;
        for (String type: types) {
            System.out.println(type);
            Object clearance = session.getAttribute("Clearance");
            if (clearance == null) {
                redir = true;
                break;
            }
            if (clearance.equals(type)) {
                redir = false;
            }
        }
        if (redir) {
            response.sendRedirect(redDir);
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