jsf-taglib-utils
================

Is set of jsf facelets tag handlers to make the life with JSF easier.

1. Handlers supporting tag declaration/definition:

<ul>
  <li>&lt;tgu:compositeComponent&gt;</li>
  <li>&lt;tgu:interface&gt;</li>
  <li>&lt;tgu:tagParam&gt;</li>
  <li>&lt;tgu:implementation&gt;</li>
</ul>

The tag definition is similar to composite component but is done in plain facelet tag. Tag file consists 
of tag's parameters declaration and tag body definition. Attributes are mapped to the attribute variables 
adding  "__"  prefix to attribute name. This mapping guarantee that no attribute name from parent var mappers 
are propagated to child component.

Example : <p:commandLink> wrapper :

<pre><code>

&lt;ui:composition&gt;
  &lt;tgu:compositeComponent&gt;
    &lt;tgu:interface&gt;
      &lt;tgu:tagParam name="action" isMethodParam="true"/&gt;
      &lt;tgu:tagParam name="actionListener"/&gt;
      &lt;tgu:tagParam name="id" required="true"/&gt;
      &lt;tgu:tagParam name="event"/&gt;
      &lt;tgu:tagParam name="update"/&gt;
      &lt;tgu:tagParam name="rendered" default="true"/&gt;
      &lt;tgu:tagParam name="style"/&gt;
      &lt;tgu:tagParam name="value"/&gt;
    &lt;/tgu:interface&gt;

    &lt;tgu:implementation&gt;

      &lt;p:commandLink  id="#{__id}"
                 action="#{:invokeTagMethodExpression(__action)}"
                 update="#{__update}"
                 rendered="#{__rendered}"
                 style="#{__style}"
                 value="#{__value}"&gt;
        
        &lt;tgu:ifExist value="__event"&gt;
          &lt;f:attribute name="event" value="#{__event}"/&gt;
        &lt;/tgu:ifExist&gt;
      
        &lt;tgu:ifExist value="__actionListener"&gt;
          &lt;f:attribute name="actionListener" value="#{__actionListener}"/&gt;
        &lt;/tgu:ifExist&gt;

        &lt;ui:insert/&gt;

      &lt;/p:commandLink&gt;

    &lt;/tgu:implementation&gt;

  &lt;/tgu:compositeComponent&gt;

&lt;/ui:composition&gt;

</code></pre>

2.Other handlers:
  tgu:ifExist
  tgu:eval
  tgu:setVar


