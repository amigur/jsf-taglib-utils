jsf-taglib-utils
================

Is set of jsf facelets tag handlers to make the life with JSF easier.

1. Handlers supporting tag declaration/definition:

<ul>
  <li><b>&lt;tgu:compositeComponent&gt;</b></li>
  <li><b>&lt;tgu:interface&gt;</b></li>
  <li><b>&lt;tgu:tagParam&gt;</b></li>
  <li><b>&lt;tgu:implementation&gt;</b></li>
</ul>

The tag definition is similar to composite component but is done in plain facelet tag. Tag file consists 
of tag's parameters declaration and tag body definition. Attributes are mapped to the attribute variables 
adding  "__"  prefix to attribute name. This mapping guarantees that no attribute name from parent var mappers 
are propagated to child component by the &lt;ui:insert&gt; tag.

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
<ul>
  <li><b>&lt;tgu:ifExist&gt;</b></li>
  <li><b>&lt;tgu:eval&gt;</b></li>
  <li><b>&lt;tgu:setVar&gt;</b></li>
</ul>
