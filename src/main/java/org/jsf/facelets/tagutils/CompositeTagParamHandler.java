package org.jsf.facelets.tagutils;

import java.io.IOException;

import javax.el.ELException;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.el.VariableMapper;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagException;

import org.omnifaces.el.MethodExpressionValueExpressionAdapter;

import com.sun.faces.facelets.tag.TagHandlerImpl;

/**
 * The Class TagParamHandler. Hadler for <tgu:tagParam>
 */
public class CompositeTagParamHandler extends TagHandlerImpl
{
	private final TagAttribute name;
	private final TagAttribute required;
	private final TagAttribute propagate;
	private final TagAttribute defaultValue;
	private final TagAttribute deprecated;
	private final TagAttribute isMethodParam;

	/**
	 * Instantiates a new tag param handler.
	 *
	 * @param config the config
	 */
	public CompositeTagParamHandler(TagConfig config)
	{
		super(config);
		this.name = getRequiredAttribute("name");
		this.required = getAttribute("required");
		this.propagate = getAttribute("propagate");
		this.defaultValue = getAttribute("default");
		this.deprecated = getAttribute("deprecated");
		this.isMethodParam = getAttribute("isMethodParam");
	}

  /* (non-Javadoc)
	 * @see javax.faces.view.facelets.FaceletHandler#apply(javax.faces.view.facelets.FaceletContext, javax.faces.component.UIComponent)
	 */
	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException, TagException
	{
		VariableMapper varMapper = ctx.getVariableMapper(); 
		if (varMapper != null && varMapper instanceof CompositeVariableMapper)
		{
			CompositeVariableMapper compositeVarMapper = (CompositeVariableMapper) varMapper;

			String strName = this.name.getValue();
			
			ValueExpression valueExpression = compositeVarMapper.resolveOverloadedVariable(strName);
			
			if (valueExpression == null)
			{
				if (this.required != null && this.required.getBoolean(ctx))
				{
					throw new TagException(tag, "Attribute \"" + strName + "\" is required!");
				}
	
				if (defaultValue != null)
				{
					valueExpression = defaultValue.getValueExpression(ctx, Object.class);
				}
				else
				{
					valueExpression = null;
				}
			}
			else
			{
				if (this.deprecated != null && this.deprecated.getBoolean(ctx))
				{
					throw new TagException(tag, "Attribute \"" + strName + "\" is deprecated!");
				}
			}
			
			if (valueExpression != null && this.isMethodParam != null && this.isMethodParam.getBoolean(ctx))
			{
				// A method expression that wraps the value expression and uses its own invoke method to get the value from the wrapped expression.
				MethodExpression methodExpression = new MethodExpressionValueExpressionAdapter(valueExpression);

				// Using the variable mapper so the expression is scoped to the body of
				// the Facelets tag. Since the variable mapper only accepts
				// value expressions, we once again wrap it by a value expression that
				// directly returns the method expression.

				valueExpression = ctx.getExpressionFactory().createValueExpression(methodExpression, MethodExpression.class);
			}

			boolean propagate = (this.propagate != null && this.propagate.getBoolean(ctx));

			if (!compositeVarMapper.addParam(strName, valueExpression, propagate))
			{
				throw new TagException(tag, "Attribute duplicate declaration! Attribute name: " + strName);
			}
		}
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
