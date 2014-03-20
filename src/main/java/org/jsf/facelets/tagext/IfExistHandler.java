package org.jsf.facelets.tagext;

import java.io.IOException;

import javax.el.ELException;
import javax.el.VariableMapper;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

/**
 * The Class IfExistHandler. checks if attributes in "value" property exists
 * 
 */
public class IfExistHandler extends TagHandler
{
	private final TagAttribute value;

	/**
	 * Instantiates a new tag wrapper handler.
	 * 
	 * @param config the config
	 */
	public IfExistHandler(TagConfig config)
	{
		super(config);
		this.value = this.getAttribute("value");
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException, ELException
	{
		VariableMapper varMapper = ctx.getVariableMapper();

		if (value != null)
		{
			String strValue = value.getValue();
			if (strValue != null && !strValue.isEmpty() && varMapper.resolveVariable(strValue) != null)
			{
				nextHandler.apply(ctx, parent);
			}
		}
	}

}
