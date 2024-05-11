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

import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;

public class WkSerdeDynamicByteArray<
                        ZT extends Number,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                        ZD extends WkSerdeDtreeNumberStructDefinition<ZT>>
    implements WkSerdeDtreeByteArrayDefinition,
               WkSerdeDtreeDynamicPrimitiveArrayDefinition<
                        WkByteArray,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                        WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                        ZD,
                        WkSerdeDtreeVariableSizeByteArray>
{

  public static <ZT extends Number,
                 ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZXO extends WkSerdeDtreeNumberMsgReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                 ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                 ZD extends WkSerdeDtreeNumberStructDefinition<ZT>>
  WkSerdeDtreeStruct<WkByteArray,
                  WkSerdeDtreeOperationSettings,
                  WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                  WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                  WkSerdeDtreeBytestreamInputBase<?>,
                  WkSerdeDtreeOperationSettings,
                  WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                  WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                  WkSerdeDtreeBytestreamOutputBase<?>,
                  WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
  newStruct(
    String label,
    String sizeLabel,
    String bytearrayLabel,
    int minLength,
    int maxLength,
    IntFunction<ZT> sizeWrapper,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
    ? extends WkSerdeDtreeStructDefinitionCore<ZT, WkSerdeDtreeOperationSettings,
    ?,?,ZXD,?,ZXO,?,WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
    WkSerdeDtreeOperationSettings,?,?,ZYD,?,ZYO,?,
    WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
    ZD,?>> sizeFieldDefinitionFactory)
  {
    return new WkSerdeDtreeStructCore<WkByteArray,
                  WkSerdeDtreeOperationSettings,
                  WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                  WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                  WkSerdeDtreeBytestreamInputBase<?>,
                  WkSerdeDtreeOperationSettings,
                  WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                  WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                  WkSerdeDtreeBytestreamOutputBase<?>,
                  WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                      label,
                      (pc) -> WkSerdeDynamicByteArray.newCore(
                              pc, sizeLabel, sizeWrapper, bytearrayLabel, minLength, maxLength, sizeFieldDefinitionFactory),
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static <ZT extends Number,
                 ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZXO extends WkSerdeDtreeNumberMsgReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                 ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                 ZD extends WkSerdeDtreeNumberStructDefinition<ZT>>
  WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                WkByteArray,
                WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                ZT, ZXD, ZXO, ZYD, ZYO, ZD,
                WkSerdeDtreeVariableSizeByteArray,
                WkSerdeDtreeVariableSizeByteArrayReader,
                WkSerdeDtreeVariableSizeByteArray,
                WkSerdeDtreeVariableSizeByteArrayWriter,
                WkSerdeDtreeVariableSizeByteArray,
                WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
  newCore(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZT> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<ZT, WkSerdeDtreeOperationSettings,
      ?,?,ZXD,?,ZXO,?,WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
      WkSerdeDtreeOperationSettings,?,?,ZYD,?,ZYO,?,
      WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
      ZD,?>> sizeFieldDefinitionFactory) {
    return new WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>(componentCore, sizeLabel, wrapperSizeGetter, arrayLabel, minLength, maxLength, sizeFieldDefinitionFactory).definitionCore;
  }

  private final WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                        WkByteArray,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                        WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                        ZT, ZXD, ZXO, ZYD, ZYO, ZD,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeDtreeVariableSizeByteArrayReader,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeDtreeVariableSizeByteArrayWriter,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>> definitionCore;

  WkSerdeDynamicByteArray(WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZT> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<ZT, WkSerdeDtreeOperationSettings,
      ?,?,ZXD,?,ZXO,?,WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
      WkSerdeDtreeOperationSettings,?,?,ZYD,?,ZYO,?,
      WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
      ZD,?>> sizeComponentDefinitionFactory) {
    this.definitionCore = new WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                                WkByteArray,
                                WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                                WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                                ZT, ZXD, ZXO, ZYD, ZYO, ZD,
                                WkSerdeDtreeVariableSizeByteArray,
                                WkSerdeDtreeVariableSizeByteArrayReader,
                                WkSerdeDtreeVariableSizeByteArray,
                                WkSerdeDtreeVariableSizeByteArrayWriter,
                                WkSerdeDtreeVariableSizeByteArray,
                                WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                                    sizeLabel,
                                    wrapperSizeGetter,
                                    sizeComponentDefinitionFactory,
                                    arrayLabel,
                                    (pc) -> WkSerdeDtreeVariableSizeByteArray.newCore(minLength, maxLength, pc),
                                    (i,xs,axb,xkc,dc) -> new WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>(i,xs,axb,xkc,dc).operationCore,
                                    (i,y,ys,ayb,ykc,dc) -> new WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>(i,y,ys,ayb,ykc,dc).operationCore,
                                    componentCore,
                                    this,
                                    WkByteArray.class);
  }

  @Override
  public Class<WkByteArray> serializableClass() {
    return WkByteArray.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeDynamicByteArrayReader<ZT, ZXO, ZXD>, WkSerdeDynamicByteArrayWriter<ZT, ZYO, ZYD>, ZD>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeDynamicByteArrayReader<ZT, ZXO, ZXD>, WkSerdeDynamicByteArrayWriter<ZT, ZYO, ZYD>, WkSerdeDtreeVariableSizeByteArray>
  variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
