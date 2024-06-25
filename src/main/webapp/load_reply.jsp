<%@ page import="com.hkitchen.dao.ContactDao" %>
<%@ page import="com.hkitchen.entities.Contact" %>
<% 
int messageId = Integer.parseInt(request.getParameter("cId"));
//out.print(messageId);
ContactDao curr = new ContactDao();
Contact contact = new Contact();
contact = curr.getContactById(messageId);
//out.print(contact.getSubject());
%>

<form action="replySendUpdate" method="POST">
 Message ID: 
 <input name="id" value='<%= messageId %>'> </input> <br>
From Email ID: <%= contact.getFromEmail() %> <br>
To Email ID :<%= contact.getToEmail() %><br>
Subject : <%= contact.getSubject() %><br>
Message : <%= contact.getBody() %><br>
Reply Message: 
<input name="reply_msg" placeholder="enter reply here"> <br>
<button type="sumbit" name="submit"> Send</button>
</form>

