package com.example.comparator;

import com.example.model.Product;
import com.example.model.ProductCategory;
import com.example.model.User;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class UsersComparator {
    private final Map<ProductCategory, Comparator<User>> customComparatorForSpecialProduct =
            new HashMap<>();

    public UsersComparator() {
        UsersAgePremiumComparator agePremiumComparator = new UsersAgePremiumComparator();
        Comparator<User> comparator = new UsersPremiumComparator()
                .thenComparing(new UsersMediumPremiumComparator());
        customComparatorForSpecialProduct.put(ProductCategory.MEDICAL, agePremiumComparator);
        customComparatorForSpecialProduct.put(ProductCategory.BOOKS, comparator);
        customComparatorForSpecialProduct.put(ProductCategory.DIGITAL, comparator);
    }

    public Comparator<User> getCustomComparatorForSpecialProduct(Product product) {
        return customComparatorForSpecialProduct.get(product.getCategory());
    }
}
