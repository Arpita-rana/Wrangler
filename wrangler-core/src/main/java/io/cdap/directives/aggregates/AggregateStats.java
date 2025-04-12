public class AggregateStats implements Directive {
    private String sizeCol, timeCol, outSizeCol, outTimeCol;
    private long totalSize = 0;
    private long totalTime = 0;
    private int rowCount = 0;
  
    public AggregateStats() {}
  
    @Override
    public UsageDefinition define() {
      return UsageDefinition.builder()
        .name("aggregate-stats")
        .define("sizeCol", "string")
        .define("timeCol", "string")
        .define("outSizeCol", "string")
        .define("outTimeCol", "string")
        .build();
    }
  
    @Override
    public void initialize(Arguments args) {
      sizeCol = ((Text) args.value("sizeCol")).value();
      timeCol = ((Text) args.value("timeCol")).value();
      outSizeCol = ((Text) args.value("outSizeCol")).value();
      outTimeCol = ((Text) args.value("outTimeCol")).value();
    }
  
    @Override
    public List<Row> execute(List<Row> rows, ExecutorContext ctx) {
      for (Row row : rows) {
        String sizeStr = row.getValue(sizeCol).toString();
        String timeStr = row.getValue(timeCol).toString();
  
        totalSize += new ByteSize(sizeStr).getBytes();
        totalTime += new TimeDuration(timeStr).getMillis();
        rowCount++;
      }
  
      Row output = new Row();
      output.add(outSizeCol, totalSize / (1024.0 * 1024)); // MB
      output.add(outTimeCol, totalTime / 1000.0); // Seconds
  
      return Collections.singletonList(output);
    }
  
    @Override
    public void destroy() {}
  }
  