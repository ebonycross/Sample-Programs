import com.emc_ideas.justaddsugar.Ingredient;

import java.util.ArrayList;

/**
 * Created by ecross on 11/5/17.
 */

public class recipe {
    /*
    object contains:
    1. list of ingredients (incl. measurement amt)
    2. measurement
    3. directions
    4. cooktime
    5. serving amount
     */
    private ArrayList<Ingredient> ingredient;
    private String direction;
    private double cooktime;
    private int servingAmt;

    public recipe(){
        ingredient = new ArrayList<Ingredient>();
        direction = null;
        cooktime = 0;
        servingAmt = 0;
    }

    public recipe(String dir, double time, int serv){
        ingredient = new ArrayList<Ingredient>();
        direction = dir;
        cooktime = time;
        servingAmt = serv;
    }

    public ArrayList<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(ArrayList<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public ArrayList<Ingredient> addIngredient(Ingredient ingred){
        ingredient.add(ingred);
        return ingredient;
    }

    public ArrayList<Ingredient> removeIngredient(Ingredient ingred, int i){
        ingredient.remove(i);
        return ingredient;
    }

    public int getItemCount(){
        return ingredient.size();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getCooktime() {
        return cooktime;
    }

    public void setCooktime(double cooktime) {
        this.cooktime = cooktime;
    }

    public int getServingAmt() {
        return servingAmt;
    }

    public void setServingAmt(int servingAmt) {
        this.servingAmt = servingAmt;
    }
}
