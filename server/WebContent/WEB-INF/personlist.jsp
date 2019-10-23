<%@page import="de.linuxhotel.jf.persistence.Person"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table><table>
  <tr>
    <th>Vorname</th>
    <th>Nachname</th>
  </tr>
  <c:forEach items="${personlist}" var="p">
  <tr>
    <td>${p.vorname}</td>
    <td>${p.nachname}</td>
  </tr>
  </c:forEach>
</table>
