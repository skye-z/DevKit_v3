package cc.stacks.devkit;

public enum DatePattern {

    COMPLETE("yyyy-MM-dd HH:mm:ss");

    private String Pattern;
    DatePattern(String Pattern){
        this.Pattern = Pattern;
    }

    public String getPattern() {
        return Pattern;
    }
}
