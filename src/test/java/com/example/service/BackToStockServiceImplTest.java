package com.example.service;

import com.example.model.Product;
import com.example.model.ProductCategory;
import com.example.model.User;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BackToStockServiceImplTest {
    private static BackToStockService backToStockService;
    private static User userNoPrivilege;
    private static User premiumUser;
    private static User userWithAgeNoPremium;
    private static User userWithAgeWithPremium;
    private static User userNull;
    private static Product medicalProduct;
    private static Product bookProduct;
    private static Product digitalProduct;

    @BeforeAll
    static void beforeAll() {
        userNoPrivilege = new User();
        userNoPrivilege.setName("Lili");
        userNoPrivilege.setAge(26);
        userNoPrivilege.setPremium(false);
        premiumUser = new User("Bob", true, 35);
        userWithAgeNoPremium = new User("Mike", false, 72);
        userWithAgeWithPremium = new User("Kate", true, 75);
        userNull = null;
        medicalProduct = new Product("Medical", ProductCategory.MEDICAL);
        bookProduct = new Product("Books", ProductCategory.BOOKS);
        digitalProduct = new Product("Digital", ProductCategory.DIGITAL);
        backToStockService = new BackToStockServiceImpl(medicalProduct, bookProduct, digitalProduct);
    }

    @Test
    void testThrowException_Ok() {
        assertThrows(RuntimeException.class,
                () -> backToStockService.subscribe(userNull, medicalProduct),
                "Can't subscribe null user");
    }

    @Test
    void listOfMedicalProduct_Ok() {
        backToStockService.subscribe(userNoPrivilege, medicalProduct);
        backToStockService.subscribe(userWithAgeNoPremium, medicalProduct);
        backToStockService.subscribe(userWithAgeWithPremium, medicalProduct);
        backToStockService.subscribe(premiumUser, medicalProduct);

        List<User> actually = backToStockService.subscribedUsers(medicalProduct);
        List<User> expected = List.of(userWithAgeNoPremium,
                userWithAgeWithPremium,
                premiumUser,
                userNoPrivilege);

        assertEquals(expected, actually);
    }

    @Test
    void listOfBookProduct_Ok() {
        backToStockService.subscribe(userNoPrivilege, bookProduct);
        backToStockService.subscribe(userWithAgeNoPremium, bookProduct);
        backToStockService.subscribe(userWithAgeWithPremium, bookProduct);
        backToStockService.subscribe(premiumUser, bookProduct);

        List<User> actually = backToStockService.subscribedUsers(bookProduct);
        List<User> expected = List.of(userWithAgeWithPremium,
                premiumUser,
                userWithAgeNoPremium,
                userNoPrivilege);

        assertEquals(expected, actually);
    }

    @Test
    void listOfDigitalProduct_Ok() {
        backToStockService.subscribe(userNoPrivilege, digitalProduct);
        backToStockService.subscribe(userWithAgeNoPremium, digitalProduct);
        backToStockService.subscribe(userWithAgeWithPremium, digitalProduct);
        backToStockService.subscribe(premiumUser, digitalProduct);

        List<User> actually = backToStockService.subscribedUsers(digitalProduct);
        List<User> expected = List.of(userWithAgeWithPremium,
                premiumUser,
                userWithAgeNoPremium,
                userNoPrivilege);

        assertEquals(expected, actually);
    }

    @Test
    void getListOfNullProduct_isNotOk() {
        Product product = new Product("Nonexistent product", ProductCategory.BOOKS);
        assertThrows(RuntimeException.class, () -> backToStockService.subscribedUsers(product),
                "Can't get list of subscribed users by this product: " + product);
    }
}
