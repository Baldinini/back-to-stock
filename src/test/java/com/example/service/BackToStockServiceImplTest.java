package com.example.service;

import com.example.comparator.UsersAgePremiumComparator;
import com.example.comparator.UsersComparator;
import com.example.comparator.UsersMediumPremiumComparator;
import com.example.comparator.UsersPremiumComparator;
import com.example.model.Product;
import com.example.model.ProductCategory;
import com.example.model.User;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackToStockServiceImplTest {
    private static UsersComparator usersComparator;
    private static BackToStockService backToStockService;
    private static final User userNoPrivilege = new User("Lili", false, 26);
    private static final User premiumUser = new User("Bob", true, 35);
    private static final User userWithAgeNoPremium = new User("Mike", false, 72);
    private static final User userWithAgeWithPremium = new User("Kate", true, 75);
    private static final User userNull = new User();
    private static final Product medicalProduct = new Product("Medical", ProductCategory.MEDICAL);
    private static final Product bookProduct = new Product("Books", ProductCategory.BOOKS);
    private static final Product digitalProduct = new Product("Digital", ProductCategory.DIGITAL);

    @BeforeAll
    static void beforeAll() {
        usersComparator = new UsersComparator();
        usersComparator.setCustomComparatorForSpecialProduct(medicalProduct,new UsersAgePremiumComparator());
        usersComparator.setCustomComparatorForSpecialProduct(bookProduct,
                new UsersPremiumComparator().thenComparing(new UsersMediumPremiumComparator()));
        usersComparator.setCustomComparatorForSpecialProduct(digitalProduct,
                new UsersPremiumComparator().thenComparing(new UsersMediumPremiumComparator()));
        backToStockService = new BackToStockServiceImpl(usersComparator, medicalProduct, bookProduct, digitalProduct);
    }

    @Test
    void listOfMedicalProduct_Ok() {
        List<User> actually = List.of(premiumUser,
                userWithAgeNoPremium,
                userWithAgeWithPremium,
                userNoPrivilege);

        backToStockService.subscribe(userNull, medicalProduct);

    }
}