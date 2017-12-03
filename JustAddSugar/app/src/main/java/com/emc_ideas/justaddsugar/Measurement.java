package com.emc_ideas.justaddsugar;

import java.io.Serializable;

/**
 * Created by ecross on 11/5/17.
 */

public enum Measurement implements Serializable {

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

    private static final long serialVersionUID = -7060210544600464481L;


    Measurement(String str){
        this.code = str;
    }


    public String getMeasurementsString(){
        return this.code;
    }

    @Override
    public String toString(){
        return this.code;
    }

}
