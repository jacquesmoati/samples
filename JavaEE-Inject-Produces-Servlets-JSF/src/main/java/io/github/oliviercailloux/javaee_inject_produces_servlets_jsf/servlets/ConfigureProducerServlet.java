package io.github.oliviercailloux.javaee_inject_produces_servlets_jsf.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.javaee_inject_produces_servlets_jsf.service.UserProducer;

@WebServlet("/configureProducerServlet")
public class ConfigureProducerServlet extends HttpServlet {
	@Inject
	private UserProducer userProducer;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final char startName = 'r';
		userProducer.setStartName(startName);
		resp.getWriter().write("Set start name: " + startName + ".");
	}
}