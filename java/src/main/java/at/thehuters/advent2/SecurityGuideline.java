package at.thehuters.advent2;

public class SecurityGuideline {

    int min;
    int max;
    char pattern;

    public SecurityGuideline(int min, int max, char pattern){
        this.min=min;
        this.max=max;
        this.pattern=pattern;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public char getPattern() {
        return pattern;
    }

    public boolean check(String password){
        var matchedChars = password.chars().filter(ch -> ch == pattern).count();
        return matchedChars >= min && matchedChars <= max;
    }

    public boolean checkPart2(String password){
        var firstMatch = password.charAt(min-1);
        var secondMatch = password.charAt(max-1);
        return firstMatch == pattern && secondMatch != pattern
                || firstMatch != pattern && secondMatch == pattern;
    }

}
