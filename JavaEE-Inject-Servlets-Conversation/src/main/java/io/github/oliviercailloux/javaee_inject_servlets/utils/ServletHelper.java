package io.github.oliviercailloux.javaee_inject_servlets.utils;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import io.github.oliviercailloux.javaee_inject_servlets.servlets.ViewConversationServlet;

@ApplicationScoped
public class ServletHelper {
	@Inject
	private ServletContext context;

	public String getViewConversationServletURL() {
		Collection<String> mappings = context.getServletRegistration(ViewConversationServlet.class.getCanonicalName())
				.getMappings();
		assert (mappings.size() == 1);
		final String urlMapping = mappings.iterator().next();
		assert (urlMapping.charAt(0) == '/');
		return urlMapping.substring(1);
	}

}
