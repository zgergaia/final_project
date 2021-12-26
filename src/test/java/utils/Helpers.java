package utils;

import com.codeborne.selenide.SelenideElement;

public class Helpers {
    public static SelenideElement FindAndScroll(SelenideElement element) {
        return element.scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}");
    }
}
