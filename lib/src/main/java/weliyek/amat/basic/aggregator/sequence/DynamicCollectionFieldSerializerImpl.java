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

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingField;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.SerializingSubfieldHandler;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberSerializing;

public class DynamicCollectionFieldSerializerImpl<
                        T extends Collection<ET>,
                        YS extends OperationSettings,
                        YD extends DynamicCollectionFieldDefinition<
                                      T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZYS extends OperationSettings,
                        ZYO extends NumberSerializing<ZT,ZYS,?,?,ZYD>,
                        ZYD extends NumberDefinition<ZT,?>,
                        ET,
                        EYS extends OperationSettings,
                        EYD extends DefinitionSegment<ET,?>,
                        EYO extends SerializingOperation<ET,EYS,?,?,EYD>,
                        VYS extends OperationSettings>
    implements DynamicCollectionFieldSerializer<
                        T, YS,
                        SerializingRuntime<OutputBytestream>,
                        SerializingResult,
                        DynamicCollectionField<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                        ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
{

  final SimplifiedDynamicCollectionFieldSerializer<
                        T, YS,
                        DynamicCollectionFieldSerializer<
                          T, YS,
                          SerializingRuntime<OutputBytestream>,
                          SerializingResult,
                          DynamicCollectionField<
                            T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                          ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                        DynamicCollectionField<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                        ZT, ZYS, ZYO, ZYD, ET, EYS, EYD, EYO, VYS> operationCore;

  DynamicCollectionFieldSerializerImpl(
    int index,
    T serializable, YS settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    SerializingFieldCore<
      T,?,DynamicCollectionField<T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
      ?,?,?> packetfieldCore,
    SimplifiedDynamicCollectionDefinitionCore<
      T,?,?,?,YS,
      DynamicCollectionFieldSerializer<T,YS,SerializingRuntime<OutputBytestream>,SerializingResult,
        DynamicCollectionField<T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
        ZT,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
      DynamicCollectionField<T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
      ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS,?> definitionCore) {
    this.operationCore =  new SimplifiedDynamicCollectionFieldSerializer<
                                T, YS,
                                DynamicCollectionFieldSerializer<
                                  T, YS,
                                  SerializingRuntime<OutputBytestream>,
                                  SerializingResult,
                                  DynamicCollectionField<
                                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                                  ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                                DynamicCollectionField<
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
  public SerializingSubfieldHandler<ZT, ZYD, ZYO> size() {
    return this.operationCore.size();
  }

  @Override
  public
  SerializingSubfieldHandler<T, VariableSizeCollectionField<T, ?, VYS, ET, ?, ?, ?, EYS, EYD, EYO, ?>, VariableSizeCollectionFieldSerializer<T, VYS, ET, EYS, EYD, EYO>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public
  DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>
  definition() {
    return this.operationCore.definition();
  }

  @Override
  public YS settings() {
    return this.operationCore.settings();
  }

  @Override
  public SerializingRuntime<OutputBytestream> dashboard() {
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
  public
  SerializingField<T, DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ?>
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
  public List<SerializingSubfieldHandler<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
