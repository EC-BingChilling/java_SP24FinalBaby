public class Baby implements Comparable<Baby> {
    private String name;
    private int rank;
    private int count;

    public Baby(String name, int rank, int count) {
        this.name = name;
        this.rank = rank;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(Baby other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + ": " + rank + ": " + count;
    }
}
