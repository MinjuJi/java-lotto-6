package domain;

public enum Ranking {
    FIRST(2_000_000_000L),
    SECOND(30_000_000L),
    THIRD(1_500_000L),
    FOURTH(50_000L),
    FIFTH(5_000L);

    final private long prize;

    Ranking(long prize) {
        this.prize = prize;
    }

    public long getPrize() {
        return prize;
    }
}
