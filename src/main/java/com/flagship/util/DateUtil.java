package com.flagship.util;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {

  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
  private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;
  private static final String offset = "+06:00";

  private DateUtil() {
  }

  public static ZonedDateTime timeNow() {
    return ZonedDateTime.now(ZONE_OFFSET);
  }

  public static ZonedDateTime fromEpochMilli(Long milli) {
    return milli == null ? null : ZonedDateTime.ofInstant(Instant.ofEpochMilli(milli), ZONE_OFFSET);
  }

  public static Long timeNowToEpochMilli() {
    return toEpochMilli(timeNow());
  }

  public static Long toEpochMilli(ZonedDateTime zonedDateTime) {
    return zonedDateTime == null ? null : zonedDateTime.toInstant().toEpochMilli();
  }

  public static ZonedDateTime getZoneDateTime(Date dateToConvert) {
    return ZonedDateTime.ofInstant(dateToConvert.toInstant(), ZONE_OFFSET);
  }

  public static ZonedDateTime getZoneDateTime(String date) {
    return ZonedDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZONE_OFFSET));
  }

  public static ZonedDateTime getZoneDateTime(String date, String format) {
    return getZoneDateTime(date, DateTimeFormatter.ofPattern(format));
  }

  public static ZonedDateTime getZoneDateTime(String date, DateTimeFormatter dateTimeFormatter) {
    return StringUtils.isBlank(date) ? null : ZonedDateTime.parse(date, dateTimeFormatter);
  }

  public static String getFormattedDate(Long milli) {
    return getFormattedDate(fromEpochMilli(milli));
  }

  public static String getFormattedDate(Long milli, String format) {
    return DateTimeFormatter.ofPattern(format).format(fromEpochMilli(milli));
  }

  public static String getFormattedDate(ZonedDateTime dateTime) {
    if (dateTime == null) {
      return null;
    } else {
      return FORMATTER.format(dateTime);
    }
  }

  public static String getFormattedDate(ZonedDateTime dateTime, String format) {
    return DateTimeFormatter.ofPattern(format).format(dateTime);
  }

  public static ZonedDateTime getTimeFromOffset(String zoneOffset) {
    return ZonedDateTime.now(ZoneId.of(zoneOffset));
  }

  public static Long getEpochTimeFromOffset(String zoneOffset) {
    return toEpochMilli(getTimeFromOffset(zoneOffset));
  }

  public static ZonedDateTime fromEpocMilliToDate(Long time) {
    return fromEpochMilli(time).truncatedTo(ChronoUnit.DAYS);
  }

  public static ZonedDateTime fromTimeNowToDate() {
    return timeNow().truncatedTo(ChronoUnit.DAYS);
  }

  public static String getFormattedTime(Long milli) {
    return milli == null ? null : getFormattedTime(fromEpochMilli(milli));
  }

  public static String getFormattedTimeUsingZoneOffset(Long milli) {
    return milli == null ? null : getFormattedTime(epochMilliToZoneDateTimeUsingOffset(milli));
  }

  public static String getFormattedDateUsingZoneOffset(Long milli) {
    return milli == null ? null : getFormattedDate(epochMilliToZoneDateTimeUsingOffset(milli));
  }

  public static String getFormattedTime(ZonedDateTime dateTime) {
    return TIME_FORMATTER.format(dateTime);
  }

  public static ZonedDateTime epochMilliToZoneDateTimeUsingOffset(Long milli) {
    if (null == milli) {
      return null;
    } else {
      return ZonedDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneId.of(offset));
    }
  }
}