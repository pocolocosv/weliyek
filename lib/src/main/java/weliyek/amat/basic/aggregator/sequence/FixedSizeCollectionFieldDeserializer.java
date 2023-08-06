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

import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.SequenceReadingRuntime;
import weliyek.amat.basic.sequence.CollectionAndElementsFieldDeserializer;
import weliyek.amat.basic.sequence.FixedSizeSequenceReading;

public final class FixedSizeCollectionFieldDeserializer<
                        T extends Collection<ET>,
                        XS extends OperationSettings,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends WkSzDefinition<ET,EXO>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>>
    implements CollectionAndElementsFieldDeserializer<
                        T,
                        XS,
                        SequenceReadingRuntime<InputBytestream>,
                        DeserializingResult<T>,
                        FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        ET, EXD, EXO>,
                FixedSizeSequenceReading<
                        T,
                        XS,
                        SequenceReadingRuntime<InputBytestream>,
                        DeserializingResult<T>,
                        FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>>
{

  final SimplifiedCollectionDeserializingCore<
                        T, XS,
                        FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                        ET, EXS, EXD, EXO> operationCore;

  FixedSizeCollectionFieldDeserializer(
    int index,
    XS settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      T,?,FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
      ?,?,?> deserializingfieldCore,
    SimplifiedCollectionDefinitionCore<
      T,XS,FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
      FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
      ?,?,?,ET,EXS,EXD,EXO,?,?,?,?,?> definitionCore) {
    this.operationCore = new SimplifiedCollectionDeserializingCore<
                                T, XS,
                                FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                                FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                                ET, EXS, EXD, EXO>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public WkSzPacketReaderSubfield<ET,EXD,EXO> element() {
    return this.operationCore.element();
  }

  @Override
  public FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?> definition() {
    return this.operationCore.definition();
  }

  @Override
  public XS settings() {
    return this.operationCore.settings();
  }

  @Override
  public SequenceReadingRuntime<InputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<DeserializingResult<T>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketReaderField<T, FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSzPacketReaderSubfield<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public String toString() {
    return this.name();
  }

}