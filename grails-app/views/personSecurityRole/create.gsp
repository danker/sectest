

<%@ page import="com.test.PersonSecurityRole" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'personSecurityRole.label', default: 'PersonSecurityRole')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${personSecurityRoleInstance}">
            <div class="errors">
                <g:renderErrors bean="${personSecurityRoleInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="person"><g:message code="personSecurityRole.person.label" default="Person" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personSecurityRoleInstance, field: 'person', 'errors')}">
                                    <g:select name="person.id" from="${com.test.Person.list()}" optionKey="id" value="${personSecurityRoleInstance?.person?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="securityRole"><g:message code="personSecurityRole.securityRole.label" default="Security Role" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personSecurityRoleInstance, field: 'securityRole', 'errors')}">
                                    <g:select name="securityRole.id" from="${com.test.SecurityRole.list()}" optionKey="id" value="${personSecurityRoleInstance?.securityRole?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
