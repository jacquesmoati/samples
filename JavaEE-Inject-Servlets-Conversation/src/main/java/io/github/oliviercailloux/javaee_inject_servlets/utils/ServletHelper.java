package io.github.oliviercailloux.javaee_inject_servlets.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.javaee_inject_servlets.servlets.ViewConversationServlet;

@RequestScoped
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
