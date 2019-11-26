package com.locadora.helper;

import java.util.function.Consumer;
import javax.annotation.Nonnull;

public final class Throwing {

  private Throwing() {
  }

  @Nonnull
  public static <T> Consumer<T> rethrow(@Nonnull final ThrowingConsumer<T> consumer) {
    return consumer;
  }

  /**
   * The compiler sees the signature with the throws T inferred to a RuntimeException type, so it
   * allows the unchecked exception to propagate.
   * <p>
   * http://www.baeldung.com/java-sneaky-throws
   */
  @SuppressWarnings("unchecked")
  @Nonnull
  public static <E extends Throwable> void sneakyThrow(@Nonnull Throwable ex) throws E {
    throw (E) ex;
  }
}
