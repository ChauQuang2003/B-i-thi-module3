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
            case "home":
                break;
            case "create":
                showFormAdd(request, response);
                break;
            default:
                showHomePage(request, response);

        }
    }

    private void showFormAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/add.jsp");
        List<Product> productList = productService.getAll();
        request.setAttribute("productList", productList);
        dispatcher.forward(request, response);
    }

    private void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/home.jsp");
        List<Product> products = productService.getAll();
        request.setAttribute("products", products);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addProduct(request, response);
                break;
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        Product product = new Product(id, name, price, quantity, quantity, color, description, category);
    }
}
