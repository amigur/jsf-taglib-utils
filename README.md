tagwrap
================

Library similar to standard JSF composite API. Unlike the standard composite components it :
<ul>
  <li>Doesn't create a new component node in JSF component tree</li>
  <li>Enables to wrap a non-component tag as the &lt;p:ajax&gt; converters</li>
</ul>

The main purpose of tagwrap library is making wrappers around JSF components in the manner of JSF composite components. Originaly the library was developed as abstraction library to support migration from Richfaces to Primefaces. 

Tagwrap library defines 4 tags corresponding to composite tags:

<ul>
  <li><b>&lt;tgw:compositeComponent&gt;</b> no correspondent composite tag</li>
  <li><b>&lt;tgw:interface&gt;</b> ~ &lt;composite:interface&gt;</li>
  <li><b>&lt;tgw:attribute&gt;</b> ~ &lt;composite:attribute&gt;</li>
  <li><b>&lt;tgw:implementation&gt;</b> ~ &lt;composite:implementation&gt;</li>
</ul>

Simple example:

<pre><code>
&lt;ui:composition&gt;
  &lt;tgw:compositeComponent&gt;
    &lt;tgw:interface&gt;
      &lt;tgw:attribute name="value" default="Hello world!"/&gt;
    &lt;/tgw:interface&gt;
    &lt;tgw:implementation&gt;
      &lt;h:outputValue  value="#{__value}"/&gt;
    &lt;/tgw:implementation&gt;
  &lt;/tgw:compositeComponent&gt;
&lt;/ui:composition&gt;
</code></pre>

Using the following fragment on JSF page:

<pre><code>
  <xy:outputText/>
</code></pre>

will show "Hello world!". 


As can be seen in the example the library maps all declared atributtes(tag parameters) from <b>interface</b> block to variables in <b>implementation</b> block using prefix "__" for variable names. Original attribute names are hidden in the implementation section. It guaratees that no parameter is propagated to children components (that is big problem in standard JSF facelets components).

More advanced example:

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
