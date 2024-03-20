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
package weliyek.serialization.filter;

import java.util.Objects;
import java.util.function.Predicate;

import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSerdeDTreeNodeDataReader;

public abstract class WkSrlzPacketNodePredicate<
                        D extends WkSerdeDTreeNodeStructDefinition<?>,
                        M extends WkSerdeDTreeNodeDataFilterable>
{

  private final D definition;
  private final Predicate<? super M> predicate;
  private String description;

  protected WkSrlzPacketNodePredicate(
    D structDefinition,
    Predicate<? super M> predicate,
    String description) {
    this.definition = Objects.requireNonNull(structDefinition);
    this.predicate = Objects.requireNonNull(predicate);
    this.description = null == description ? "" : description;
  }

  public abstract boolean canBeTestedAgainst(WkSerdeDTreeNodeDataFilterable node);

  @SuppressWarnings("unchecked")
  boolean testIfProperSegmentOrFalseOtherwise(WkSerdeDTreeNodeDataFilterable node) {
    if (canBeTestedAgainst(node)) {
      return test((M)node);
    }
    return false;
  }

  boolean test(M node) {
    return this.predicate.test(node);
  }

  public final D targetProtocolField() {
    return this.definition;
  }

  /**
   * Tells if expected segment to be tested requires to have a deserialized value.
   * @return True if deserialized value must be present in target segment. False otherwise.
   */
  public abstract boolean deserializedIsRequired();

  static WkSerdeDTreeNodeStructDefinition<?>
  extractProtocolDefinitionFrom(WkSerdeDTreeNodeDataFilterable node) {
    if (isAnInputPacketReadingNode(node)) {
      return extractDefinition((WkSerdeDTreeNodeDataReader<?,?,?,?,?>)node);
    } else if (isAnInputPacketFieldNode(node)) {
      return extractDefinition((WkSrlzInputPacketFieldFrameNode<?,?,?>)node);
    }
    throw new IllegalArgumentException();
  }

  protected static boolean isAnInputPacketReadingNode(WkSerdeDTreeNodeDataFilterable node) {
    return node instanceof WkSerdeDTreeNodeDataReader;
  }

  protected static boolean isAnInputPacketFieldNode(WkSerdeDTreeNodeDataFilterable node) {
    return node instanceof WkSrlzInputPacketFieldFrameNode;
  }

  protected static WkSerdeDTreeNodeStructDefinition<?> extractDefinition(
    WkSerdeDTreeNodeDataReader<?,?,?,?,?> opNode) {
    return opNode.definition();
  }

  protected static WkSerdeDTreeNodeStructDefinition<?> extractDefinition(
    WkSrlzInputPacketFieldFrameNode<?,?,?> fieldNode) {
    return fieldNode.structComponent().definition();
  }

  public String description() {
    return this.description;
  }

  @Override
  public String toString() {
    return description();
  }

}
