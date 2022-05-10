package Repository;

import ru.netology.domain.Product;

public class Repository {

    protected Product[] products = new Product[0];

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }


    public void save(Product product) {
        checkIfProductAlreadyInRepo(product.getId());
        int length = products.length + 1;
        Product[] tmp = new Product[length];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        int length = products.length - 1;
        Product[] tmp = new Product[length];
        int index = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[index] = product;
                index++;
            }
        }
        products = tmp;

    }

    public Product findById(int id) {
        for (Product product : findAll()) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void checkIfProductAlreadyInRepo(int id) {
        for (Product product : findAll()) {
            if (product.getId() == id) {
                throw new AlreadyExistsException("Product with id " + id + " already exists");
            }
        }
        return;
    }

}
