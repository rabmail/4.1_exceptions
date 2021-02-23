package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private Product Prod1 = new Book(1, "Java", 500, "Pupkin", 1000, 2021);
    private Product Prod2 = new Book(2, "C++", 600, "Ivanov", 100, 2020);
    private Product Prod3 = new Book(3, "Delphi", 500, "Semenov", 600, 2015);
    private Product Prod4 = new Book(4, "MuMu", 500, "Turgenev", 500, 2005);
    private Product Prod5 = new Book(5, "Kama-Sutra", 500, "Indeec", 300, 1980);

    @Test
    void shouldRemoveById() throws NotFoundException {
        repository.save(Prod1);
        repository.save(Prod2);
        repository.save(Prod3);
        repository.save(Prod4);
        repository.save(Prod5);

        ProductManager managers = new ProductManager(repository);
        int id = 3;
        managers.removeById(id);
        Product[] actual = managers.getAll();
        Product[] expected = new Product[]{Prod1, Prod2, Prod4, Prod5};
        assertArrayEquals(actual, expected);
    }
    @Test
    void shouldRemoveByIdException(){
        repository.save(Prod1);
        repository.save(Prod2);
        repository.save(Prod3);
        repository.save(Prod4);
        repository.save(Prod5);
        ProductManager managers = new ProductManager(repository);
        int id = 10;
        assertThrows(NotFoundException.class, () -> managers.removeById(id));
    }

}
