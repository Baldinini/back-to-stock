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

    public void setCustomComparatorForSpecialProduct(Product product,
                                                     Comparator<User> userComparator) {
        this.customComparatorForSpecialProduct.put(product.getCategory(), userComparator);
    }

    public Comparator<User> getCustomComparatorForSpecialProduct(Product product) {
        return customComparatorForSpecialProduct.get(product.getCategory());
    }
}
