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

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeDTreeNumberReader;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;

public class WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<
                        T extends WkPrimitiveArray<?,?>,
                        XO extends WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNode<
                                        T,
                                        WkSettingsSrlzPacketOperationData,
                                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,XO,?,? extends ZXD,? extends VXD>,
                        ZT extends Number,
                        ZXO extends WkSerdeDTreeNumberReader<
                                        ZT,
                                        WkSettingsSrlzPacketOperationData,
                                        ?,?,ZXD>,
                        ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                        VXO extends WkSerdeDTreeGenericVariableSizePrimitiveArrayReader<
                                        T,
                                        WkSzVariableLengthOperationSettings,
                                        ?,?,VXD>,
                        VXD extends WkSerdeDTreeGenericVariableSizePrimitiveArrayDefinition<T>>
    extends WkDynamicSequenceSrlzInputPacketDecoderFrameNodeCore<
                        T,
                        WkSettingsSrlzPacketOperationData,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
                        XO,
                        WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD,
                        WkSzInputBytestreamBase<?>,
                        ZT,
                        WkSettingsSrlzPacketOperationData,
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
                        WkSettingsSrlzPacketOperationData,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkResultSrlzPacketOperationData<T>,
                        XD, ZT, ZXO, ZXD, VXO, VXD>
{

  public WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNodeCore(
    int index,
    WkSettingsSrlzPacketOperationData settings,
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
                             .serializable().get();
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
