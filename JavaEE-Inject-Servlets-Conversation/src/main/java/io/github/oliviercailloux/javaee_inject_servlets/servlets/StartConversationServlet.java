package io.github.oliviercailloux.javaee_inject_servlets.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

@WebServlet("/startConversationServlet")
public class StartConversationServlet extends HttpServlet {
	@Inject
	private Conversation conversation;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType(MediaType.TEXT_HTML);
		resp.setLocale(Locale.ENGLISH);

		conversation.begin();

		@SuppressWarnings("resource")
		final ServletOutputStream out = resp.getOutputStream();
		out.println("<html><body>");
		out.println("<p>Started: " + conversation.getId() + ".</p>");
		final String conversationUrl = "viewConversationServlet?cid=" + conversation.getId();
		out.println("<p>Check out <a href=\"" + conversationUrl + "\">that conversation</a>.</p>");
		out.println("</body></html>");
	}
}
