<%--
  Created by IntelliJ IDEA.
  User: zhangchenglong
  Date: 2023/12/17
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
                    pageEncoding="UTF-8"/>
<html>
<head>
    <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
</head>
<body>
\${userObject.username} : ${userObject.username}
\${applicationScope['scopedTarget.user'].username} : ${applicationScope['scopedTarget.user'].username}
</body>
</html>