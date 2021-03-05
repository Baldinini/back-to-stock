package com.example.service;

import com.example.comparator.UsersComparator;
import com.example.model.Product;
import com.example.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BackToStockServiceImpl implements BackToStockService {
    private final Map<Product, List<User>> subscribers = new HashMap<>();
    private final UsersComparator usersComparator = new UsersComparator();

    public BackToStockServiceImpl(Product... products) {
        for (Product product : products) {
            this.subscribers.put(product, new ArrayList<>());
        }
    }

    @Override
    public void subscribe(User user, Product product) {
        List<User> users = subscribers.get(product);
        if (user == null) {
            throw new RuntimeException("Can't subscribe null user");
        }
        users.add(user);
    }

    @Override
    public List<User> subscribedUsers(Product product) {
        if (subscribers.containsKey(product)) {
            return subscribers.get(product)
                    .stream()
                    .sorted(usersComparator.getCustomComparatorForSpecialProduct(product))
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("Can't get list of subscribed users by this product: " + product);
    }
}
