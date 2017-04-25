package io.github.oliviercailloux.javaee_servlets_jsf.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import io.github.oliviercailloux.javaee_servlets_jsf.model.VariableUser;
import io.github.oliviercailloux.javaee_servlets_jsf.utils.ServletHelper;

@WebServlet("/getVariableUserServlet")
public class GetVariableUserServlet extends HttpServlet {
	@SuppressWarnings("unused")
	static final Logger LOGGER = Logger.getLogger(GetVariableUserServlet.class.getCanonicalName());

	@Inject
	private VariableUser user;

	@SuppressWarnings("resource")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final ServletOutputStream writer = ServletHelper.configureAndGetOutputStream(resp);
		final String userId = req.getParameter("userId");
		final int intUserId;
		if (userId == null) {
			intUserId = 0;
		} else {
			try {
				intUserId = Integer.parseInt(userId);
			} catch (NumberFormatException e) {
				LOGGER.log(Level.FINE, "Error converting parameter userId.", e);
				resp.sendError(Status.BAD_REQUEST.getStatusCode(), "Invalid user id, integer expected.");
				return;
			}
		}
		user.setUserId(intUserId);
		writer.println(user.getName());
	}
}
