package io.github.oliviercailloux.javaee_jpa_jsf.jsf_beans;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;

import com.google.common.collect.ImmutableList;

import io.github.oliviercailloux.javaee_jpa_jsf.service.ItemService;

@RequestScoped
@Named
public class ItemsSubsetRequest {
	/**
	 * 422 Unprocessable Entity error code (see <a
	 * href=https://tools.ietf.org/html/rfc4918#section-11.2>WebDAV</a>)
	 */
	private static final int UNPROCESSABLE = 422;

	@Min(0)
	private int end;

	@Inject
	private ItemService itemS;

	@Min(0)
	private int start;

	private List<String> subsetNames;

	public ItemsSubsetRequest() {
		subsetNames = ImmutableList.of("Non-populated");
		start = 0;
		end = Integer.MAX_VALUE;
	}

	public int getEnd() {
		return end;
	}

	public int getStart() {
		return start;
	}

	@Transactional
	public List<String> getSubsetNames() {
		return subsetNames;
	}

	public String populate() throws IOException {
		if (!(start <= end)) {
			FacesContext.getCurrentInstance().getExternalContext().responseSendError(UNPROCESSABLE,
					"Start must be ≤ end");
			return "navigation outcome will be ignored";
		}

		assert (start <= end);
		subsetNames = itemS.getSubsetNames(start, end);
		return null;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
