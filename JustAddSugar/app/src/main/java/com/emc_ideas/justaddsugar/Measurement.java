package com.emc_ideas.justaddsugar;

/**
 * Created by ecross on 11/5/17.
 */

public enum Measurement {
    teaspoon ("tsp"),
    tablespoon ("tbsp"),
    pound ("lb"),
    gram ("g"),
    ounce ("oz"),
    cup ("cup"),
    liter("L"),
    gallon("gal"),
    quart("qt"),
    mililiter("mL"),
    fuild_ounce("fl oz"),
    Null("null")

    ;//ends field definition

    private final String code;


    Measurement(String str){
        this.code = str;
    }

    public String getMeasurements(){
        return this.code;
    }

}
