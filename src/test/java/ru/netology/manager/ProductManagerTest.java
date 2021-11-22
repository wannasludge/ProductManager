package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    Product product1 = new Book(1, "1984", 450, "George Orwell");
    Product product2 = new Book(2, "The Dharma Bums", 390, "Jack Kerouac");
    Product product3 = new Smartphone(3, "3310", 5000, "Nokia");
    Product product4 = new Smartphone(4, "RAZR V3", 7000, "Motorolla");
    Product product5 = new Smartphone(4, "L6", 6500, "Motorolla");

    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    @BeforeEach
    public void setUp() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        manager.add(product5);
    }
    @Test
    public void shouldSearchByNameBook() {
        Product[] actual = manager.searchBy("1984");
        Product[] expected = new Product[]{product1};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchByNameSmartphone() {
        Product[] actual = manager.searchBy("3310");
        Product[] expected = new Product[]{product3};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchByManufacturer() {
        Product[] actual = manager.searchBy("Motorolla");
        Product[] expected = new Product[]{product4};
        assertArrayEquals(actual, expected);
    }


    @Test
    public void shouldSearchByAuthor() {
        Product[] actual = manager.searchBy("Jack Kerouac");
        Product[] expected = new Product[]{product2};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchByAuthorFindNothing() {
        Product[] actual = manager.searchBy("Lev Tolstoy");
        Product[] expected = new Product[]{};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchByManufactererTwoSameResults(){
        Product[] actual = manager.searchBy("Motorolla");
        Product[] expected = new Product[]{product4, product5};
        assertArrayEquals(actual, expected);
    }

}