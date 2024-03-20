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
import java.util.function.Predicate;

import weliyek.serialization.filter.WkSrlzPacketNodePredicate;

public abstract class WkSrlzStructComponentFrameNodeCore<
                        T,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XD extends WkSerdeDTreeNodeStructDefinition<T>,
                        XO extends WkSerdeDTreeNodeDataReader<T,XS,?,?,XD>,
                        AXBC extends WkSzInputBytestreamBase<?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YD extends WkSerdeDTreeNodeStructDefinition<T>,
                        YO extends WkSerdeDTreeNodeDataWriter<T,YS,?,?,YD>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        D extends WkSerdeDTreeNodeStructDefinition<T>>
    implements WkSerdeDTreeNodeStructComponent<D>
{

  public static final char FIELD_NAME_SEPARATOR = '.';

  private final String label;
  private final WkSrlzStructDefinitionFrameNodeCore<T,XS,?,?,XD,XO,AXBC,YS,?,?,YD,YO,AYBC,D,?> definitionCore;

  private String name;

  private final WkSerdeDTreeNodeStructComponent<D> body;

  protected WkSrlzStructComponentFrameNodeCore(
    String label,
    WkSrlzStructDefinitionFrameNodeCoreFactory<T,XS,XD,XO,AXBC,YS,YD,YO,AYBC,D> definitionFactory) {
    this.label = Objects.requireNonNull(label);
    this.body = new WkSerdeDTreeNodeStructComponent<D>() {

      @Override
      public String label() {
        return WkSrlzStructComponentFrameNodeCore.this.label();
      }

      @Override
      public D definition() {
        return WkSrlzStructComponentFrameNodeCore.this.definition();
      }

      @Override
      public String name() {
        return WkSrlzStructComponentFrameNodeCore.this.name();
      }

      @Override
      public WkSrlzPacketNodePredicate<?, ?>
          makeTester(Predicate<WkSerdeDTreeNodeDataComponent<?, ?, ?>> test, String description) {
        return WkSrlzStructComponentFrameNodeCore.this.makeTester(test, description);
      }
    };
    this.definitionCore = Objects.requireNonNull(definitionFactory).apply(this);
    this.definitionCore.initialize();
  }

  @Override
  public final String label() {
    return this.label;
  }

  public WkSerdeDTreeNodeStructComponent<D> asProtocolField() {
    return this.body;
  }

  protected abstract WkSrlzStructDefinitionFrameNodeCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore();

  @Override
  public D definition() {
    return definitionCore().definition();
  }

  public WkSrlzStructDefinitionFrameNodeCore<T,XS,?,?,XD,XO,AXBC,YS,?,?,YD,YO,AYBC,D,?> definitionCore() {
    return this.definitionCore;
  }

  private String generateName() {
    StringBuilder strB = new StringBuilder();
    if (null == parentDefinitionCore()) {
      strB.append(asProtocolField().type().name());
      strB.append('(');
      strB.append(hashCode());
      strB.append(')');
      strB.append(':');
      strB.append(label());
      return  strB.toString();
    } else {
      strB.append(parentDefinitionCore().name());
      strB.append(WkSrlzStructComponentFrameNodeCore.FIELD_NAME_SEPARATOR);
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
  public WkSrlzPacketNodePredicate<?, ?> makeTester(Predicate<WkSerdeDTreeNodeDataComponent<?, ?, ?>> test, String description) {
    // TODO Auto-generated method stub
    return null;
  }

}
