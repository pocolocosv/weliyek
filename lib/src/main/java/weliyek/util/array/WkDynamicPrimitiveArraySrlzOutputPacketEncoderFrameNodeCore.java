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

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;

public class WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore<
                        T extends WkPrimitiveArray<?,?>,
                        YO extends WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                                        T,
                                        WkSzOperationSettings,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,?,YO,? extends ZYD,? extends VYD>,
                        ZT extends Number,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<
                                        ZT,
                                        WkSzOperationSettings,
                                        ?,?,ZYD>,
                        ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,?>,
                        VYO extends WkVariableSizePrimitiveArraySrlzOutputPacketEncoderFrameNode<
                                        T,
                                        WkSzOperationSettings,
                                        ?,?,VYD>,
                        VYD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T,?>>
    extends WkDynamicSequenceSrlzOutputPacketEncoderFrameNodeCore<
                        T,
                        WkSzOperationSettings,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YO,
                        WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT,
                        WkSzOperationSettings,
                        ZYO,
                        ZYD,
                        WkSzOperationSettings,
                        VYO,
                        VYD,
                        WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
                          T,?,?,YD,YO,
                          ZT,?,?,ZYD,ZYO,?,
                          ?,?,VYD,VYO,?,?>>
    implements WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                        T,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        YD, ZT, ZYO, ZYD, VYO, VYD>
{

  public WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore(
    int index,
    T serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<T,?,YD,?,?,?> packetHandlerCore,
    WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
      T,?,?,YD,YO,
      ZT,?,?,ZYD,ZYO,?,
      ?,?,VYD,VYO,?,?> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  protected WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>
  getThis() {
    return this;
  }

}
