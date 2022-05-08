package Manager;

import Repository.Repository;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    @Test
    void shouldAddNewProductToArray() {
        Repository repo = new Repository();
        Product p1 = new Book(1, "My Life", 300, "pac");

        repo.Add(p1);

        Product[] x = repo.getProducts();

        System.out.println(x);
    }
}