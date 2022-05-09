package Manager;

import Repository.Repository;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    Product p1 = new Book(1,"The Lord of The Rings", 1000, "J. R. R. Tolkien");
    Product p2 = new Book(2, "Pride and Prejudice", 800, "Jane Austen");
    Product p3 = new Book(3, "To Kill a Mockingbird ", 700, "Harper Lee");
    Product p4 = new Book(4, "War and Peace", 1500, "Lev Tolstoy");
    Product p5 = new Book(5, "The Godfather", 900, "Mario Puzo");

    Product p6 = new Smartphone(6, "IPhone 12 Pro Max", 162000, "Apple");
    Product p7 = new Smartphone(7, "Realme 8 128", 24000, "Xiaomi");
    Product p8 = new Smartphone(8, "Galaxy A52", 34000, "Samsung");
    Product p9 = new Smartphone(9, "IPhone 12 mini", 74000, "Apple");
    Product p10 = new Smartphone(10, "Redmi 9", 19000, "Xiaomi");
    Product p11 = new Smartphone(11, "IPhone 12 mini", 74000, "Apple");


    @Test
    void shouldAddNewProductToArray() {
        Repository repo = new Repository();

        repo.save(p10);
        repo.save(p5);
        repo.save(p1);

        Product[] expected = {p10, p5, p1};
        Product[] actual = repo.getProducts();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddNewProductToArrayWithManager() {
        Repository repo = new Repository();
        ProductManager manager = new ProductManager(repo);

        manager.add(p1);
        manager.add(p4);

        Product[] expected = {p1, p4};
        Product[] actual = repo.getProducts();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSetProducts() {
        Repository repo = new Repository();

        repo.setProducts(new Product[]{p4, p5, p6});

        Product[] expected = {p4, p5, p6};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        Repository repo = new Repository();

        repo.setProducts(new Product[]{p4, p5, p6});

        repo.removeById(5);

        Product[] expected = {p4, p6};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByTest() {
        Repository repo = new Repository();
        ProductManager manager = new ProductManager(repo);

        repo.setProducts(new Product[]{p1, p2, p3, p6, p9,});


        Product[] expected = {p9};
        Product[] actual = manager.searchBy("IPhone 12 mini");

        assertArrayEquals(expected, actual);
    }

    @Test
    void matchesTest() {
        Repository repo = new Repository();
        ProductManager manager = new ProductManager(repo);

        repo.setProducts(new Product[]{p1, p2, p3, p6, p9});


        boolean expected = true;
        boolean actual = manager.matches(p9, "IPhone 12 mini");

        assertEquals(expected, actual);
    }

    @Test
    void searchByTestMultipleMatches() {
        Repository repo = new Repository();
        ProductManager manager = new ProductManager(repo);

        repo.setProducts(new Product[]{p1, p2, p3, p6, p9, p11});


        Product[] expected = {p9, p11};
        Product[] actual = manager.searchBy("IPhone 12 mini");

        assertArrayEquals(expected, actual);
    }
}