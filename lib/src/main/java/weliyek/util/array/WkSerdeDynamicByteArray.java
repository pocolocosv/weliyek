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

import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCoreRoot;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinitionCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeDtreeNumberReader;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;

public class WkSerdeDynamicByteArray<
                        ZT extends Number,
                        ZXD extends WkSerdeDtreeNumberDefinition<ZT>,
                        ZXO extends WkSerdeDtreeNumberReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                        ZYD extends WkSerdeDtreeNumberDefinition<ZT>,
                        ZYO extends WkSerdeDtreeNumberWriter<ZT,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                        ZD extends WkSerdeDtreeNumberDefinition<ZT>>
    implements WkSerdeDtreeByteArrayDefinition,
               WkSerdeDtreeDynamicPrimitiveArrayDefinition<
                        WkByteArray,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                        WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                        ZD,
                        WkSerdeDtreeVariableSizeByteArray>
{
  public static <ZX extends Number,
                 ZXD extends WkSerdeDtreeNumberDefinition<ZX>,
                 ZXO extends WkSerdeDtreeNumberReader<ZX,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                 ZYD extends WkSerdeDtreeNumberDefinition<ZX>,
                 ZYO extends WkSerdeDtreeNumberWriter<ZX,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                 ZD extends WkSerdeDtreeNumberDefinition<ZX>>
  WkSerdeDtreeStruct<WkByteArray,
                  WkSerdeDtreeOperationSettings,
                  WkSerdeDynamicByteArray<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                  WkSerdeDynamicByteArrayReader<ZX,ZXO,ZXD>,
                  WkSerdeDtreeBytestreamInputBase<?>,
                  WkSerdeDtreeOperationSettings,
                  WkSerdeDynamicByteArray<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                  WkSerdeDynamicByteArrayWriter<ZX,ZYO,ZYD>,
                  WkSerdeDtreeBytestreamOutputBase<?>,
                  WkSerdeDynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>
  newStruct(
    String label,
    String sizeLabel,
    String bytearrayLabel,
    int minLength,
    int maxLength,
    IntFunction<ZX> sizeWrapper,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZX,WkSerdeDtreeOperationSettings,ZXD,ZXO,WkSerdeDtreeBytestreamInputBase<?>,
      WkSerdeDtreeOperationSettings,ZYD,ZYO,WkSerdeDtreeBytestreamOutputBase<?>,ZD>  sizeDefinition)
  {
    return new WkSerdeDtreeNodeStructComponentCoreRoot<WkByteArray,
        WkSerdeDtreeOperationSettings,
        WkSerdeDynamicByteArray<ZX,ZXD,ZXO,?,?,? extends ZXD>,
        WkSerdeDynamicByteArrayReader<ZX,ZXO,ZXD>,
        WkSerdeDtreeBytestreamInputBase<?>,
        WkSerdeDtreeOperationSettings,
        WkSerdeDynamicByteArray<ZX,?,?,ZYD,ZYO,? extends ZYD>,
        WkSerdeDynamicByteArrayWriter<ZX,ZYO,ZYD>,
        WkSerdeDtreeBytestreamOutputBase<?>,
        WkSerdeDynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>(
                  label,
                  (pc) -> WkSerdeDynamicByteArray.newCore(
                              pc, sizeLabel, sizeWrapper, bytearrayLabel, minLength, maxLength, sizeDefinition),
                  WkSerdeDtreeBytestreamCountingInputStream::new,
                  WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static <ZX extends Number,
                 ZXD extends WkSerdeDtreeNumberDefinition<ZX>,
                 ZXO extends WkSerdeDtreeNumberReader<ZX,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                 ZYD extends WkSerdeDtreeNumberDefinition<ZX>,
                 ZYO extends WkSerdeDtreeNumberWriter<ZX,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                 ZD extends WkSerdeDtreeNumberDefinition<ZX>>
  WkSerdeDtreeNodeStructDefinitionCore<WkByteArray,
                        WkSerdeDtreeOperationSettings,?,?,
                        WkSerdeDynamicByteArray<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeDynamicByteArrayReader<ZX,ZXO,ZXD>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,?,?,
                        WkSerdeDynamicByteArray<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSerdeDynamicByteArrayWriter<ZX,ZYO,ZYD>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeDynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>,?>
  newCore(
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZX> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZX,WkSerdeDtreeOperationSettings,ZXD,ZXO,WkSerdeDtreeBytestreamInputBase<?>,
      WkSerdeDtreeOperationSettings,ZYD,ZYO,WkSerdeDtreeBytestreamOutputBase<?>,ZD> sizeDefinitionFactory)
  {
    return new WkSerdeDynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>(componentCore, sizeLabel, wrapperSizeGetter, arrayLabel, minLength, maxLength, sizeDefinitionFactory).definitionCore;
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

  WkSerdeDynamicByteArray(WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZT> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT,WkSerdeDtreeOperationSettings,ZXD,ZXO,WkSerdeDtreeBytestreamInputBase<?>,WkSerdeDtreeOperationSettings,
      ZYD,ZYO,WkSerdeDtreeBytestreamOutputBase<?>,ZD> sizeComponentDefinitionFactory) {
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
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public
      WkSerdeDtreeNodeStructComponentHandler<WkSerdeDynamicByteArrayReader<ZT, ZXO, ZXD>, WkSerdeDynamicByteArrayWriter<ZT, ZYO, ZYD>, ZD>
      size() {
    return this.definitionCore.size();
  }

  @Override
  public
      WkSerdeDtreeNodeStructComponentHandler<WkSerdeDynamicByteArrayReader<ZT, ZXO, ZXD>, WkSerdeDynamicByteArrayWriter<ZT, ZYO, ZYD>, WkSerdeDtreeVariableSizeByteArray>
      variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
