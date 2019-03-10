<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Produccion</title>
		<meta name="description" content="Pagina sin sentido en el lado del server" />
		<meta name="author" content="Claramente no fue sacado de Codrops" />
		<link rel="shortcut icon" href="favicon.ico">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css" />" />

		<script>document.documentElement.className="js";var supportsCssVars=function(){var e,t=document.createElement("style");return t.innerHTML="root: { --tmp-var: bold; }",document.head.appendChild(t),e=!!(window.CSS&&window.CSS.supports&&window.CSS.supports("font-weight","var(--tmp-var)")),t.parentNode.removeChild(t),e};supportsCssVars()||alert("Please view this demo in a modern browser that supports CSS Variables.");</script>
	</head>
	<body class="demo-1 loading">
		<main>
			<div class="content">
				<div class="content__section">
					<div class="content__main">
						<span>Produccion</span>
						<span>Auto-partes</span>
					</div>
					<h2 class="content__text">Server side</h2>
				</div>
				<div class="content__section">
					<h2 class="content__text">Contacto</h2>
					<div class="content__contact">
						<a href="#">Diego Viera</a>
                                                <a href="#" onclick="copyText(this)" >v88.diego@gmail.com</a>
						<a href="#">-------------------------------</a>
					</div>
				</div>
			</div>
		</main>
                <script>
                function copyText(element) {
                var range, selection, worked;

                if (document.body.createTextRange) {
                  range = document.body.createTextRange();
                  range.moveToElementText(element);
                  range.select();
                } else if (window.getSelection) {
                  selection = window.getSelection();        
                  range = document.createRange();
                  range.selectNodeContents(element);
                  selection.removeAllRanges();
                  selection.addRange(range);
                }

                try {
                  document.execCommand('copy');

                }
                catch (err) {
                  alert('Error al copiar el texto');
                }
              }
                </script>
                <script src="<c:url value="/resources/js/charming.min.js" />"></script>
                <script src="<c:url value="/resources/js/demo.js" />"></script>
	</body>
</html>
