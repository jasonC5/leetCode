package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

public class LC929 {
    public int numUniqueEmails(String[] emails) {
        Set<String> container = new HashSet<>();
        for (String email : emails) {
            String[] split = email.split("@");
            if (split[0].contains(".")) {
                split[0] = split[0].replace(".","");
            }
            if (split[0].contains("+")) {
                split[0] = split[0].split("\\+")[0];
            }
            email = split[0]+"@"+split[1];
            container.add(email);
        }
        return container.size();
    }
}
