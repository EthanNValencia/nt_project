package com.nephew.website;

import com.nephew.website.entities.PageType;

public class Main {

    public static void main(String[] args) {
        for (PageType pageType : PageType.values()) {
            System.out.println(makeStringLowercaseWithUppercaseAtIndexZero(pageType.toString()) + ",");
        }
    }

    public static String makeStringLowercaseWithUppercaseAtIndexZero(String pageType) {
        String newString = pageType.toLowerCase();
        newString = Character.toUpperCase(newString.charAt(0)) + newString.substring(1);
        return newString;
    }
}
