public class Human {
    boolean isAlive;
    boolean isSick;
    boolean isImmune;
    int infectionDay;
    int sickDaysremaining;

    public Human(int sickDays) {
        isAlive = true;
        isSick = false;
        isImmune = false;
        sickDaysremaining = sickDays;
    }
	
	// a shorter version of toText with no "nametags"
	public String toText2() {
		return ("[" + isAlive + ", "+ isSick + ", "+ isImmune + "," + infectionDay + ", " + sickDaysremaining + "]");
	}

    public String toText() {
        return ("[isAlive: " + isAlive + ", " + "isSick: " + isSick + ", " + "isImmune: " + isImmune + ","
                + "infectionDay: " + infectionDay + ", " + "sickDaysremaining" + sickDaysremaining + "]");
    }

    public void setIsAlive(boolean Alive) {
        this.isAlive = Alive;
    }

    public void setIsSick(boolean Sick) {
        this.isSick = Sick;
    }

    public void setIsImmune(boolean Immune) {
        this.isImmune = Immune;
    }

    public void setInfectionDay(int Days) {
        this.infectionDay = Days;
    }

    public void setsickDays(int Days) {
        this.sickDaysremaining = Days;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public boolean getIsSick() {
        return isSick;
    }

    public int getInfectionDay() {
        return infectionDay;
    }

    public boolean getIsImmune() {
        return isImmune;
    }

    public int getSickDays() {
        return sickDaysremaining;
    }

}