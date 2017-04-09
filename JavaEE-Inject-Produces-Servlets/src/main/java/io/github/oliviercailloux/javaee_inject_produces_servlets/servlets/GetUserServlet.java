package io.github.oliviercailloux.javaee_inject_produces_servlets.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.javaee_inject_produces_servlets.model.User;
import io.github.oliviercailloux.javaee_inject_produces_servlets.utils.ServletHelper;

@WebServlet("/getUserServlet")
public class GetUserServlet extends HttpServlet {
	@Inject
	private User user;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("resource")
		final ServletOutputStream writer = ServletHelper.configureAndGetOutputStream(resp);
		writer.println(user.getName());
	}
}
