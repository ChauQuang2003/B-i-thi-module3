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

<body>
<div class="container mt-4">
  <h1 class="text-center">Management Product</h1>

  <div class="card mt-3">
    <div class="card-body">
      <h5>Find Product</h5>
      <form class="row g-3">
        <div class="col-md-3">
          <input type="text" class="form-control" placeholder="Enter Product Name" id="productName">
        </div>
        <div class="col-md-2">
          <input type="text" class="form-control" placeholder="Enter Price" id="price">
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
    <a href="http://localhost:8080/products?action=add">
      <button class="btn btn-dark float-end">+ Add Product</button>
    </a>
  </div>

  <table class="table table-bordered table-striped mt-5">
    <thead class="table-primary text-center">
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
    <c:forEach var="item" items="${products}" varStatus="loop">
      <tr>
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.price}</td>
        <td>${item.quantity}</td>
        <td>${item.color}</td>
        <td>${item.category.name}</td>
        <td>
          <button class="btn btn-warning btn-sm">Edit</button>
          <button class="btn btn-danger btn-sm">Delete</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
