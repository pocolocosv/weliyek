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
package weliyek.util.array;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;

public class WkSerdeDynamicByteArrayWriter<
                        ZT extends Number,
                        ZYO extends WkSerdeDtreeNumberWriter<ZT,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberDefinition<ZT>>
    implements WkSerdeDtreeByteArrayWriter<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>>,
               WkSerdeDtreeDynamicPrimitiveArrayWriter<
                        WkByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        ZT, ZYO, ZYD,
                        WkSerdeDtreeVariableSizeByteArrayWriter,
                        WkSerdeDtreeVariableSizeByteArray>
{

  final WkSerdeDtreeDynamicPrimitiveArrayWriterCore<
                        WkByteArray,
                        WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                        WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        ZT, ZYO, ZYD,
                        WkSerdeDtreeVariableSizeByteArrayWriter,
                        WkSerdeDtreeVariableSizeByteArray> operationCore;

  WkSerdeDynamicByteArrayWriter(
    int index,
    WkByteArray serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<
      WkByteArray,?,WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,?,?,?> serializingfieldCore,
    WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
      WkByteArray,?,?,WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
      WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,ZT,?,?,ZYD,ZYO,?,?,?,
      WkSerdeDtreeVariableSizeByteArray,WkSerdeDtreeVariableSizeByteArrayWriter,?,
      ?> definitionCore) {
    this.operationCore = new WkSerdeDtreeDynamicPrimitiveArrayWriterCore<
                                WkByteArray,
                                WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                                WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                ZT, ZYO, ZYD,
                                WkSerdeDtreeVariableSizeByteArrayWriter,
                                WkSerdeDtreeVariableSizeByteArray>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    serializingfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public WkByteArray serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD> definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkByteArray>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeNodeDataOutputComponent<WkByteArray,WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public Optional<WkSerdeDtreeNodeDataOutputComponent<ZT, ZYD, ZYO>> size() {
    return this.operationCore.size();
  }

  @Override
  public
  Optional<WkSerdeDtreeNodeDataOutputComponent<WkByteArray, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayWriter>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public int getRequestedLength() {
    return serializable().getLength();
  }

}
