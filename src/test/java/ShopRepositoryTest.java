import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product product1 = new Product(1, "Хлеб", 30);
    Product product2 = new Product(2, "Яблочный сок", 100);
    Product product3 = new Product(3, "Шоколад", 80);

    @Test
    public void shouldDeleteExistProduct() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        repository.removeById(2);
        Product[] expected = { product1, product3 };
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenTryDeleteNotExistProduct() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(5);
        });
    }

    @Test
    public void shouldAddProduct() {
        ShopRepository repository = new ShopRepository();

        repository.add(product1);
        Product[] expected = { product1 };
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenIdAlreadyExist() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(new Product(2, "Рис", 70));
        });
    }

}
