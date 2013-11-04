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


EXample : <p:commandLink> wrapper :


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:ui="http://java.sun.com/jsf/facelets"
    xmlns:h="http://java.sun.com/jsf/html"
    xmlns:f="http://java.sun.com/jsf/core"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:tgu="http://jsf.tagutils.org"
    >

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

</html>

