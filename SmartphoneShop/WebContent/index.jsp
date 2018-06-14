<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SmartphoneShop</title>
</head>
<body>

<jsp:include page="navigation.jsp"></jsp:include>
<div class="row">

				<div class="col-lg-12">
					<h1 class="page-header">Aktuelle Alben</h1>
				</div>

				<c:forEach items="${ ARTIKEL_LISTE }" var="artikel" begin="0" end="11">
					<c:url var="trackAnsichtLink" value="ArtikelServlet">
						<c:param name="command" value="TRACK_ANSICHT" />
						<c:param name="idArtikel" value="${ artikel.idArtikel }" />
					</c:url>
					<div class="col-lg-3 col-md-4 col-xs-6 thumb">
						<a class="thumbnail" href="${ trackAnsichtLink }"><img
							height=400px width=300px
							src="/Projekt/BildServlet?idArtikel=${ artikel.idArtikel }"
							title="${ artikel.interpret } - ${ artikel.album }"></a>
					</div>
				</c:forEach>


			</div>

</body>
</html>