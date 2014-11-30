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

```xml
<ui:composition>
  <tx:compositeComponent>
    <tx:interface>
      <tx:attribute name="value" default="Hello world!"/>
    </tx:interface>
    <tx:implementation>
      <h:outputValue  value="#{__value}"/>
    </tx:implementation>
  </tx:compositeComponent>
</ui:composition>
```

Following fragment from JSF page:

```xml
  <my:outputText/>;
```

shows "Hello world!" text. 

Tagext maps all declared atributtes(tag parameters) from the <b>interface</b> block to variables in the <b>implementation</b> block using prefix "__" for variable names. Original attributes are hidden in the implementation section. This approach guaratees that no tag parameter is propagated to children components (that is big problem in Mojarra facelets components).

More advanced example:

```xml
<pre><code>
<ui:composition>
  <tg:compositeComponent>
    <tx:interface>
      <tx:attribute name="action" isMethodParam="true"/>
      <tx:attribute name="actionListener"/>
      <tx:attribute name="id" required="true"/>
      <tx:attribute name="event"/>
      <tx:attribute name="update"/>
      <tx:attribute name="rendered" default="true"/>
      <tx:attribute name="style"/>
      <tx:attribute name="value"/>
    </tx:interface>
    <tx:implementation>
      <p:commandLink  id="#{__id}"
                 action="#{:invokeTagMethodExpression(__action)}"
                 update="#{__update}"
                 rendered="#{__rendered}"
                 style="#{__style}"
                 value="#{__value}">
        <tx:ifExist value="__event">
          <f:attribute name="event" value="#{__event}"/>
        </tx:ifExist>
        <tx:ifExist value="__actionListener">
          <f:attribute name="actionListener" value="#{__actionListener}"/>
        </tx:ifExist>
        <ui:insert/>
      </p:commandLink>
    </tx:implementation>
  </tx:compositeComponent>
</ui:composition>
```

2.Another handlers:
<ul>
  <li><b>&lt;tx:ifExist var="xyz"&gt;</b></li> checks if variable is defined. It is similar to &lt;c:if test="#{!empty xyz}&gt; that checks the emptiness of variable "xyz".  Unlike c:if the tx:ifExists checks if variable exists at all.
  <li><b>&lt;tx:eval&gt; expression="#{value_expression}"</b></li> Evaluates specified value expression.
  <li><b>&lt;tx:setVar&gt;</b></li>
</ul>
