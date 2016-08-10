package io.github.oliviercailloux.javaee_inject_produces_servlets.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.javaee_inject_produces_servlets.model.User;

@WebServlet("/getUserServlet")
public class GetUserServlet extends HttpServlet {
	@Inject
	private User user;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("resource")
		final PrintWriter writer = resp.getWriter();
		writer.write(user.getName());
	}
}
