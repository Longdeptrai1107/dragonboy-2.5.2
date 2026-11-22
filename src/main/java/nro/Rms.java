package nro;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;

public final class Rms {
   public static String RMS_ResVersion = "ResVersion";
   public static String RMS_clienttype = "clienttype";
   public static String RMS_lastZoomlevel = "lastZoomlevel";
   public static String RMS_userAo = "userAo";
   public static String RMS_acc = "acc";
   public static String RMS_pass = "pass";
   public static String RMS_check = "check";

   public static void saveRMS(String filename, byte[] data) {
      try {
         RecordStore recordStore;
         if ((recordStore = RecordStore.openRecordStore("vj" + filename, true)).getNumRecords() > 0) {
            recordStore.setRecord(1, data, 0, data.length);
         } else {
            recordStore.addRecord(data, 0, data.length);
         }

         recordStore.closeRecordStore();
      } catch (Exception ignoredException) {
         Res.outz("bi loi save -------------------------" + filename);
      }
   }

   public static void deleteRecord(String name) {
      for (int index = 0; index < RecordStore.listRecordStores().length; index++) {
         if (RecordStore.listRecordStores().equals(name)) {
            try {
               RecordStore.deleteRecordStore(name);
            } catch (RecordStoreNotFoundException recordStoreNotFoundException) {
               recordStoreNotFoundException.printStackTrace();
            } catch (RecordStoreException recordStoreException) {
               recordStoreException.printStackTrace();
            }
         }
      }
   }

   public static void clearAll() {
      Res.outz("CLEAR ALL");

      try {
         String[] texts;
         if ((texts = RecordStore.listRecordStores()) != null) {
            for (int index = 0; index < texts.length; index++) {
               if (texts[index] != null
                  && !texts[index].equals("vj2.5.2")
                  && !texts[index].equals("2.5.2")
                  && !texts[index].equals("vj" + ServerListScreen.RMS_svselect)
                  && !texts[index].equals(ServerListScreen.RMS_svselect)
                  && !texts[index].equals("vj" + RMS_acc)
                  && !texts[index].equals(RMS_acc)
                  && !texts[index].equals("vj" + RMS_pass)
                  && !texts[index].equals(RMS_pass)
                  && !texts[index].equals("vj" + RMS_check)
                  && !texts[index].equals(RMS_check)) {
                  RecordStore.deleteRecordStore(texts[index]);
               }
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public static byte[] loadRMS(String filename) {
      try {
         RecordStore recordStore;
         byte[] record = (recordStore = RecordStore.openRecordStore("vj" + filename, false)).getRecord(1);
         recordStore.closeRecordStore();
         return record;
      } catch (Exception ignoredException) {
         return null;
      }
   }

   public static void saveRMSInt(String file, int x) {
      try {
         saveRMS(file, new byte[]{(byte)x});
      } catch (Exception ignoredException) {
      }
   }

   public static void saveRMSString(String filename, String data) {
      try {
         saveRMS(filename, data.getBytes("UTF-8"));
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public static String loadRMSString(String fileName) {
      byte[] storedFileName;
      if ((storedFileName = loadRMS(fileName)) == null) {
         return null;
      }

      try {
         return new String(storedFileName, "UTF-8");
      } catch (Exception ignoredException) {
         return new String(storedFileName);
      }
   }

   public static int loadRMSInt(String file) {
      byte[] storedFile;
      return (storedFile = loadRMS(file)) == null ? -1 : storedFile[0];
   }
}
