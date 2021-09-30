package tdd;

public class ChangeModule {

    private int changes;

    public void put(int change) {
        this.changes += change;
    }

    public int getChanges() {
        return changes;
    }

    public void withdraw(int change) {
        if ((this.changes - change) < 0) throw new IllegalStateException();
        this.changes -= change;
    }
}