package io.github.oliviercailloux.javaee_jpa_jsf.jsf_beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;

import com.google.common.collect.ImmutableList;

import io.github.oliviercailloux.javaee_jpa_jsf.service.ItemService;

@RequestScoped
@Named
public class ItemsSubsetRequest {
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

	public Validator getEndValidator() {
		return new Validator() {
			@Override
			public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
				final UIInput startCmp = (UIInput) context.getViewRoot().findComponent("start-param");
				final int startValue = ((Integer) startCmp.getValue()).intValue();
				final int endValue = ((Integer) value).intValue();
				if (!(startValue <= endValue)) {
					final FacesMessage message = new FacesMessage("Start must be ≤ end");
					throw new ValidatorException(message);
				}
			}
		};
	}

	public int getStart() {
		return start;
	}

	@Transactional
	public List<String> getSubsetNames() {
		return subsetNames;
	}

	public String populate() {
		assert (start <= end);
		subsetNames = itemS.getSubsetNames(start, end);
		/** Navigate action: stay in current page. */
		return null;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
