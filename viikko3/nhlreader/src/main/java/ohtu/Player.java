
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private String team;
    private int assists;
    private int goals;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    @Override
    public String toString() {
        return name + "(" + team + ") with " + goals + " + " + assists + " = " + (goals + assists) + " points." ;
    }

}
