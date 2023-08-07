package weliyek.serialization.base;

import weliyek.amat.base.WkSzSegment;

public class WkSzSegmentException extends WkSzException
{

  private static final long serialVersionUID = 2018_03_05_000L;

  private final WkSzSegment segment;

  public WkSzSegmentException(WkSzSegment segment) {
    this.segment = segment;
  }

  public WkSzSegmentException(WkSzSegment segment, String message) {
    super(message);
    this.segment = segment;
  }

  public WkSzSegmentException(WkSzSegment segment, Throwable cause) {
    super(cause);
    this.segment = segment;
  }

  public WkSzSegmentException(WkSzSegment segment, String message, Throwable cause) {
    super(message, cause);
    this.segment = segment;
  }

  public WkSzSegmentException(
    WkSzSegment segment,
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.segment = segment;
  }

  public WkSzSegment getSegment() {
    return this.segment;
  }

}
