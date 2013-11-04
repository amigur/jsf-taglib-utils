jsf-taglib-utils
================

Set of jsf facelets tag handlers to simplify tag component declaration/definition. 

Handlers supporting tag declaration/definition:

  tgu:compositeComponent
  tgu:interface
  tgu:tagParam
  tgu:implementation

Other handlers:
  tgu:ifExist
  tgu:eval
  tgu:setVar


Example : <p:commandLink> wrapper :


<ui:composition>
  <tgu:compositeComponent>
    <tgu:interface>
      <tgu:tagParam name="action" isMethodParam="true"/>
      <tgu:tagParam name="actionListener"/>
      <tgu:tagParam name="id" required="true"/>
      <tgu:tagParam name="event"/>
      <tgu:tagParam name="update"/>
      <tgu:tagParam name="rendered" default="true"/>
      <tgu:tagParam name="style"/>
      <tgu:tagParam name="value"/>
    </tgu:interface>

    <tgu:implementation>

      <p:commandLink  id="#{__id}"
                 action="#{:invokeTagMethodExpression(__action)}"
                 update="#{__update}"
                 rendered="#{__rendered}"
                 style="#{__style}"
                 value="#{__value}">
        
        <tgu:ifExist value="__event">
          <f:attribute name="event" value="#{__event}"/>
        </tgu:ifExist>
      
        <tgu:ifExist value="__actionListener">
          <f:attribute name="actionListener" value="#{__actionListener}"/>
        </tgu:ifExist>

        <ui:insert/>

      </p:commandLink>

    </tgu:implementation>

  </tgu:compositeComponent>

</ui:composition>
