package service;

import configuration.ConnectDatabase;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService<Product> {
    Connection connection = ConnectDatabase.getConnection();

    @Override
    public void add(Product product) {
        String sql = "insert into product(name, price, quantity, color, description,category) values(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "select product.*, c.name as nameCategory from product join category c on c.id = product.category_id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String nameCategory = resultSet.getString("nameCategory");
                Category category = new Category(category_id, nameCategory);
                Product product = new Product(id, name, price, quantity, color, description, category);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void update(int id, Product product) {
        String sql = "UPDATE product SET name = ?, price = ?, quantity = ?, category_id = ?, color = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, product.getCategory().getId());
            preparedStatement.setString(5, product.getColor());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xóa thành công sản phẩm với ID: " + id);
            } else {
                System.out.println("Không tìm thấy sản phẩm với ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa sản phẩm", e);
        }
    }

    @Override
    public void edit(int id, Product product) {

    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }
}
