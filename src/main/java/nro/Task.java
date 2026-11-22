package nro;

public final class Task {
   public int index;
   public short[] counts;
   public short taskId;
   public String[] names;
   public String[] details;
   public String[] subNames;
   public String[] contentInfo;
   public short count;

   public Task(short taskIdValue, byte indexValue, String name, String detail, String[] subNamesValue, short[] countsValue, short countValue, String[] contentInfoValue) {
      this.taskId = taskIdValue;
      this.index = indexValue;
      this.names = mFont.tahoma_7b_green2.splitFontArray(name, Panel.WIDTH_PANEL - 20);
      this.details = mFont.tahoma_7.splitFontArray(detail, Panel.WIDTH_PANEL - 20);
      this.subNames = subNamesValue;
      this.counts = countsValue;
      this.count = countValue;
      this.contentInfo = contentInfoValue;
   }
}
