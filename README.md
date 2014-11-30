tagext
================

Library similar to standard JSF composite API. It is based on Mojarra implementation, Myfaces are not supported. Unlike the standard composite components it:

<ul>
  <li>doesn't create a new component node in JSF component tree</li>
  <li>enables to wrap a non-component tag as the &lt;p:ajax&gt; or converters</li>
</ul>

The main purpose of Tagext library is making wrappers around JSF components in the same manner of JSF composite components does. Originaly the library was developed as an abstraction library to support migration from Richfaces to Primefaces. 

Tagwrap library defines 4 tags corresponding to composite tags:

<ul>
  <li><b>&lt;tx:compositeComponent&gt;</b> no correspondent composite tag</li>
  <li><b>&lt;tx:interface&gt;</b> ~ &lt;composite:interface&gt;</li>
  <li><b>&lt;tx:attribute&gt;</b> ~ &lt;composite:attribute&gt;</li>
  <li><b>&lt;tx:implementation&gt;</b> ~ &lt;composite:implementation&gt;</li>
</ul>

Simple example of custom <my:outputText> definition :

<pre><code>
&lt;ui:composition&gt;
  &lt;tx:compositeComponent&gt;
    &lt;tx:interface&gt;
      &lt;tx:attribute name="value" default="Hello world!"/&gt;
    &lt;/tx:interface&gt;
    &lt;tx:implementation&gt;
      &lt;h:outputValue  value="#{__value}"/&gt;
    &lt;/tx:implementation&gt;
  &lt;/tx:compositeComponent&gt;
&lt;/ui:composition&gt;
</code></pre>

Following fragment from JSF page:

<pre><code>
  &lt;my:outputText/&gt;
</code></pre>

shows "Hello world!" text. 

Tagext maps all declared atributtes(tag parameters) from the <b>interface</b> block to variables in the <b>implementation</b> block using prefix "__" for variable names. Original attributes are hidden in the implementation section. This approach guaratees that no tag parameter is propagated to children components (that is big problem in Mojarra facelets components).

More advanced example:

<pre><code>
&lt;ui:composition&gt;
  &lt;tg:compositeComponent&gt;
    &lt;tx:interface&gt;
      &lt;tx:attribute name="action" isMethodParam="true"/&gt;
      &lt;tx:attribute name="actionListener"/&gt;
      &lt;tx:attribute name="id" required="true"/&gt;
      &lt;tx:attribute name="event"/&gt;
      &lt;tx:attribute name="update"/&gt;
      &lt;tx:attribute name="rendered" default="true"/&gt;
      &lt;tx:attribute name="style"/&gt;
      &lt;tx:attribute name="value"/&gt;
    &lt;/tx:interface&gt;
    &lt;tx:implementation&gt;
      &lt;p:commandLink  id="#{__id}"
                 action="#{:invokeTagMethodExpression(__action)}"
                 update="#{__update}"
                 rendered="#{__rendered}"
                 style="#{__style}"
                 value="#{__value}"&gt;
        &lt;tx:ifExist value="__event"&gt;
          &lt;f:attribute name="event" value="#{__event}"/&gt;
        &lt;/tx:ifExist&gt;
        &lt;tx:ifExist value="__actionListener"&gt;
          &lt;f:attribute name="actionListener" value="#{__actionListener}"/&gt;
        &lt;/tx:ifExist&gt;
        &lt;ui:insert/&gt;
      &lt;/p:commandLink&gt;
    &lt;/tx:implementation&gt;
  &lt;/tx:compositeComponent&gt;
&lt;/ui:composition&gt;
</code></pre>


2.Another handlers:
<ul>
  <li><b>&lt;tx:ifExist var="xyz"&gt;</b></li> checks if variable is defined. It is similar to &lt;c:if test="#{!empty xyz}&gt; that checks the emptiness of variable "xyz".  Unlike c:if the tx:ifExists checks if variable exists at all.
  <li><b>&lt;tx:eval&gt; expression="#{value_expression}"</b></li> Evaluates specified value expression.
  <li><b>&lt;tx:setVar&gt;</b></li>
</ul>
