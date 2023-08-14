package weliyek.serialization;

public class WkSzSegmentException extends WkSzException
{

  private static final long serialVersionUID = 2018_03_05_000L;

  private final WkSrlzFrameNode segment;

  public WkSzSegmentException(WkSrlzFrameNode segment) {
    this.segment = segment;
  }

  public WkSzSegmentException(WkSrlzFrameNode segment, String message) {
    super(message);
    this.segment = segment;
  }

  public WkSzSegmentException(WkSrlzFrameNode segment, Throwable cause) {
    super(cause);
    this.segment = segment;
  }

  public WkSzSegmentException(WkSrlzFrameNode segment, String message, Throwable cause) {
    super(message, cause);
    this.segment = segment;
  }

  public WkSzSegmentException(
    WkSrlzFrameNode segment,
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.segment = segment;
  }

  public WkSrlzFrameNode getSegment() {
    return this.segment;
  }

}
