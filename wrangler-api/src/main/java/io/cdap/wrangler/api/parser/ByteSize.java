public class ByteSize extends Token {
    private final long bytes;
  
    public ByteSize(String value) {
      super("BYTE_SIZE", value);
      this.bytes = parse(value);
    }
  
    private long parse(String input) {
      String normalized = input.toUpperCase();
      double num = Double.parseDouble(normalized.replaceAll("[A-Z]+", ""));
      if (normalized.endsWith("KB")) return (long) (num * 1024);
      if (normalized.endsWith("MB")) return (long) (num * 1024 * 1024);
      if (normalized.endsWith("GB")) return (long) (num * 1024 * 1024 * 1024);
      return 0;
    }
  
    public long getBytes() {
      return bytes;
    }
  }
  