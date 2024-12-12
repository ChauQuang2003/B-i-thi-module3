<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chauq
  Date: 12/12/2024
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Management Product</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<style>

  .delete-confirm-checkbox {
    display: none;
  }

  .delete-confirm {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 9999;
    justify-content: center;
    align-items: center;
  }

  .confirm-box {
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    text-align: center;
    width: 300px;
  }

  .delete-confirm-checkbox:checked + .delete-confirm {
    display: flex;
  }

  .confirm-box .btn {
    margin: 10px;
    text-decoration: none;
    padding: 10px 20px;
    border-radius: 5px;
    color: white;
  }

  .confirm-box .btn-danger {
    background-color: #dc3545;
  }

  .confirm-box .btn-secondary {
    background-color: #6c757d;
  }
</style>
<body>
<div class="container mt-4">
  <h1 class="text-center">Management Product</h1>

  <div class="card mt-3">
    <div class="card-body">
      <h5>Find Product</h5>
      <form action="/products?action=search" method="get" class="row g-3">
        <div class="col-md-3">
          <input hidden="hidden" name="action" value="search">
          <input type="text" class="form-control" placeholder="Enter Product Name" id="productName" name="keyword">
        </div>
        <div class="col-md-2">
          <input hidden="hidden" name="action" value="search">
          <input type="text" class="form-control" placeholder="Enter Price" id="price" name="keywordPrice">
        </div>
        <div class="col-md-3">
          <input type="text" class="form-control" placeholder="Enter Category" id="category">
        </div>
        <div class="col-md-2">
          <input type="text" class="form-control" placeholder="Enter Color" id="color">
        </div>
        <div class="col-md-2 d-flex align-items-center">
          <button type="reset" class="btn btn-secondary me-2">Clear</button>
          <button type="submit" class="btn btn-primary">Search</button>
        </div>
      </form>
    </div>
  </div>

  <div class="mt-3">
    <a href="http://localhost:8080/products?action=create">
      <button class="btn btn-dark float-end">+ Add Product</button>
    </a>
  </div>
  <p class="text-danger">${text}</p>
  <form method="post" action="${pageContext.request.contextPath}/products">
  <table class="table table-bordered table-striped mt-5">
    <thead class="table-primary text-center">

    <p class="text-success">
      <c:if test="${not empty sessionScope.txt}">
        ${sessionScope.txt}
        <c:set var="txt" value="${sessionScope.txt}" />
        <c:remove var="txt" />
      </c:if>
    </p>
    <tr>
      <th>STT</th>
      <th>Product Name</th>
      <th>Price</th>
      <th>Quantity</th>
      <th>Color</th>
      <th>Category</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody class="text-center">
    <c:forEach var="item" items="${productList}" varStatus="loop">
      <tr>
        <td>${loop.index + 1}</td>
        <td>${item.name}</td>
        <td>${item.price}</td>
        <td>${item.quantity}</td>
        <td>${item.color}</td>
        <td>${item.categoryId.name}</td>
        <td>

          <button class="btn btn-warning btn-sm">Edit</button>
          <label for="delete-${item.id}" class="text-danger" style="cursor: pointer;">
            <i class="fas fa-trash"></i> Delete
          </label>
          <input type="checkbox" id="delete-${item.id}" class="delete-confirm-checkbox">
          <div class="delete-confirm">
            <div class="confirm-box">
              <p>Bạn có chắc chắn muốn xóa sản phẩm này không?</p>
              <a href="${pageContext.request.contextPath}/products?action=delete&id=${item.id}" class="btn btn-danger">Xóa</a>
              <label for="delete-${item.id}" class="btn btn-secondary">Hủy</label>
            </div>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybBogGzOgQpeP8pgi04B3p7r6L2e8zBOHp+0m9Qd8Pp6L6gD6" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-pZjw8f+ua7Kw1TIq5tvbLrQ+0WfGe1Ff5Lg5ZC2oqvN8Kp1U+6E5z0I5qD0XGf6z" crossorigin="anonymous"></script>
</body>

</html>
