package controller;

import model.Category;
import model.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductService", value = "/products")
public class ProductController extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormAdd(request, response);
                break;
            case "delete":
                showFormDelete(request, response);
                break;
            case "search":
                showSearch(request, response);
                break;
            case "home":
                break;
            default:
                showHomePage(request, response);

        }
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String keyword = request.getParameter("keyword");
//        List<Product> products = productService.findByName(keyword);
//        request.setAttribute("productList", products);
//        if (keyword == null) {
//            double keywordPrice = Double.parseDouble(request.getParameter("keywordPrice"));
//            List<Product> products1 = productService.findByPrice(keywordPrice);
//            request.setAttribute("productList", products1);
//        }
//        request.getRequestDispatcher("/view/home.jsp").forward(request, response);

        String keyword = request.getParameter("keyword");  // Tìm kiếm theo tên sản phẩm
        String keywordPrice = request.getParameter("keywordPrice");  // Tìm kiếm theo giá

        List<Product> products = new ArrayList<>();
        if (keyword != null && !keyword.isEmpty()) {
            products = productService.findByName(keyword);
        } else if (keywordPrice != null && !keywordPrice.isEmpty()) {
            try {
                double price = Double.parseDouble(keywordPrice);
                products = productService.findByPrice(price);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Giá không hợp lệ");
            }
        }

        request.setAttribute("productList", products);
        request.getRequestDispatcher("/view/home.jsp").forward(request, response);

    }

    private void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        response.sendRedirect("/products");
    }

    private void showFormAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = productService.findByCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/add.jsp");
        dispatcher.forward(request, response);
    }

    private void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/home.jsp");
        List<Product> products = productService.getAll();
        request.setAttribute("productList", products);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addProduct(request, response);
                break;
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));

        boolean hasError = false;

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("nameError", "Tên sản phẩm không được để trống.");
            hasError = true;
        }

        double price = 0;
        if (priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("priceError", "Giá sản phẩm không được để trống.");
            hasError = true;
        } else {
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 10000000) {
                    request.setAttribute("priceError", "Giá sản phẩm phải lớn hơn 10.000.000 VNĐ.");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("priceError", "Giá sản phẩm phải là một số hợp lệ.<br>");
                hasError = true;
            }
        }


        int quantity = 0;
        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            request.setAttribute("quantityError", "Số lượng sản phẩm không được để trống.<br>");
            hasError = true;
        } else {
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    request.setAttribute("quantityError", "Số lượng sản phẩm phải là số nguyên dương.<br>");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("quantityError", "Số lượng sản phẩm phải là một số nguyên hợp lệ.<br>");
                hasError = true;
            }
        }


        if (color == null || !(color.equals("Đỏ") || color.equals("Xanh") || color.equals("Đen") ||
                color.equals("Trắng") || color.equals("Vàng"))) {
            request.setAttribute("colorError", "Màu sắc phải là một trong các giá trị: Đỏ, Xanh, Đen, Trắng, Vàng.<br>");
            hasError = true;
        }

        List<Category> categories = productService.findByCategory();
        request.setAttribute("categories", categories);

        if (hasError) {
            request.getRequestDispatcher("/view/add.jsp").forward(request, response);
            List<Product>categoryList = productService.getAll();
            request.setAttribute("categoryList", categoryList);
            return;
        }

        Category category1 = new Category(category);
        Product product = new Product(name, price, quantity, color, description, category1);
        productService.add(product);
        request.getSession().setAttribute("txt", "Thêm sản phẩm thành công");
        response.sendRedirect("/products");
    }

}
