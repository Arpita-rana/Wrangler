public class TimeDuration extends Token {
    private final long millis;
  
    public TimeDuration(String value) {
      super("TIME_DURATION", value);
      this.millis = parse(value);
    }
  
    private long parse(String input) {
      if (input.endsWith("ms")) return Long.parseLong(input.replace("ms", ""));
      if (input.endsWith("s")) return Long.parseLong(input.replace("s", "")) * 1000;
      if (input.endsWith("m")) return Long.parseLong(input.replace("m", "")) * 60000;
      if (input.endsWith("h")) return Long.parseLong(input.replace("h", "")) * 3600000;
      return 0;
    }
  
    public long getMillis() {
      return millis;
    }
  }
  