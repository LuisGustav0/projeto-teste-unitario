package com.locadora.helper;

import java.util.function.Consumer;

public class Conditional {

  private Boolean value;

  public static Conditional of(Boolean value) {
    return new Conditional(value);
  }

  private Conditional(Boolean value) {
    this.value = value;
  }

  private boolean isTrue() {
    return this.value != null && this.value;
  }

  private boolean isFalse() {
    return this.value != null && !this.value;
  }

  public Conditional ifNull(Consumer<Boolean> consumer) {
    if (this.value == null) {
      consumer.accept(null);
    }

    return this;
  }

  public void ifNullPresent(Consumer<Boolean> consumer) {
    if (this.value == null) {
      consumer.accept(null);
    }
  }

  public Conditional ifTrue(Consumer<Boolean> consumer) {
    if (isTrue())
      consumer.accept(value);

    return this;
  }

  public void ifTruePresent(Consumer<Boolean> consumer) {
    if (isTrue())
      consumer.accept(value);
  }

  public void orElse(Consumer<Boolean> consumer) {
    if (isFalse())
      consumer.accept(value);
  }
}
