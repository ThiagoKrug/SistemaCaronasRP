<%-- 
    Document   : index
    Created on : 08/02/2013, 15:22:44
    Author     : thiago
--%>

<%@page import="java.sql.Connection"%>
<%@page import="com.model.dao.PassageiroDAO"%>
<%@page import="com.model.entity.Passageiro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    Connection connection = (Connection) request.getAttribute("connection");
    PassageiroDAO pdao = new PassageiroDAO(connection);
    request.setAttribute("pdao", pdao);
%>
<layout:page title="Listagem de Passageiros" description="" keywords="">
<jsp:body>
        <h1>Passageiros</h1>
        <h4><a href="./passageiro/formulario.jsp">Cadastrar Passageiro</a></h4>
        <table>
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>RG</th>
                    <th>Telefone</th>
                    <th colspan="2">Opções</th>
                </tr>
            </thead>
            <tbody>
                <r:forEach var="passageiro" items="${pdao.getPassageiros()}">
                    <tr>
                        <td>${passageiro.getNome()}</td>
                        <td>${passageiro.getRg()}</td>
                        <td>${passageiro.getTelefone()}</td>
                        <td><a href="./passageiro/formulario.jsp?id_passageiro=${passageiro.getIdPassageiro()}">Editar</a></td>
                        <td><a href="" onclick="excluir(${passageiro.getIdPassageiro()});">Excluir</a></td>
                    </tr>
                </r:forEach>
            </tbody>
        </table>

        <script type="text/javascript">
                            function excluir(idPassageiro) {
                                event.preventDefault();

                                if (confirm("Deseja realmente excluir este passageiro?")) {
                                    /* Send the data using post */
                                    $.post("./passageiro/excluir.jsp",
                                            {id_passageiro: idPassageiro},
                                    function(data) {
                                        alert(data);
                                        console.log(data);
                                        window.location = "./passageiro/index.jsp";
                                        return;
                                    }
                                    );
                                }
                                ;
                            }
        </script>
    </jsp:body>
</layout:page>