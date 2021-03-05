package com.example.comparator;

import com.example.model.User;
import java.util.Comparator;

public class UsersPremiumComparator implements Comparator<User> {
    @Override
    public int compare(User firstUser, User secondUser) {
        return Boolean.compare(secondUser.isPremium(), firstUser.isPremium());
    }
}
