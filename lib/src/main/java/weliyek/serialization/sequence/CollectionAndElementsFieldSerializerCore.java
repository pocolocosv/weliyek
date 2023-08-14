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
import java.util.List;

import weliyek.serialization.WkAggregatorSrlzOutputPacketEncoderFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;

public abstract class CollectionAndElementsFieldSerializerCore<
                        T extends Collection<ET>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends WkSequenceEncodingRuntimeSrlzPacketOperationData<YB>,
                        YQC extends WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,YQ>,
                        YR extends WkEncodingResultSrlzPacketOperationData,
                        YD extends WkSzCollectionAndElementsDefinition<T,?,YO,ET,?>,
                        YO extends CollectionAndElementsFieldSerializer<T,YS,YQ,YR,YD,ET,EYD,EYO>,
                        YOC extends CollectionAndElementsFieldSerializerCore<T,YS,YB,YBC,YQ,YQC,YR,YD,YO,?,AYBC,ET,EYS,EYD,EYO,DC>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        ET,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                        DC extends CollectionAndElementsFieldDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YD,YO,AYBC,
                                        ET,?,?,?,EYS,EYD,EYO,?,?,DC>>
        extends WkAggregatorSrlzOutputPacketEncoderFrameNodeCore<T, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYBC, DC>
        implements CollectionAndElementsFieldSerializer<T, YS, YQ, YR, YD, ET, EYD, EYO>
{

  private final List<ET> serializableAsList;

  private WkSrlzOutputPacketSubfieldFrameNodeCore<ET,EYS,EYD,EYO,T,YBC,YD,YO> elementPacketSubfield;

  protected CollectionAndElementsFieldSerializerCore(
    int index,
    T serializable,
    YS settings,
    AYBC parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
    this.serializableAsList = CollectionAndElementsFieldSerializer.collectionToList(serializable);
    this.elementPacketSubfield = getSubfieldpacketFor(definitionCore().elementComponent);
  }

  @Override
  public final List<ET> serializableAsList() {
    return this.serializableAsList;
  }

  @Override
  protected final void onAggregatorInitialization() {
    onCollectionWritingInitialization();
  }

  protected abstract void onCollectionWritingInitialization();

  @Override
  public final WkSrlzOutputPacketSubfieldFrameNode<ET,EYD,EYO> element() {
    return this.elementPacketSubfield.asSubfield();
  }

}
