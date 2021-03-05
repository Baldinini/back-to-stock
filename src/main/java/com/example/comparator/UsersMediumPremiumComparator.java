package com.example.comparator;


import com.example.model.User;
import java.util.Comparator;

public class UsersMediumPremiumComparator implements Comparator<User> {
    @Override
    public int compare(User firstUser, User o2) {
        if (firstUser.getAge() >= 70 && !firstUser.isPremium()) {
            if (o2.getAge() < 70 && !o2.isPremium()) {
                return -1;
            }
        } else if (firstUser.getAge() < 70 && !firstUser.isPremium()) {
            if (o2.getAge() >= 70 && !o2.isPremium()) {
                return 1;
            }
        }
        return 0;
    }
}
