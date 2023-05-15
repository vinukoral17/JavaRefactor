package portfolioTask5;

import java.util.Comparator;
import java.util.List;


public class Team implements Comparable<Team> {
	private int position;
	private String name;
	private int races;
	private int wins;
	private int podiums;
	private int points;
	private int polePositions;
	private int fastestLaps;
	private int pointsDifference;
	private int seasonsInChampionship;

	public Team(int position, String name, int races, int wins, int podiums, int points, int polePositions,
			int fastestLaps, int pointsDifference, int seasonsInChampionship) {
		this.position = position;
		this.name = name;
		this.races = races;
		this.wins = wins;
		this.podiums = podiums;
		this.points = points;
		this.polePositions = polePositions;
		this.fastestLaps = fastestLaps;
		this.pointsDifference = pointsDifference;
		this.seasonsInChampionship = seasonsInChampionship;
	}

	public String toString() {
		return String.format("%d. %-20s    %d  %d  %d  %d  %d  %d  %d  %d", position, name, races, wins, podiums, points,
				polePositions, fastestLaps, pointsDifference, seasonsInChampionship);
	}

	public int getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}

	public int getRaces() {
		return races;
	}

	public int getWins() {
		return wins;
	}

	public int getPodiums() {
		return podiums;
	}

	public int getPoints() {
		return points;
	}

	public int getPolePositions() {
		return polePositions;
	}

	public int getFastestLaps() {
		return fastestLaps;
	}

	public int getPointsDifference() {
		return pointsDifference;
	}

	public int getSeasonsInChampionship() {
		return seasonsInChampionship;
	}

	// Setters
	public void setPosition(int position) {
		this.position = position;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRaces(int races) {
		this.races = races;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setPodiums(int podiums) {
		this.podiums = podiums;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setPolePositions(int polePositions) {
		this.polePositions = polePositions;
	}

	public void setFastestLaps(int fastestLaps) {
		this.fastestLaps = fastestLaps;
	}

	public void setPointsDifference(int pointsDifference) {
		this.pointsDifference = pointsDifference;
	}

	public void setSeasonsInChampionship(int seasonsInChampionship) {
		this.seasonsInChampionship = seasonsInChampionship;
	}

	// CompareTo method for sorting
	public int compareTo(Team other) {
		return Integer.compare(this.getPoints(), other.getPoints());
	}

	public static List<Team> getTopTeamsByPoints(List<Team> teams, int n) {
        Comparator<Team> byPoints = Comparator.comparing(Team::getPoints).reversed();
        return teams.stream()
        		.sorted(byPoints)
        		.limit(n)
        		.toList();
        		}
}
