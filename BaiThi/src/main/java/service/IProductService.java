package service;

import model.Category;
import model.Product;

import java.util.List;

public interface IProductService <E>{
    void add(E e);
    List<E> getAll();
    void update(int id, E e);
    void delete(int id);
    void edit(int id, E e);
    List<E> findByName(String name);

    List<Product> findByPrice(double price);

    List<Category> findByCategory();
}
