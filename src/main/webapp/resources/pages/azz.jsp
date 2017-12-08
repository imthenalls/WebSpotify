<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
  <c:forEach varStatus="loop" items="${adList}" var="Ad">
    <div class="row">
    <div id="ad-no-pos">
          <div class="ad-container">
            <img id="adImg" src="${pageContext.request.contextPath}${Ad.imagePath}">
            <c:choose>
              <c:when test="${Ad.active}">
                
              </c:when>
              <c:otherwise>
                
              </c:otherwise>
            </c:choose>
            <button class="btn-primary">active</button>
          </div>
        </div>
  </c:forEach>
</div>
</div>
