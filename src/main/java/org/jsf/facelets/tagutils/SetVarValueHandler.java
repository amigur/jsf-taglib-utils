package org.jsf.facelets.tagutils;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import com.sun.faces.facelets.tag.TagHandlerImpl;

public class SetVarValueHandler extends TagHandlerImpl
{
	private final TagAttribute var;
	private final TagAttribute value;

	public SetVarValueHandler(TagConfig config)
	{
		super(config);
		this.var = getRequiredAttribute("var");
		this.value = getAttribute("value");
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException
	{
		ValueExpression valueExpression = value.getValueExpression(ctx, Object.class);
		Object valueValue = valueExpression.getValue(ctx);
		ctx.setAttribute(var.getValue(), valueValue);
	}

	/**
	 * Apply next handler.
	 *
	 * @param ctx the ctx
	 * @param c the c
	 */
	protected void applyNextHandler(FaceletContext ctx, UIComponent c)
	{
		// Swallow children - if they're text, we've already handled them.
	}
}
