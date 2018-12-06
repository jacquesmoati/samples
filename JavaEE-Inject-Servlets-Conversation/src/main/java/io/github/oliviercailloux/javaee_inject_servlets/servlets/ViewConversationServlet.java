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

import io.github.oliviercailloux.javaee_inject_servlets.service.Timestamp;

@SuppressWarnings("serial")
@WebServlet("/viewConversationServlet")
public class ViewConversationServlet extends HttpServlet {
	@Inject
	private Conversation conversation;

	@Inject
	private Timestamp t;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType(MediaType.TEXT_PLAIN);
		resp.setLocale(Locale.ENGLISH);

		@SuppressWarnings("resource")
		final ServletOutputStream out = resp.getOutputStream();
		out.println("Current: " + conversation.getId() + ".");
		out.println("Timestamp: " + t.asIso() + ".");
	}
}
