package org.jsf.facelets.tagutils;

import java.io.IOException;

import javax.el.VariableMapper;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;

import com.sun.faces.facelets.tag.TagHandlerImpl;

/**
 * The Class CompositeComponentHandler. Handler for tag <x:compositeComponent>
 */
public class CompositeComponentHandler extends TagHandlerImpl
{
	/**
	 * Instantiates a new composite component.
	 *
	 * @param config the config
	 */
	public CompositeComponentHandler(TagConfig config)
	{
		super(config);
	}

	/* (non-Javadoc)
	 * @see javax.faces.view.facelets.FaceletHandler#apply(javax.faces.view.facelets.FaceletContext, javax.faces.component.UIComponent)
	 */
	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException
	{
		VariableMapper overloadedVarMapper = ctx.getVariableMapper();
		
		try
		{
			CompositeVariableMapper compositeVarMapper = new CompositeVariableMapper(overloadedVarMapper);
			ctx.setVariableMapper(compositeVarMapper);
		
			this.nextHandler.apply(ctx, parent);
		}
		finally
		{
			ctx.setVariableMapper(overloadedVarMapper);
		}
	}

}
