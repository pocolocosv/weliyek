package weliyek.serialization;

import weliyek.serialization.tree.WkSerdeDTreeNode;

public class WkSzSegmentException extends WkSzException
{

  private static final long serialVersionUID = 2018_03_05_000L;

  private final WkSerdeDTreeNode segment;

  public WkSzSegmentException(WkSerdeDTreeNode segment) {
    this.segment = segment;
  }

  public WkSzSegmentException(WkSerdeDTreeNode segment, String message) {
    super(message);
    this.segment = segment;
  }

  public WkSzSegmentException(WkSerdeDTreeNode segment, Throwable cause) {
    super(cause);
    this.segment = segment;
  }

  public WkSzSegmentException(WkSerdeDTreeNode segment, String message, Throwable cause) {
    super(message, cause);
    this.segment = segment;
  }

  public WkSzSegmentException(
    WkSerdeDTreeNode segment,
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.segment = segment;
  }

  public WkSerdeDTreeNode getSegment() {
    return this.segment;
  }

}
