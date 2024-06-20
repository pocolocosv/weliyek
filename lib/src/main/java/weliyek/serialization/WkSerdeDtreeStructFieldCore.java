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
package weliyek.serialization;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import weliyek.serialization.filter.WkSrlzPacketNodePredicate;
import weliyek.serialization.tree.WkSerdeDtreeStruct;

public abstract class WkSerdeDtreeStructFieldCore<
                        T,
                        XO extends WkSerdeDtreeMsgReader<T,?,?,?,?>,
                        YO extends WkSerdeDtreeMsgWriter<T,?,?,?,?>,
                        D extends WkSerdeDtreeStructDefinition<T>,
                        DC extends WkSerdeDtreeStructDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? super D,?>>
    implements WkSerdeDtreeStructField<D>
{

  public static final char FIELD_NAME_SEPARATOR = '.';

  private final String label;
  private final DC definitionCore;

  private String name;

  protected WkSerdeDtreeStructFieldCore(
    String label,
    WkSrlzStructDefinitionFrameNodeCoreFactory<? extends DC> definitionFactory) {
    this.label = Objects.requireNonNull(label);
    this.definitionCore = Objects.requireNonNull(definitionFactory).apply(this);
    this.definitionCore.initialize();
  }

  @Override
  public final String label() {
    return this.label;
  }

  abstract public WkSerdeDtreeStructField<D> asProtocolField();

  abstract protected Optional<WkSerdeDtreeStructDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?>> parentDefinitionCore();

  @Override
  @SuppressWarnings("unchecked")
  public D definition() {
    return (D) definitionCore().definition();
  }

  DC definitionCore() {
    return this.definitionCore;
  }

  private String generateName() {
    StringBuilder strB = new StringBuilder();
    if (parentDefinitionCore().isEmpty()) {
      strB.append(WkSerdeDtreeStruct.class.getSimpleName());
      strB.append('(');
      strB.append(hashCode());
      strB.append(')');
      strB.append(':');
      strB.append(label());
      return  strB.toString();
    } else {
      strB.append(parentDefinitionCore().get().name());
      strB.append(WkSerdeDtreeStructFieldCore.FIELD_NAME_SEPARATOR);
      strB.append(label());
      return strB.toString();
    }
  }

  @Override
  public String name() {
    if (null == this.name) {
      this.name = generateName();
    }
    return this.name;
  }

  @Override
  public String toString() {
    return name();
  }

  @Override
  public WkSrlzPacketNodePredicate<?, ?> makeTester(Predicate<WkSerdeDtreeMsgField<?, ?, ? super D>> test, String description) {
    // TODO Auto-generated method stub
    return null;
  }

}
