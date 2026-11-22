package nro;

final class SessionHandshakeWatchdog implements Runnable {
   private Session_ME session;
   private final long generation;

   SessionHandshakeWatchdog(Session_ME sessionValue, long generationValue) {
      this.session = sessionValue;
      this.generation = generationValue;
   }

   public final void run() {
      try {
         Thread.sleep(20000L);
      } catch (InterruptedException ignoredInterruptedException) {
         return;
      }

      if (this.generation == this.session.getConnectionGeneration()
         && (this.session.connecting || this.session.connected && !this.session.getKeyComplete)) {
         Session_ME.failConnection(this.session, this.generation, "socket/key handshake timed out");
      }
   }
}
