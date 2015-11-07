package com.jokewizard.jokes;

public class JokeWizard {

    public String getJoke(int day){
        if(day==2) {
            return "You should brush your teeth twice a day!!"                    ;
        }
        else if (day==1){
            return "My grandfather likes twerking!";
        }
        return "You are a developer. You should take a shower daily even in winters";
    }
}
