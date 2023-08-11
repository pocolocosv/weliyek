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

import weliyek.serialization.WkSzDefinition;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPacketReaderOperation;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzSequenceReadingRuntime;
import weliyek.serialization.WkSzVariableLengthOperationSettings;

public final class VariableSizeCollectionFieldDeserializer<
                        T extends Collection<ET>,
                        XS extends WkSzVariableLengthOperationSettings,
                        ET,
                        EXS extends WkSzOperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>>
    implements CollectionAndElementsFieldDeserializer<
                        T,
                        XS,
                        WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<T>,
                        VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        ET,
                        EXD,
                        EXO>,
               VariableSizeSequenceReading<
                        T,
                        XS,
                        WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<T>,
                        VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>>
{

  final SimplifiedCollectionDeserializingCore<
                        T,
                        XS,
                        VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                        ET,
                        EXS,
                        EXD,
                        EXO> operationCore;

  VariableSizeCollectionFieldDeserializer(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      T,?,VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,?,?,?> deserializingfieldCore,
    SimplifiedCollectionDefinitionCore<
      T,XS,VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
      VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
      ?,?,?,ET,EXS,EXD,EXO,?,?,?,?,?> definitionCore) {
    this.operationCore = new SimplifiedCollectionDeserializingCore<
                                T,
                                XS,
                                VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                                VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                                ET,EXS,EXD,EXO>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public WkSzPacketReaderSubfield<ET, EXD, EXO> element() {
    return this.operationCore.element();
  }

  @Override
  public VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?> definition() {
    return this.operationCore.definition();
  }

  @Override
  public XS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzSequenceReadingRuntime<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzReadingResult<T>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public
  WkSzPacketReaderField<T,VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSzPacketReaderSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

}