package nro;

public final class TaskOrder {
   public int taskId;
   public int count;
   public short maxCount;
   public String name;
   public String description;
   public int killId;

   public TaskOrder(byte taskIdValue, short countValue, short maxCountValue, String nameValue, String descriptionValue, byte killIdValue, byte mapId) {
      this.count = countValue;
      this.maxCount = maxCountValue;
      this.taskId = taskIdValue;
      this.name = nameValue;
      this.description = descriptionValue;
      this.killId = killIdValue;
   }
}
