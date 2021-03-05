package com.example.comparator;


import com.example.model.User;
import java.util.Comparator;

public class UsersAgePremiumComparator implements Comparator<User> {
    @Override
    public int compare(User firstUser, User secondUser) {
        if (firstUser.getAge() >= 70 || firstUser.isPremium()) {
            if (secondUser.isPremium() || secondUser.getAge() >= 70) {
                return 0;
            }
            return -1;
        }
        return 1;
    }
}
