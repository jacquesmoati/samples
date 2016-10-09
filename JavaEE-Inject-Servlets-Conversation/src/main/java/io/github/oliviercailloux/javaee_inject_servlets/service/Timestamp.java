package io.github.oliviercailloux.javaee_inject_servlets.service;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import javax.enterprise.context.ConversationScoped;

@ConversationScoped
public class Timestamp implements Serializable {
	private final Instant t;

	public Timestamp() {
		t = Instant.now();
	}

	public String asIso() {
		return DateTimeFormatter.ISO_OFFSET_TIME.format(t.atOffset(ZoneOffset.UTC));
	}
}
