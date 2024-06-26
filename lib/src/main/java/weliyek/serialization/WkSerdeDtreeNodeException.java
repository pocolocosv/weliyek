package weliyek.serialization;

import weliyek.serialization.tree.WkSerdeDtreeNode;

public class WkSerdeDtreeNodeException extends WkSerdeException
{

  private static final long serialVersionUID = 2018_03_05_000L;

  private final WkSerdeDtreeNode segment;

  public WkSerdeDtreeNodeException(WkSerdeDtreeNode segment) {
    this.segment = segment;
  }

  public WkSerdeDtreeNodeException(WkSerdeDtreeNode segment, String message) {
    super(message);
    this.segment = segment;
  }

  public WkSerdeDtreeNodeException(WkSerdeDtreeNode segment, Throwable cause) {
    super(cause);
    this.segment = segment;
  }

  public WkSerdeDtreeNodeException(WkSerdeDtreeNode segment, String message, Throwable cause) {
    super(message, cause);
    this.segment = segment;
  }

  public WkSerdeDtreeNodeException(
    WkSerdeDtreeNode segment,
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.segment = segment;
  }

  public WkSerdeDtreeNode getSegment() {
    return this.segment;
  }

}
