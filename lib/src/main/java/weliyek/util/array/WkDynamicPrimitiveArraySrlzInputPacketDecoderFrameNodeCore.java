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

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;

public class WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<
                        T extends WkPrimitiveArray<?,?>,
                        XO extends WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNode<
                                        T,
                                        WkSzOperationSettings,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,XO,?,? extends ZXD,? extends VXD>,
                        ZT extends Number,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<
                                        ZT,
                                        WkSzOperationSettings,
                                        ?,?,ZXD>,
                        ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,ZXO>,
                        VXO extends WkVariableSizePrimitiveArraySrlzInputPacketDecoderFrameNode<
                                        T,
                                        WkSzVariableLengthOperationSettings,
                                        ?,?,VXD>,
                        VXD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T,VXO>>
    extends WkDynamicSequenceSrlzInputPacketDecoderFrameNodeCore<
                        T,
                        WkSzOperationSettings,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        XO,
                        WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD,
                        WkSzInputBytestreamBase<?>,
                        ZT,
                        WkSzOperationSettings,
                        ZXO,
                        ZXD,
                        WkSzVariableLengthOperationSettings,
                        VXO,
                        VXD,
                        WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
                          T,XD,XO,?,?,
                          ZT,ZXD,ZXO,?,?,?,
                          VXD,VXO,?,?,?,?>>
    implements WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNode<
                        T,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<T>,
                        XD, ZT, ZXO, ZXD, VXO, VXD>
{

  public WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNodeCore(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<T,?,XD,?,?,?> deserializingfieldCore,
    WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
      T,XD,XO,?,?,
      ZT,ZXD,ZXO,?,?,?,
      VXD,VXO,?,?,?,?> definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, deserializingfieldCore, definitionCore, operationBody);
  }

  @Override
  protected T onFullReadingCompletion() {
    return variableSequence().field().get()
                             .firstOperation().get()
                             .result().get()
                             .deserialized().get();
  }

  @Override
  protected void onPartialReadingCompletion() {
    // Nothing to do.
  }

  @Override
  protected WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>
  getThis() {
    return this;
  }

}
