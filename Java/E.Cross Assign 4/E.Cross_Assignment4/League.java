/**Assignment 4, Fall 2014: Worker Class (League)
 * Determines the records, winners, losses and ties of six soccer teams
 * The values 2d array is based on the file that is read in on through
 * the driver class.
 * Values for the records, winners, losses and ties are computated using 
 * incremented loops
 * 
 * @author Ebony Cross
 * 
 */

public class League {
	private final int ROWS = 6; //number of array rows
	private final int COLS = 10; //number of array columns
	private int[][] points = new int[6][10]; //points for the soccer teams
	private String[] names = {"Arabian", "Gobi", "Outback","Patagonian", "Sahara", "Sonoran"};
	private int win, lose, tie;

	/** default constructor*/
	public League(){
		names = null;
		win = 0;
		lose = 0;
		tie = 0;
		for(int row = 0; row < points.length; row++){
			for(int col = 0; col < points[row].length; col++){
				points[row][col] = 1;
			}
		}	
	}

	/**constructor copies values of array points to array of parameter 
	 * 
	 * @param p score of points for each team in each game
	 */
	public League(int[][] p){
		for(int row = 0; row < p.length; row++){
			for(int col = 0; col < p[row].length; col++){
				points[row][col]=p[row][col];	
			}
		}
	}

	/**contructor initializes win, lose, tie to values from the parameter and 
	 * copies arrays to the corresponding array in the parameter
	 * @param w the numbers of wins for a particular team
	 * @param l the numbers of losses for a particular team
	 * @param t the numbers of ties for a particular team
	 * @param teamName the names in the soccer league
	 * @param p the score of points for each team in each game
	 */
	public League(int w, int l, int t, String[] teamName, int[][] p){
		win = w;
		lose = l;
		tie = t;
		for(int row = 0; row < p.length; row++){
			for(int col = 0; col < p[row].length; col++){
				points[row][col] =p[row][col];
			}
		}

		for(int i = 0; i < teamName.length; i++){
			teamName[i] = names[i];
		}
	}

	/**computes string name of each team in league
	 * 
	 * @param num position number of the team specified
	 * @return name of the team 
	 */
	public String getNames(int num){
		String[] team = new String[names.length];
		for(int i = 0; i < names.length; i++){
			team[i] = names[i];
		}
		return team[num];
	}

	/**find the total number of points a specific team
	 * 
	 * @param num position number of the team specified
	 * @return total number of points earned
	 */
	public int getTotal(int num){
		int total = 0;
		for(int i = 0; i < points.length; i++){
			total = 0;
			for(int col = 0; col < points[num].length; col++){
				total += points[num][col]; 
			}	
		}
		return total;
	}

	/**find the number of losses for the specified team
	 * 
	 * @param num position number of the team specified
	 * @return the number of losses for the specific team
	 */
	public int getLoss(int num){
		int totalLoss = 0;
		for(int i = 0; i < points.length; i++){
			totalLoss = 0;
			for(int col = 0; col < points[num].length; col++){
				if(points[num][col] == 0){
					totalLoss++; 
				}
			}	
		}
		lose = totalLoss;
		return totalLoss;
	}

	/**find the number of ties of the indicated team
	 * 
	 * @param num position number of the team specified
	 * @return number of ties for the specific team
	 */
	public int getTie(int num){
		int totalTie = 0;
		for(int i = 0; i < points.length; i++){
			totalTie = 0;
			for(int col = 0; col < points[num].length; col++){
				if(points[num][col] == 1){
					totalTie++; 
				}
			}	
		}
		tie = totalTie;
		return totalTie;
	}

	/**find the number of wins for a specified team
	 * 
	 * @param num position number of the team specified
	 * @return number of wins for desired team
	 */
	public int getWin(int num){
		int totalWin = 0;
		for(int i = 0; i < points.length; i++){
			totalWin = 0;
			for(int col = 0; col < points[num].length; col++){
				if(points[num][col] == 3){
					totalWin++; 
				}
			}	
		}
		win = totalWin;
		return totalWin;
	}

	/**determine the championship winner which will be the team(s)
	 *  with the most points
	 * @return names(s) of the team(s)
	 */
	public String championshipWinner(){
		int highest;
		int[] total = new int[points.length]; 
		int totalWin = 0;
		String teamWinner = "";

		for(int row = 0; row < points.length; row++){
			totalWin = 0;
			for(int col = 0; col < points[row].length; col++){
				totalWin += points[row][col];
			}
			total[row] = totalWin;
		}

		highest = total[0];

		for(int i = 1; i < points.length; i++){
			if(total[i] > highest){
				highest = total[i];
			}
		}

		for(int row = 0; row < points.length; row++){
			if(total[row] == highest){
				teamWinner += names[row] + " ";
			}
		}
		return teamWinner;
	}

	/**determine winner of the earth cup trophy which is the team(s) with
	 * the most games won
	 * 
	 * @return name(s) of the team(s) with the most wins 
	 */
	public String earthCupWinner(){
		int highest;
		int[] total = new int[points.length]; 
		int totalWin = 0;
		String teamWinner = "";

		for(int row = 0; row < points.length; row++){
			totalWin = 0;
			for(int col = 0; col < points[row].length; col++){
				if(points[row][col] == 3){
					totalWin += points[row][col];
				}
			}
			total[row] = totalWin;
		}

		highest = total[0];

		for(int i = 1; i < points.length; i++){
			if(total[i] > highest){
				highest = total[i];
			}
		}

		for(int row = 0; row < points.length; row++){
			if(total[row] == highest){
				teamWinner += names[row] + " ";
			}
		}
		return teamWinner;


	}

	public String getRecord(int num){
		String rec = getWin(num) + "-" + getLoss(num) + "-" + getTie(num);
		return rec;

	}
}