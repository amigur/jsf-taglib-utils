package org.jsf.facelets.tagext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.el.ValueExpression;
import javax.el.VariableMapper;

/**
 * The Class CompositeVariableMapper.
 */
public class CompositeVariableMapper extends VariableMapper
{
	private VariableMapper overloadedVarMapper;
	private Set<String> hiddeParamMap;
	private Map<String, ValueExpression> compositeParamMap;

	/**
	 * Instantiates a new composite variable mapper.
	 *
	 * @param oldVarMapper the old var mapper
	 */
	public CompositeVariableMapper(VariableMapper overloadedVarMapper)
	{
		super();
		this.overloadedVarMapper = overloadedVarMapper;
		this.compositeParamMap = new HashMap<String, ValueExpression>();
		this.hiddeParamMap = new HashSet<>();
	}

	@Override
	public ValueExpression resolveVariable(String variable)
	{
		if (hiddeParamMap.contains(variable))
		{
			return null;
		}

		if (compositeParamMap.containsKey(variable))
		{
			return compositeParamMap.get(variable);
		}

		return overloadedVarMapper.resolveVariable(variable);

	}

	@Override
	public ValueExpression setVariable(String variable, ValueExpression expression)
	{
		if (compositeParamMap.containsKey(variable))
		{
			return compositeParamMap.put(variable, expression);
		}
		return overloadedVarMapper.setVariable(variable, expression);
	}

	/**
	 * Resolve overloaded variable.
	 *
	 * @param strName the str name
	 * @return the value expression
	 */
	public ValueExpression resolveOverloadedVariable(String strName)
	{
		return overloadedVarMapper.resolveVariable(strName);
	}

	/**
	 * Adds the variable.
	 *
	 * @param variableName the variable name
	 * @param value the value
	 * @param propagate the propagate to children
	 * @return true, if variable is added, false if already exists
	 */
	public boolean addParam(String variableName, ValueExpression value, boolean propagate)
	{
		String aliasName = "__" + variableName;
		if (!compositeParamMap.containsKey(aliasName))
		{
			compositeParamMap.put(aliasName, value);
			if (!propagate)
			{
				hiddeParamMap.add(variableName);
			}
			return true;
		}
		return false;
	}

}
