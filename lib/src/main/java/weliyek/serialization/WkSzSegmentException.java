package weliyek.serialization;

import weliyek.serialization.tree.WkSerdeTreeNode;

public class WkSzSegmentException extends WkSzException
{

  private static final long serialVersionUID = 2018_03_05_000L;

  private final WkSerdeTreeNode segment;

  public WkSzSegmentException(WkSerdeTreeNode segment) {
    this.segment = segment;
  }

  public WkSzSegmentException(WkSerdeTreeNode segment, String message) {
    super(message);
    this.segment = segment;
  }

  public WkSzSegmentException(WkSerdeTreeNode segment, Throwable cause) {
    super(cause);
    this.segment = segment;
  }

  public WkSzSegmentException(WkSerdeTreeNode segment, String message, Throwable cause) {
    super(message, cause);
    this.segment = segment;
  }

  public WkSzSegmentException(
    WkSerdeTreeNode segment,
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.segment = segment;
  }

  public WkSerdeTreeNode getSegment() {
    return this.segment;
  }

}
