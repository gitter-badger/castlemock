<%@ include file="../../../../../includes.jspf"%>
<%--
  ~ Copyright 2016 Karl Dahlgren
  ~  
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~   http://www.apache.org/licenses/LICENSE-2.0
  ~  
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<c:url var="delete_resources_url"  value="/web/wss/project/${webSocketStompProjectId}/application/${webSocketStompApplicationId}/resource/delete/confirm" />
<div class="content-top">
    <h1><spring:message code="websocket.stomp.deleteresources.header.deleteresources"/></h1>
</div>
<c:choose>
    <c:when test="${websocketstompResources.size() > 0}">
        <p><spring:message code="websocket.stomp.deleteresources.label.confirmation"/></p>
        <form:form action="${delete_resources_url}" method="POST" commandName="deleteWebSocketStompResourcesCommand">
            <ul>
                <c:forEach items="${websocketstompResources}" var="websocketstompResource" varStatus="loopStatus">
                    <li>${websocketstompResource.name}</li>
                    <form:hidden path="websocketstompResources[${loopStatus.index}].id" value="${websocketstompResource.id}"/>
                </c:forEach>
            </ul>

            <button class="button-error pure-button" type="submit"><i class="fa fa-trash"></i> <span><spring:message code="websocket.stomp.deleteresources.button.deleteresources"/></span></button>
            <a href="<c:url value="/web/wss/project/${webSocketStompProjectId}"/>" class="button-secondary pure-button"><i class="fa fa-times"></i> <spring:message code="websocket.stomp.deleteresources.button.cancel"/></a>
        </form:form>
    </c:when>
    <c:otherwise>
        <spring:message code="websocket.stomp.deleteresources.label.noresources"/>
        <p>
        <a href="<c:url value="/web/wss/project/${webSocketStompProjectId}"/>" class="button-secondary pure-button"><i class="fa fa-times"></i> <spring:message code="websocket.stomp.deleteresources.button.cancel"/></a>
        </p>
    </c:otherwise>
</c:choose>