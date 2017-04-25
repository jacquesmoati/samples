package io.github.oliviercailloux.javaee_servlets_jsf.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.javaee_servlets_jsf.model.ConstantUser;
import io.github.oliviercailloux.javaee_servlets_jsf.utils.ServletHelper;

@WebServlet("/getConstantUserServlet")
public class GetConstantUserServlet extends HttpServlet {
	@Inject
	private ConstantUser user;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("resource")
		final ServletOutputStream writer = ServletHelper.configureAndGetOutputStream(resp);
		writer.println(user.getName());
	}
}
