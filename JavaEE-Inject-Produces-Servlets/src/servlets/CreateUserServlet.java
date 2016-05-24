package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ent.User;

@WebServlet("/createUserServlet")
public class CreateUserServlet extends HttpServlet {
	@Inject
	private User user;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("resource")
		final PrintWriter writer = resp.getWriter();
		writer.write(user.getName());
	}
}
