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
import java.util.function.IntFunction;

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSerdeDTreeNodeStructDefinitionCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.number.WkSerdeDTreeNumberReader;
import weliyek.serialization.number.WkSerdeDTreeNumberWriter;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;

public class WkDynamicByteArraySrlzStructNode<
                        ZT extends Number,
                        ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ZXO extends WkSerdeDTreeNumberReader<ZT,WkSettingsSrlzPacketOperationData,?,?,ZXD>,
                        ZYD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ZYO extends WkSerdeDTreeNumberWriter<ZT,WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                        ZD extends WkSerdeDTreeNumberDefinition<ZT>>
    implements WkSerdeDTreeByteArrayDefinition,
               WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<
                        WkByteArray,
                        WkDynamicByteArraySrlzInputNode<ZT,ZXO,ZXD>,
                        WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>,
                        ZD,
                        WkSerdeDTreeVariableSizeByteArray>
{
  public static <ZX extends Number,
                 ZXD extends WkSerdeDTreeNumberDefinition<ZX>,
                 ZXO extends WkSerdeDTreeNumberReader<ZX,WkSettingsSrlzPacketOperationData,?,?,ZXD>,
                 ZYD extends WkSerdeDTreeNumberDefinition<ZX>,
                 ZYO extends WkSerdeDTreeNumberWriter<ZX,WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                 ZD extends WkSerdeDTreeNumberDefinition<ZX>>
  WkSrlzStruct<WkByteArray,
                  WkSettingsSrlzPacketOperationData,
                  WkDynamicByteArraySrlzStructNode<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                  WkDynamicByteArraySrlzInputNode<ZX,ZXO,ZXD>,
                  WkSzInputBytestreamBase<?>,
                  WkSettingsSrlzPacketOperationData,
                  WkDynamicByteArraySrlzStructNode<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                  WkDynamicByteArraySrlzOutputNode<ZX,ZYO,ZYD>,
                  WkSzOutputBytestreamBase<?>,
                  WkDynamicByteArraySrlzStructNode<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>
  newStruct(
    String label,
    String sizeLabel,
    String bytearrayLabel,
    int minLength,
    int maxLength,
    IntFunction<ZX> sizeWrapper,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZX,WkSettingsSrlzPacketOperationData,ZXD,ZXO,WkSzInputBytestreamBase<?>,
      WkSettingsSrlzPacketOperationData,ZYD,ZYO,WkSzOutputBytestreamBase<?>,ZD>  sizeDefinition)
  {
    return new WkSrlzStructComponentFrameNodeRootCore<WkByteArray,
        WkSettingsSrlzPacketOperationData,
        WkDynamicByteArraySrlzStructNode<ZX,ZXD,ZXO,?,?,? extends ZXD>,
        WkDynamicByteArraySrlzInputNode<ZX,ZXO,ZXD>,
        WkSzInputBytestreamBase<?>,
        WkSettingsSrlzPacketOperationData,
        WkDynamicByteArraySrlzStructNode<ZX,?,?,ZYD,ZYO,? extends ZYD>,
        WkDynamicByteArraySrlzOutputNode<ZX,ZYO,ZYD>,
        WkSzOutputBytestreamBase<?>,
        WkDynamicByteArraySrlzStructNode<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>(
                  label,
                  (pc) -> WkDynamicByteArraySrlzStructNode.newCore(
                              pc, sizeLabel, sizeWrapper, bytearrayLabel, minLength, maxLength, sizeDefinition),
                  WkSzCountingInputBytestream::new,
                  WkSzCountingOutputBytestream::new);
  }

  public static <ZX extends Number,
                 ZXD extends WkSerdeDTreeNumberDefinition<ZX>,
                 ZXO extends WkSerdeDTreeNumberReader<ZX,WkSettingsSrlzPacketOperationData,?,?,ZXD>,
                 ZYD extends WkSerdeDTreeNumberDefinition<ZX>,
                 ZYO extends WkSerdeDTreeNumberWriter<ZX,WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                 ZD extends WkSerdeDTreeNumberDefinition<ZX>>
  WkSerdeDTreeNodeStructDefinitionCore<WkByteArray,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkDynamicByteArraySrlzStructNode<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                        WkDynamicByteArraySrlzInputNode<ZX,ZXO,ZXD>,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkDynamicByteArraySrlzStructNode<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                        WkDynamicByteArraySrlzOutputNode<ZX,ZYO,ZYD>,
                        WkSzOutputBytestreamBase<?>,
                        WkDynamicByteArraySrlzStructNode<ZX,ZXD,ZXO,ZYD,ZYO,ZD>,?>
  newCore(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZX> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZX,WkSettingsSrlzPacketOperationData,ZXD,ZXO,WkSzInputBytestreamBase<?>,
      WkSettingsSrlzPacketOperationData,ZYD,ZYO,WkSzOutputBytestreamBase<?>,ZD> sizeDefinitionFactory)
  {
    return new WkDynamicByteArraySrlzStructNode<ZX,ZXD,ZXO,ZYD,ZYO,ZD>(componentCore, sizeLabel, wrapperSizeGetter, arrayLabel, minLength, maxLength, sizeDefinitionFactory).definitionCore;
  }

  private final WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
                        WkByteArray,
                        WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkDynamicByteArraySrlzInputNode<ZT,ZXO,ZXD>,
                        WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>,
                        ZT, ZXD, ZXO, ZYD, ZYO, ZD,
                        WkSerdeDTreeVariableSizeByteArray,
                        WkSerdeDTreeVariableSizeByteArrayReader,
                        WkSerdeDTreeVariableSizeByteArray,
                        WkSerdeDTreeVariableSizeByteArrayWriter,
                        WkSerdeDTreeVariableSizeByteArray,
                        WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,ZYD,ZYO,ZD>> definitionCore;

  WkDynamicByteArraySrlzStructNode(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZT> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT,WkSettingsSrlzPacketOperationData,ZXD,ZXO,WkSzInputBytestreamBase<?>,WkSettingsSrlzPacketOperationData,
      ZYD,ZYO,WkSzOutputBytestreamBase<?>,ZD> sizeComponentDefinitionFactory) {
    this.definitionCore = new WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
                                WkByteArray,
                                WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                WkDynamicByteArraySrlzInputNode<ZT,ZXO,ZXD>,
                                WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>,
                                ZT, ZXD, ZXO, ZYD, ZYO, ZD,
                                WkSerdeDTreeVariableSizeByteArray,
                                WkSerdeDTreeVariableSizeByteArrayReader,
                                WkSerdeDTreeVariableSizeByteArray,
                                WkSerdeDTreeVariableSizeByteArrayWriter,
                                WkSerdeDTreeVariableSizeByteArray,
                                WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                                    sizeLabel,
                                    wrapperSizeGetter,
                                    sizeComponentDefinitionFactory,
                                    arrayLabel,
                                    (pc) -> WkSerdeDTreeVariableSizeByteArray.newCore(minLength, maxLength, pc),
                                    (i,xs,axb,xkc,dc) -> new WkDynamicByteArraySrlzInputNode<ZT,ZXO,ZXD>(i,xs,axb,xkc,dc).operationCore,
                                    (i,y,ys,ayb,ykc,dc) -> new WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>(i,y,ys,ayb,ykc,dc).operationCore,
                                    componentCore,
                                    this,
                                    WkByteArray.class);
  }

  @Override
  public Class<WkByteArray> serializableClass() {
    return WkByteArray.class;
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public
      WkSerdeDTreeNodeStructComponentHandler<WkDynamicByteArraySrlzInputNode<ZT, ZXO, ZXD>, WkDynamicByteArraySrlzOutputNode<ZT, ZYO, ZYD>, ZD>
      size() {
    return this.definitionCore.size();
  }

  @Override
  public
      WkSerdeDTreeNodeStructComponentHandler<WkDynamicByteArraySrlzInputNode<ZT, ZXO, ZXD>, WkDynamicByteArraySrlzOutputNode<ZT, ZYO, ZYD>, WkSerdeDTreeVariableSizeByteArray>
      variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
