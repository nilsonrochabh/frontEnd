<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usu�rio</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<center>
		<h1>Cadastro de Produtos</h1>
	<h3 style="color: orange;">${msg}</h3>
	</center>
	
	<form action="salvarProduto" method="post" id="formProd">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>C�digo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${prod.id}" class="field-long"></td>
					</tr>
					

					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${prod.nome}"></td>
					</tr>
					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${prod.quantidade}"></td>
					</tr>
										<tr>
						<td>Valor:</td>
						<td><input type="text" id="valor" name="valor"
							value="${prod.valor}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> 
							<input type="submit"  value="Cancelar" 
									onclick="document.getElementById('formProd').action = 'salvarProduto?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>
<a href="acessoLiberado.jsp">In�cio </a>
<a href="index.jsp">Sair </a>
	<div class="container">
		<table class="responsive-table">
			<caption>Produtos Cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor</th>
				<th>Delete</th>
				<th>Editar</th>
			</tr>
			<c:forEach items="${produtos}" var="prod">
				<tr>
					<td style="width: 150px"><c:out value="${prod.id}">
						</c:out></td>
					<td><c:out value="${prod.nome}"></c:out></td>
					<td><c:out value="${prod.quantidade}"></c:out></td>
					<td><c:out value="${prod.valor}"></c:out></td>

					<td><a href="salvarProduto?acao=delete&prod=${prod.id}"><img
							src="resources/img/excluir.png" alt="excluir" title="Excluir"
							width="20px" height="20px"> </a></td>
					<td><a href="salvarProduto?acao=editar&prod=${prod.id}"><img
							alt="Editar" title="Editar" src="resources/img/editar.png"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>