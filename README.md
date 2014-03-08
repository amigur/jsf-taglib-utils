tagext
================

Library similar to standard JSF composite API. Unlike the standard composite components it:
<ul>
  <li>doesn't create a new component node in JSF component tree</li>
  <li>enables to wrap a non-component tag as the &lt;p:ajax&gt; or converters</li>
</ul>

The main purpose of tagext library is making wrappers around JSF components in the manner of JSF composite components does. Originaly the library was developed as abstraction library to support migration from Richfaces to Primefaces. 

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
  &lt;tgx:compositeComponent&gt;
    &lt;tgx:interface&gt;
      &lt;tgx:attribute name="value" default="Hello world!"/&gt;
    &lt;/tgx:interface&gt;
    &lt;tgx:implementation&gt;
      &lt;h:outputValue  value="#{__value}"/&gt;
    &lt;/tgx:implementation&gt;
  &lt;/tgx:compositeComponent&gt;
&lt;/ui:composition&gt;
</code></pre>

Following fragment in JSF page:

<pre><code>
  <my:outputText/>
</code></pre>

show "Hello world!" text. 


As can be seen in the example above the library maps all declared atributtes(tag parameters) from <b>interface</b> block to variables in <b>implementation</b> block using prefix "__" for variable names. Original attribute names are hidden in the implementation section. This approach guaratees that no tag parameter is propagated to children components (that is big problem in standard JSF facelets components).

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


2.Other handlers:
<ul>
  <li><b>&lt;tgw:ifExist var="xyz"&gt;</b></li> checks if variable is defined. It is similar to &lt;c:if test="#{!empty xyz}&gt; that checks the emptiness of varyable "xyz".
  <li><b>&lt;tgw:eval&gt; expression="#{value_expression}"</b></li> Evaluates specified value expression.
  <li><b>&lt;tgw:setVar&gt;</b></li>
</ul>
