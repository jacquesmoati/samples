package io.github.oliviercailloux.javaee_inject_servlets.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.javaee_inject_servlets.service.SayHelloService;
import io.github.oliviercailloux.javaee_inject_servlets.utils.ServletHelper;

@WebServlet("/sayHelloServlet")
public class SayHelloServlet extends HttpServlet {
	@Inject
	private SayHelloService sayHelloService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("resource")
		final ServletOutputStream out = ServletHelper.configureAndGetOutputStream(resp);
		final String greeting = sayHelloService.getHello();
		out.println(greeting);
	}
}
