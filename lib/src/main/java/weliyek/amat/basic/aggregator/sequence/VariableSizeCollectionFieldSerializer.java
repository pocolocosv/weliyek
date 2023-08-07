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
package weliyek.amat.basic.aggregator.sequence;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceWriting;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;
import weliyek.serialization.WkSzDefinition;

public final class VariableSizeCollectionFieldSerializer<
                        T extends Collection<ET>,
                        YS extends OperationSettings,
                        ET,
                        EYS extends OperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>>
    implements CollectionAndElementsFieldSerializer<
                        T,
                        YS,
                        SequenceWritingRuntime<WkSzOutputBytestream>,
                        SerializingResult,
                        VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        ET,
                        EYD,
                        EYO>,
               VariableSizeSequenceWriting<
                        T,
                        YS,
                        SequenceWritingRuntime<WkSzOutputBytestream>,
                        SerializingResult,
                        VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>>
{

  final SimplifiedCollectionSerializingCore<
                        T, YS,
                        VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                        ET,EYS,EYD,EYO> operationCore;

  VariableSizeCollectionFieldSerializer(
    int index,
    T serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      T,?,VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,?,?,?> serializingfieldCore,
    SimplifiedCollectionDefinitionCore<
      T,?,?,?,YS,VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
      VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
      ET,?,?,?,EYS,EYD,EYO,?,?> definitionCore) {
    this.operationCore = new SimplifiedCollectionSerializingCore<
                                T,
                                YS,
                                VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                                VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
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
  public WkSzPacketWriterSubfield<ET, EYD, EYO> element() {
    return this.operationCore.element();
  }

  @Override
  public VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?> definition() {
    return this.operationCore.definition();
  }

  @Override
  public YS settings() {
    return this.operationCore.settings();
  }

  @Override
  public SequenceWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<SerializingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketWriterField<T, VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>, ?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSzPacketWriterSubfield<?,?,?>> subfields() {
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
