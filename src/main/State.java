package main;

public class State {
    private int position;
    private boolean isParked;
    private int streak;

    public State(int pos, boolean isprkd, int strk) {
        position = pos;
        isParked = isprkd;
        streak = strk;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public boolean getIsParked() {
        return isParked;
    }
    public void setParked(boolean isParked) {
        this.isParked = isParked;
    }

    public int getStreak() {
        return streak;
    }
    public void setStreak(int streak) {
        this.streak = streak;
    }
}
