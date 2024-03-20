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
package weliyek.serialization.sequence;

import java.util.Collection;

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDTreeNodeDataReader;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;
import weliyek.util.array.WkDynamicSequenceSrlzInputPacketDecoderFrameNodeCore;

public final class WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XO extends WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                                        T,XS,
                                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        XD,ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD extends WkDynamicCollectionSrlzStructDefinitionFrameNode<
                                        T,XO,?,?,ET,EXS,?,EXO,?,?,?,?,VXS,?>,
                        ZT extends Number,
                        ZXS extends WkSettingsSrlzPacketOperationData,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                        VXS extends WkSzVariableLengthOperationSettings>
    extends WkDynamicSequenceSrlzInputPacketDecoderFrameNodeCore<
                        T, XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
                        XO,
                        WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                          T,XS,XO,XD,ZT,ZXS,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD,
                        WkSzInputBytestreamBase<?>,
                        ZT, ZXS, ZXO, ZXD, VXS,
                        WkVariableSizeCollectionSrlzInputNode<T,VXS,ET,EXS,EXD,EXO>, // VXO
                        WkVariableSizeCollectionSrlzStructNode<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>, // VXD
                        WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<T,XS,XO,XD,?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?,?>>
    implements WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                        T, XS,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkResultSrlzPacketOperationData<T>,
                        XD, ZT, ZXO, ZXD, ET, EXS, EXD, EXO,
                        VXS>
{

  public WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<T,?,XD,?,?,?> packetfieldCore,
    WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
      T,XS,XO,XD,?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?,?> definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
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
  protected
  WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<T, XS, XO, XD, ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>
  getThis() {
    return this;
  }

}
