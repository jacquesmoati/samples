package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserProducer;

@WebServlet("/configureProducerServlet")
public class ConfigureProducerServlet extends HttpServlet {
	@Inject
	private UserProducer userProducer;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userProducer.setStartName('r');
		resp.getWriter().write("Set start name.");
	}
}
