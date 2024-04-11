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

import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.number.WkSerdeDtreeNumberReader;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;

public class WkSerdeDynamicByteArrayReader<
                        ZT extends Number,
                        ZXO extends WkSerdeDtreeNumberReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberDefinition<ZT>>
    implements WkSerdeDtreeByteArrayReader<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>,
               WkSerdeDtreeDynamicPrimitiveArrayReader<
                        WkByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT,
                        ZXO,
                        ZXD,
                        WkSerdeDtreeVariableSizeByteArrayReader,
                        WkSerdeDtreeVariableSizeByteArray>
{

  final WkSerdeDtreeDynamicPrimitiveArrayReaderCore<
                        WkByteArray,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT, ZXO, ZXD,
                        WkSerdeDtreeVariableSizeByteArrayReader,
                        WkSerdeDtreeVariableSizeByteArray> operationCore;

  WkSerdeDynamicByteArrayReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataInputComponentCore<
      WkByteArray,?,WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?> deserializingfieldCore,
    WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
      WkByteArray,WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
      WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,?,?,ZT,ZXD,ZXO,?,?,?,
      WkSerdeDtreeVariableSizeByteArray,WkSerdeDtreeVariableSizeByteArrayReader,?,?,?,
      ?> definitionCore) {
    this.operationCore = new WkSerdeDtreeDynamicPrimitiveArrayReaderCore<
                                WkByteArray,
                                WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                                WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                ZT, ZXO, ZXD,
                                WkSerdeDtreeVariableSizeByteArrayReader,
                                WkSerdeDtreeVariableSizeByteArray>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this);
  }

  /*
  public DynamicByteArrayDeserializing(
    int i,
    OperationSettings xs,
    InputBytestream<?> axb,
    DeserializingFieldCore<ByteArrayWrapper, ?, ? extends DynamicByteArray<ZX, ZXO, ?, ?, ZD>, ?, ?, ?> xkc,
    SimplifiedDynamicPrimitiveArrayDefinitionCore<ByteArrayWrapper, DynamicByteArrayDeserializing<ZX, ZXO, ZD>, DynamicByteArraySerialzing<ZY, ZYO, ZD>, ZX, ZXO, ZY, ZYO, ZD, VariableSizeByteArrayDeserializing, VariableSizeByteArraySerializing, VariableSizeByteArray, DynamicByteArray<ZX, ZXO, ZY, ZYO, ZD>> dc) {
    // TODO Auto-generated constructor stub
  }
  */

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD> definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
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
  public WkSerdeDtreeNodeDataInputComponent<WkByteArray,WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public Optional<WkSerdeDtreeNodeDataInputComponent<ZT, ZXD, ZXO>> size() {
    return this.operationCore.size();
  }

  @Override
  public
  Optional<WkSerdeDtreeNodeDataInputComponent<WkByteArray, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayReader>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public int getRequestedLength() {
    return size().get().firstOperation().get().result().get().serializable().get().intValue();
  }

}
