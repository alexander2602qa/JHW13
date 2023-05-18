public class ShopRepository {
    private Product[] products = new Product[0];

    private Product findById(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].id == id) {
                return products[i];
            }
        }
        return null;
    }

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Товар с таким Id уже существует");
        }
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }


    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Нельзя удалить несуществующий товар"
            );
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}
