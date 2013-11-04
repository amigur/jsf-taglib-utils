package org.jsf.facelets.tagutils;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;

import com.sun.faces.facelets.tag.TagHandlerImpl;

/**
 * The Class CompositeInterfaceHandler. <tgu:interface>
 */
public class CompositeInterfaceHandler extends TagHandlerImpl
{
	
	/**
	 * Instantiates a new interface handler.
	 *
	 * @param config the config
	 */
	public CompositeInterfaceHandler(TagConfig config)
	{
		super(config);
	}

	/* (non-Javadoc)
	 * @see javax.faces.view.facelets.FaceletHandler#apply(javax.faces.view.facelets.FaceletContext, javax.faces.component.UIComponent)
	 */
	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException
	{
		this.nextHandler.apply(ctx, parent);
	}
}
