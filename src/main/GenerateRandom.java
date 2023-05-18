package main;
import java.util.*;


public class GenerateRandom {
	private Random random;
	private float multiplier = 1.0f;
	private ArrayList<String> possibleNames = new ArrayList<String>(Arrays.asList(
			"Michael", "Jordan", "Steph", "Kush", "Droopy", "Hoi Fung", "Ray", 
			"Zaza", "Archibold", "Freddie", "Wij", "Richard", "Kang", "Zheng",
			"Rundle", "Tim", "Sum", "Ting", "Wong", "Jake", "Tuffy", "Stevo"));
	private ArrayList<String> possibleItemNames = new ArrayList<String>(Arrays.asList(
			"Apple", "Blue Pot", "Sushi", "Katsu Chicken Sandwich", "Protein Shake",
			"Trenbolone Acetate"));
	private ArrayList<String> possibleTeamNames = new ArrayList<String>(Arrays.asList(
			"Celtics", "Nets", "Knicks", "76ers", "Raptors", "Bulls", "Cavaliers", "Pistons",
			"Pacers", "Bucks", "Hawks", "Hornets", "Heat", "Magic", "Wizards", "Nuggets",
			"Timberwolves", "Thunder", "Trail Blazers", "Jazz", "Warriors", "Clippers",
			"Lakers", "Suns", "Kings", "Mavericks", "Rockets", "Grizzlies", "Pelicans", 
			"Spurs"));
	
	public GenerateRandom() {
		random = new Random();
		
	}
	public Athlete generateAthlete(int index) {
		HashMap<Athlete.STATS, Integer> statValues = new HashMap<Athlete.STATS, Integer>();
		int nameIndex = random.nextInt(possibleNames.size());
		String name = possibleNames.get(nameIndex);
		possibleNames.remove(nameIndex);
		int offence = (int) (random.nextInt(60, 70) * multiplier);
		statValues.put(Athlete.STATS.O, offence);
		int defence = (int) (random.nextInt(60, 70) * multiplier);
		statValues.put(Athlete.STATS.D, defence);
		int stamina = (int) (random.nextInt(60, 70) * multiplier);
		statValues.put(Athlete.STATS.S, stamina);
		int agility = (int) (random.nextInt(60, 70) * multiplier);
		statValues.put(Athlete.STATS.A, agility);
		Athlete.POSITION p = generatePosition(index);
		return new Athlete(name, p, statValues);
	}
	public Athlete.POSITION generatePosition(int index) {
		Athlete.POSITION p;
		switch (index) {
			case 0:
				p = Athlete.POSITION.PG;
				break;
			case 1:
				p = Athlete.POSITION.SG;
				break;
			case 2:
				p = Athlete.POSITION.SF;
				break;
			case 3:
				p = Athlete.POSITION.PF;
				break;
			case 4:
				p = Athlete.POSITION.C;
				break;
			default:
				return null;
			}
		return p;
	}
	
	
	public ArrayList<Athlete> generateWeeklyAthletes() {
		ArrayList<Athlete> weeklyAthletes = new ArrayList<Athlete>();
		while (weeklyAthletes.size()<3) {
			int positionIndex = random.nextInt(5);
			Athlete athlete = generateAthlete(positionIndex);
			weeklyAthletes.add(athlete);
		}
		return weeklyAthletes;
	}
	public ArrayList<Item> generateWeeklyItems() {
		ArrayList<Item> weeklyItems = new ArrayList<Item>();
		while (weeklyItems.size()<3) {
			int index = random.nextInt(possibleItemNames.size());
			int statIndex = random.nextInt(4);
			Athlete.STATS s = Athlete.STATS.O;
			String name = possibleItemNames.get(index);
			possibleItemNames.remove(index);
			int increaseBy = (int) (random.nextInt(10) * multiplier);
			int price = increaseBy * 100;
			switch (statIndex) {
			case 0:
				s = Athlete.STATS.O;
				break;
			case 1:
				s = Athlete.STATS.D;
				break;
			case 2:
				s = Athlete.STATS.S;
				break;
			case 3:
				s = Athlete.STATS.A;
				break;
			default:
				break;
			}
			Item item = new Item(name, s, increaseBy, price);
			weeklyItems.add(item);
		}	
		return weeklyItems;
	}
	
	public ArrayList<Team> generateTeams() {
		ArrayList<Team> opposingTeams = new ArrayList<Team>();
		while (opposingTeams.size()<3) {
			int teamIndex = random.nextInt(possibleTeamNames.size());
			Team team = new Team(possibleTeamNames.get(teamIndex));
			possibleTeamNames.remove(teamIndex);
				for (int i=0; i<5; i++) {
					Athlete athlete = generateAthlete(i);
					team.addPlayer(athlete);
				}
				opposingTeams.add(team);
			}
		return opposingTeams;
	}
	
	public int getRandomNumber(int upper) {
		return random.nextInt(upper);
	}
	
}