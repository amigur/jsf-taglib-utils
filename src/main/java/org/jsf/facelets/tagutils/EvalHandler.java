package org.jsf.facelets.tagutils;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import com.sun.faces.facelets.tag.TagHandlerImpl;

public class EvalHandler extends TagHandlerImpl
{
	private final TagAttribute expression;

	public EvalHandler(TagConfig config)
	{
		super(config);
		this.expression = getRequiredAttribute("expression");
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException
	{
		ValueExpression valueExpression = expression.getValueExpression(ctx, Object.class);
		valueExpression.getValue(ctx);
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
