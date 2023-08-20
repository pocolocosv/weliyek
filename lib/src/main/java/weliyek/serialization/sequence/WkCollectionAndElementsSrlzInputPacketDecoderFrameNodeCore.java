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

import weliyek.serialization.WkAggregatorSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationCtrl;

public abstract class WkCollectionAndElementsSrlzInputPacketDecoderFrameNodeCore<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQ extends WkSequenceDecodingRuntimeSrlzPacketOperationData<XB>,
                        XQC extends WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<XB,XBC,XQ>,
                        XR extends WkDecodingResultSrlzPacketOperationData<T>,
                        XD extends WkCollectionAndElementsSrlzStructDefinitionFrameNode<T,XO,?,ET,?>,
                        XO extends WkCollectionAndElementsSrlzInputPacketDecoderFrameNode<T,XS,XQ,XR,XD,ET,EXD,EXO>,
                        XOC extends WkCollectionAndElementsSrlzInputPacketDecoderFrameNodeCore<
                                        T,XS,XB,XBC,XQ,XQC,XR,XD,XO,?,AXBC,ET,EXS,EXD,EXO,DC>,
                        AXBC extends WkSzInputBytestreamBase<?>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSrlzStructDefinitionFrameNode<ET>,
                        EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                        DC extends WkCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XO,AXBC,
                                        ?,?,?,?,?,?,?,?,
                                        ET,EXS,EXD,EXO,
                                        ?,?,?,?,?,DC>>
        extends WkAggregatorSrlzInputPacketDecoderFrameNodeCore<T, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXBC, DC>
        implements WkCollectionAndElementsSrlzInputPacketDecoderFrameNode<T, XS, XQ, XR, XD, ET, EXD, EXO>
{

  private WkSrlzInputPacketSubfieldFrameNodeCore<ET,EXS,EXD,EXO,T,XBC,XD,XO> elementPacketSubfield;

  protected WkCollectionAndElementsSrlzInputPacketDecoderFrameNodeCore(
    int index,
    XS settings,
    AXBC parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<T,?,XD,?,?,?> packetfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
    this.elementPacketSubfield = getSubfieldpacketFor(definitionCore().elementComponent);
  }

  @Override
  protected final void onAggregatorReadingInitialization() {
    onCollectionReadingInitialization();
  }

  protected abstract void onCollectionReadingInitialization();

  @Override
  protected final T onFullReadingCompletion() {
    return definitionCore().collectionSerializingFactory.apply(this.body());
  }

  @Override
  protected final void onPartialReadingCompletion() {
    // Nothing to do.
  }

  @Override
  public final WkSrlzInputPacketSubfieldFrameNode<ET, EXD, EXO> elements() {
    return this.elementPacketSubfield.asSubfield();
  }

}
