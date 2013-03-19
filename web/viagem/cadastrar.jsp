<%--
    Document   : cadastrar
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.model.entity.SolicitacaoViagem"%><%@page import="com.mail.Mail"%><%@page import="com.convert.ViagemConverter"%><%@page import="com.model.entity.Viagem"%><%@page import="com.model.dao.ViagemDAO"%><%@page import="com.auth.AuthChecker"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8" %><%
    PrintWriter saida = response.getWriter();
    boolean auth = new AuthChecker().authAjax(session,
            new String[] {AuthChecker.ADMIN, AuthChecker.SERVIDOR}, saida);
    if (auth) {
        Connection connection = (Connection) request.getAttribute("connection");
        ViagemDAO vdao = new ViagemDAO(connection);
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                ViagemConverter vc = new ViagemConverter();
                Viagem viagem = (Viagem) vc.convertEntity(request);

                if (viagem.getIdViagem() == null || viagem.getIdViagem() <= 0) {
                    if (vdao.inserir(viagem) == 1) {
                        saida.print("Viagem cadastrada com sucesso.");
                    } else {
                        saida.print("Problemas ao criar viagem!");
                    }
                } else {
                    Viagem viagem2 = (Viagem)vdao.getById(viagem.getIdViagem());
                    if (vdao.alterar(viagem) == 1) {
                        Mail mail = new Mail();
                        if (!viagem.getVeiculo().getPlaca().equals(viagem2.getVeiculo().getPlaca())) {
                            
                            mail.sendmail(viagem2.getMotorista().getEmail(), Mail.CHARLIE_VICTOR_TEMPLATE);
                            for (SolicitacaoViagem sv: viagem2.getSolicitacoes(connection)) {
                                mail.sendmail(sv.getSolicitante().getEmail(), Mail.CHARLIE_VICTOR_TEMPLATE);
                            }
                        }
                        
                        if (!viagem.getPercurso().equals(viagem2.getPercurso())) {
                            
                            mail.sendmail(viagem2.getMotorista().getEmail(), Mail.CHARLIE_ROMEO_TEMPLATE);
                            for (SolicitacaoViagem sv: viagem2.getSolicitacoes(connection)) {
                                mail.sendmail(sv.getSolicitante().getEmail(), Mail.CHARLIE_ROMEO_TEMPLATE);
                            }
                        }
                        saida.print("Viagem atualizada com sucesso.");
                    } else {
                        saida.print("Problemas ao atualizar a viagem!");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                saida.print(e.getMessage());
            }
        }
    }
    saida.flush();
    saida.close();
%>