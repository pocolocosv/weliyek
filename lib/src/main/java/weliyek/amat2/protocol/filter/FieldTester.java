/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.amat2.protocol.filter;

import java.util.Objects;
import java.util.function.Predicate;

import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.serialization.WkSzDefinition;

public abstract class FieldTester<
                        D extends WkSzDefinition<?,?>,
                        M extends WkSzFilterableSegment>
{

  private final D definition;
  private final Predicate<? super M> predicate;
  private String description;

  protected FieldTester(
    D protocolFieldDefinition,
    Predicate<? super M> predicate,
    String description) {
    this.definition = Objects.requireNonNull(protocolFieldDefinition);
    this.predicate = Objects.requireNonNull(predicate);
    this.description = null == description ? "" : description;
  }

  public abstract boolean canBeTestedAgainst(WkSzFilterableSegment segment);

  @SuppressWarnings("unchecked")
  boolean testIfProperSegmentOrFalseOtherwise(WkSzFilterableSegment segment) {
    if (canBeTestedAgainst(segment)) {
      return test((M)segment);
    }
    return false;
  }

  boolean test(M segment) {
    return this.predicate.test(segment);
  }

  public final D targetProtocolField() {
    return this.definition;
  }

  /**
   * Tells if expected segment to be tested requires to have a deserialized value.
   * @return True if deserialized value must be present in target segment. False otherwise.
   */
  public abstract boolean deserializedIsRequired();

  static WkSzDefinition<?,?>
  extractProtocolDefinitionFrom(WkSzFilterableSegment segment) {
    if (isSegmentAReadingOperation(segment)) {
      return extractDefinition((WkSzPacketReaderOperation<?,?,?,?,?>)segment);
    } else if (isSegmentAPacketField(segment)) {
      return extractDefinition((WkSzPacketReaderField<?,?,?>)segment);
    }
    throw new IllegalArgumentException();
  }

  protected static boolean isSegmentAReadingOperation(WkSzFilterableSegment segment) {
    return segment instanceof WkSzPacketReaderOperation;
  }

  protected static boolean isSegmentAPacketField(WkSzFilterableSegment segment) {
    return segment instanceof WkSzPacketReaderField;
  }

  protected static WkSzDefinition<?,?> extractDefinition(
    WkSzPacketReaderOperation<?,?,?,?,?> operationSegment) {
    return operationSegment.definition();
  }

  protected static WkSzDefinition<?,?> extractDefinition(
    WkSzPacketReaderField<?,?,?> packetfield) {
    return packetfield.structComponent().definition();
  }

  public String description() {
    return this.description;
  }

  @Override
  public String toString() {
    return description();
  }

}
