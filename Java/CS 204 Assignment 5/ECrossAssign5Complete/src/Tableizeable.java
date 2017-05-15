/**
 * Takes the fields of a collection of objects and
 * returns them in a two dimensional array of strings
 * to population a table
 */
 public interface Tableizeable<T> {
	public String[][] toTable();
}
