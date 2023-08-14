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
import java.util.Optional;

import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzSequenceWritingRuntime;
import weliyek.serialization.WkSzWritingResult;

public final class FixedSizeCollectionFieldSerializer<
                        T extends Collection<ET>,
                        YS extends WkSzOperationSettings,
                        ET,
                        EYS extends WkSzOperationSettings,
                        EYD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>>
    implements CollectionAndElementsFieldSerializer<
                        T,
                        YS,
                        WkSzSequenceWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        ET,
                        EYD,
                        EYO>,
                WkFixedSizeSequenceSrlzOutputPacketEncoderFrameNode<
                        T,
                        YS,
                        WkSzSequenceWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>>
{

  final SimplifiedCollectionSerializingCore<
                        T, YS,
                        FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                        ET,EYS,EYD,EYO> operationCore;

  FixedSizeCollectionFieldSerializer(
    int index,
    T serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      T,?,FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
      ?,?,?> serializingfieldCore,
    SimplifiedCollectionDefinitionCore<
      T,?,?,?,YS,FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
      FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
      ET,?,?,?,EYS,EYD,EYO,?,?> definitionCore) {
    this.operationCore = new SimplifiedCollectionSerializingCore<
                                  T, YS,
                                  FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                                  FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                                  ET,EYS,EYD,EYO>(
                                      index,
                                      serializable,
                                      settings,
                                      parentBytestream,
                                      serializingfieldCore,
                                      definitionCore,
                                      this);
  }

  @Override
  public WkSrlzOutputPacketSubfieldFrameNode<ET, EYD, EYO> element() {
    return this.operationCore.element();
  }

  @Override
  public FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?> definition() {
    return this.operationCore.definition();
  }

  @Override
  public YS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzSequenceWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzWritingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<T, FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public T serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<ET> serializableAsList() {
    return this.operationCore.serializableAsList();
  }

}
