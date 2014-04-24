<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="wrapper">
<div id="header-wrapper">
<div id="header">
<div id="logo">
<h1><a href="#">Lab Sample Blog </a></h1>
<p>template design by <a href="http://www.freecsstemplates.org/">CSS
Templates</a></p>
</div>
</div>
</div>
<div id="page">
<div id="page-bgtop">
<div id="page-bgbtm">
<div id="content"><c:forEach items="${articles}" var="each">
	<div class="post">
	<h2 class="title"><a href="#">${each.title}</a></h2>
	<p class="meta">Posted by ${profile.name}</a> on
	${each.createdate} &nbsp;&bull;&nbsp; hit
	(${each.hit})</a> &nbsp;&bull;&nbsp;</p>
	<div class="entry">
	<p><img src="/images/img03.jpg" width="186" height="186" alt=""
		class="alignleft border" />${each.contents}</p>
	</div>
	</div>
</c:forEach></div>
<div id="sidebar">
<ul>
	<li>
	<h2>Profile</h2>
	<p>${profile.name}, ${profile.hobby}, ${profile.age}</p>
	</li>
	<li>
	<h2>Visitor</h2>
	<ul>
		<c:forEach items="${visitors}" var="each">
			<li><a href="/lab/blog/${each.visitorId}">${each.nickname}</a></li>
		</c:forEach>
	</ul>
	</li>
	<li>
	<h2>Comments</h2>
	<ul>
		<c:forEach items="${comments}" var="each">
			<li>${each.comment} ${each.createdate}</li>
		</c:forEach>
	</ul>
	</li>
</ul>
</div>

</div>
</div>
</div>
</div>
</body>
</html>
