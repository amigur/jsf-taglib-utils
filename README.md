tagwrap
================

Library similar to JSF composite API based on simple taghandlers.  Originaly the library was developed as abstraction library 
to support the richfaces to primefaces migration. 

The main purpose of tagwrap library is making wrappers around JSF components in the manner of JSF composite components. Unlike 
the standard composite components it doesn't create a new component node in JSF component tree and enables to wrap non-component tag as
the &lt;<p:ajax>&gt, converters etc. 

Tagwrap library defines 4 tags.  corresponding to jsf composite tags:

<ul>
  <li><b>&lt;tgw:compositeComponent&gt;</b> ~ </li>
  <li><b>&lt;tgw:interface&gt;</b> ~ &lt;composite:interface&gt;</li>
  <li><b>&lt;tgw:attribute&gt;</b> ~ &lt;composite:attribute&gt;</li>
  <li><b>&lt;tgw:implementation&gt;</b> ~ &lt;composite:implementation&gt;</li>
</ul>


Simple example:
<pre><code>
&lt;ui:composition&gt;
  &lt;tgw:compositeComponent&gt;
    &lt;tgw:interface&gt;
      &lt;tgw:attribute name="action" isMethodParam="true"/&gt;
      &lt;tgw:attribute name="actionListener"/&gt;
      &lt;tgw:attribute name="id" required="true"/&gt;
      &lt;tgw:attribute name="event"/&gt;
      &lt;tgw:attribute name="update"/&gt;
      &lt;tgw:attribute name="rendered" default="true"/&gt;
      &lt;tgw:attribute name="style"/&gt;
      &lt;tgw:attribute name="value"/&gt;
    &lt;/tgw:interface&gt;
    &lt;tgw:implementation&gt;
      &lt;p:commandLink  id="#{__id}"
                 action="#{:invokeTagMethodExpression(__action)}"
                 update="#{__update}"
                 rendered="#{__rendered}"
                 style="#{__style}"
                 value="#{__value}"&gt;
        &lt;tgw:ifExist value="__event"&gt;
          &lt;f:attribute name="event" value="#{__event}"/&gt;
        &lt;/tgw:ifExist&gt;
        &lt;tgw:ifExist value="__actionListener"&gt;
          &lt;f:attribute name="actionListener" value="#{__actionListener}"/&gt;
        &lt;/tgw:ifExist&gt;
        &lt;ui:insert/&gt;
      &lt;/p:commandLink&gt;
    &lt;/tgw:implementation&gt;
  &lt;/tgw:compositeComponent&gt;
&lt;/ui:composition&gt;
</code></pre>


Attributes names are mapped to variables names adding  prefix "__"  to the attribute name. 


This mapping guarantees that no attribute name 
from parent var mappers are propagated to child component by the &lt;ui:insert&gt; tag. Example : &lt;p:commandLink&gt; wrapper :
<pre><code>
&lt;ui:composition&gt;
  &lt;tgw:compositeComponent&gt;
    &lt;tgw:interface&gt;
      &lt;tgw:attribute name="action" isMethodParam="true"/&gt;
      &lt;tgw:attribute name="actionListener"/&gt;
      &lt;tgw:attribute name="id" required="true"/&gt;
      &lt;tgw:attribute name="event"/&gt;
      &lt;tgw:attribute name="update"/&gt;
      &lt;tgw:attribute name="rendered" default="true"/&gt;
      &lt;tgw:attribute name="style"/&gt;
      &lt;tgw:attribute name="value"/&gt;
    &lt;/tgw:interface&gt;
    &lt;tgw:implementation&gt;
      &lt;p:commandLink  id="#{__id}"
                 action="#{:invokeTagMethodExpression(__action)}"
                 update="#{__update}"
                 rendered="#{__rendered}"
                 style="#{__style}"
                 value="#{__value}"&gt;
        &lt;tgw:ifExist value="__event"&gt;
          &lt;f:attribute name="event" value="#{__event}"/&gt;
        &lt;/tgw:ifExist&gt;
        &lt;tgw:ifExist value="__actionListener"&gt;
          &lt;f:attribute name="actionListener" value="#{__actionListener}"/&gt;
        &lt;/tgw:ifExist&gt;
        &lt;ui:insert/&gt;
      &lt;/p:commandLink&gt;
    &lt;/tgw:implementation&gt;
  &lt;/tgw:compositeComponent&gt;
&lt;/ui:composition&gt;
</code></pre>

2.Other handlers:
<ul>
  <li><b>&lt;tgw:ifExist var="xyz"&gt;</b></li> checks if variable is defined. It is similar to &lt;c:if test="#{!empty xyz}&gt; that checks the emptiness of varyable "xyz".
  <li><b>&lt;tgw:eval&gt; expression="#{value_expression}"</b></li> Evaluates specified value expression.
  <li><b>&lt;tgw:setVar&gt;</b></li>
</ul>
