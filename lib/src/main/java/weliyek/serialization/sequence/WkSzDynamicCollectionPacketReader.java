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
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;

public class WkSzDynamicCollectionPacketReader<
                        T extends Collection<ET>,
                        XS extends WkSzOperationSettings,
                        ZT extends Number,
                        ZXS extends WkSzOperationSettings,
                        ZXO extends WkSzNumberReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,?>,
                        ET,
                        EXS extends WkSzOperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        VXS extends WkSzVariableLengthOperationSettings>
    implements WkSzDynamicCollectionPacketReaderOperation<
                        T, XS,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<T>,
                        WkSzDynamicCollection<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                        ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>
{

  final WkSzSimplifiedDynamicSequencePacketReaderCore<
                        T, XS,
                        WkSzDynamicCollectionPacketReaderOperation<
                          T, XS,
                          WkSzReadingRuntime<WkSzInputBytestream>,
                          WkSzReadingResult<T>,
                          WkSzDynamicCollection<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                          ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                        WkSzDynamicCollection<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                        ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS> operationCore;

  WkSzDynamicCollectionPacketReader(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      T,?,WkSzDynamicCollection<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
      ?,?,?> packetfieldCore,
    WkSzSimplifiedDynamicCollectionStructDefinition<
      T,XS,
      WkSzDynamicCollectionPacketReaderOperation<
        T,XS,WkSzReadingRuntime<WkSzInputBytestream>,WkSzReadingResult<T>,
        WkSzDynamicCollection<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
        ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
      WkSzDynamicCollection<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
      ?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?,?> definitionCore) {
    this.operationCore = new WkSzSimplifiedDynamicSequencePacketReaderCore<
                                T, XS,
                                WkSzDynamicCollectionPacketReaderOperation<
                                T, XS,
                                WkSzReadingRuntime<WkSzInputBytestream>,
                                WkSzReadingResult<T>,
                                WkSzDynamicCollection<
                                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                                WkSzDynamicCollection<
                                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    packetfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public WkSzPacketReaderSubfield<ZT, ZXD, ZXO> size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSzPacketReaderSubfield<T, VariableSizeCollectionField<T, VXS, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?>, VariableSizeCollectionFieldDeserializer<T, VXS, ET, EXS, EXD, EXO>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public
  WkSzDynamicCollection<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>
  definition() {
    return this.operationCore.definition();
  }

  @Override
  public XS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzReadingRuntime<WkSzInputBytestream> dashboard() {
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
  WkSzPacketReaderField<T, WkSzDynamicCollection<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public List<WkSzPacketReaderSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
