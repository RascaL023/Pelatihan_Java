package src.player;

public class Player {
    private String name, team;
    private int semester, win, lose, draw;

    public Player(String name, String team, int s){
        win = 0; lose = 0; draw = 0;
        this.name = name;
        this.team = team;
        semester = s;
    }

    public String getName(){ return name; }
    public String getTeam(){ return team; }
    public int getSmt(){ return semester; }
    public int getWin(){ return win; }
    public int getLose(){ return lose; }
    public int getDraw(){ return draw; }

    public void addWin(){ win++; }
    public void addLose(){ lose++; }
    public void addDraw(){ draw++; }
}
