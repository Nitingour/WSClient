<h1>World Population/h1>
<%@page import="java.util.ArrayList,test.CountryBean" %>
<table border="1">
<tr><th>Rank</th><th>Country </th><th>Population</th><th>Flag</th></tr>
<%
ArrayList<CountryBean> list=(ArrayList<CountryBean>)request.getAttribute("LIST");
for(CountryBean c:list)
{
%>	
   <tr>
     <td><%=c.getRank() %></td>
   <td><%=c.getCountry()%></td>
   <td><%=c.getPopulation() %></td>
   <td><img src="<%=c.getFlag() %>" /></td>
   </tr>

<%
}
%>
</table>