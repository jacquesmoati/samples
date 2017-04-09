package io.github.oliviercailloux.javaee_inject_produces_servlets_jsf.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

public class ServletHelper {
	static public ServletOutputStream configureAndGetOutputStream(HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType(MediaType.TEXT_PLAIN);
		resp.setLocale(Locale.ENGLISH);
		return resp.getOutputStream();
	}

}
