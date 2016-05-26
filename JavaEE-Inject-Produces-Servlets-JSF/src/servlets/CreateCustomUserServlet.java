package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import model.User;
import service.UserProducer;

@WebServlet("/createCustomUserServlet")
@Transactional
public class CreateCustomUserServlet extends HttpServlet {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private Instance<User> userInstance;

	@Inject
	private UserProducer userProducer;

	/**
	 * <p>
	 * This method will sometimes produce Chomsky and sometimes Rawls.
	 * </p>
	 * <p>
	 * Try to find out why.
	 * </p>
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("resource")
		final PrintWriter writer = resp.getWriter();
		userProducer.setStartName('c');
		final User user = userInstance.get();
		writer.write(user.getName());
	}
}
