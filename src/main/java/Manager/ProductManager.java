package Manager;

import Repository.Repository;
import ru.netology.domain.Product;

public class ProductManager {

    private Repository repository;

    public ProductManager(Repository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save(product);
    }


    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;

    }

    public boolean matches(Product product, String search) {
        return product.getTitle().contains(search);
    }
}
