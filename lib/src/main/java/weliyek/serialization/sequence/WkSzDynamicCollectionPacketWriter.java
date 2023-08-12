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
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterOperation;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberWriter;

public class WkSzDynamicCollectionPacketWriter<
                        T extends Collection<ET>,
                        YS extends WkSzOperationSettings,
                        YD extends WkSzDynamicCollectionDefinition<
                                      T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZYS extends WkSzOperationSettings,
                        ZYO extends WkSzNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ET,
                        EYS extends WkSzOperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        VYS extends WkSzOperationSettings>
    implements WkSzDynamicCollectionPacketWriterOperation<
                        T, YS,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        WkSzDynamicCollection<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                        ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
{

  final WkSzSimplifiedDynamicSequencePacketWriterCore<
                        T, YS,
                        WkSzDynamicCollectionPacketWriterOperation<
                          T, YS,
                          WkSzWritingRuntime<WkSzOutputBytestream>,
                          WkSzWritingResult,
                          WkSzDynamicCollection<
                            T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                          ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                        WkSzDynamicCollection<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                        ZT, ZYS, ZYO, ZYD, ET, EYS, EYD, EYO, VYS> operationCore;

  WkSzDynamicCollectionPacketWriter(
    int index,
    T serializable, YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      T,?,WkSzDynamicCollection<T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
      ?,?,?> packetfieldCore,
    WkSzSimplifiedDynamicCollectionStructDefinition<
      T,?,?,?,YS,
      WkSzDynamicCollectionPacketWriterOperation<T,YS,WkSzWritingRuntime<WkSzOutputBytestream>,WkSzWritingResult,
        WkSzDynamicCollection<T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
        ZT,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
      WkSzDynamicCollection<T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
      ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS,?> definitionCore) {
    this.operationCore =  new WkSzSimplifiedDynamicSequencePacketWriterCore<
                                T, YS,
                                WkSzDynamicCollectionPacketWriterOperation<
                                  T, YS,
                                  WkSzWritingRuntime<WkSzOutputBytestream>,
                                  WkSzWritingResult,
                                  WkSzDynamicCollection<
                                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                                  ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                                WkSzDynamicCollection<
                                T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                                ZT, ZYS, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    packetfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public WkSzPacketWriterSubfield<ZT, ZYD, ZYO> size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSzPacketWriterSubfield<T, VariableSizeCollectionField<T, ?, VYS, ET, ?, ?, ?, EYS, EYD, EYO, ?>, VariableSizeCollectionFieldSerializer<T, VYS, ET, EYS, EYD, EYO>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public
  WkSzDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>
  definition() {
    return this.operationCore.definition();
  }

  @Override
  public YS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
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
  public
  WkSzPacketWriterField<T, WkSzDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ?>
  packetField() {
    return this.operationCore.packetField();
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
  public List<WkSzPacketWriterSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
