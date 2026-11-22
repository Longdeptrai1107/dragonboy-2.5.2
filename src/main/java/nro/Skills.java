package nro;

public final class Skills {
   public static MyHashTable skills = new MyHashTable("VSKILL");

   public static void add(Skill skill) {
      skills.put(new Short(skill.skillId), skill);
   }

   public static Skill get(short skillId) {
      return (Skill)skills.get(new Short(skillId));
   }
}
