package com.december.bikemanager.data;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

public class Utility {
  private static Random random = new Random();

  public static boolean is(Object value) {
    if (value == null) {
      return false;
    }

    if (value instanceof Boolean) {
      return (Boolean) value;
    }

    if (value instanceof Number) {
      return ((Number) value).doubleValue() != 0;
    }

    if (value instanceof String) {
      return !((String) value).isEmpty();
    }

    if (value instanceof Collection) {
      return !((Collection<?>) value).isEmpty();
    }

    if (value instanceof Map) {
      return !((Map<?, ?>) value).isEmpty();
    }

    // For arrays
    if (value.getClass().isArray()) {
      return java.lang.reflect.Array.getLength(value) > 0;
    }

    // For all other objects, consider them truthy
    return true;
  }

  public static boolean isnt(Object value) {
    return !is(value);
  }

  public static int randInt(int bound) {
    return random.nextInt(bound);
  }

  // public static boolean isInteger(String str, boolean allowNegative) {
  // try {
  // double value = Double.parseDouble(str);
  // int rounded = (int) Math.round(value);

  // return value == rounded && (allowNegative || value >= 0);
  // } catch (Exception e) {
  // return false;
  // }
  // }

  public static boolean isDouble(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
