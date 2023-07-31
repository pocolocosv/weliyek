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
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;

public class DynamicCollectionFieldDeserializerImpl<
                        T extends Collection<ET>,
                        XS extends OperationSettings,
                        ZT extends Number,
                        ZXS extends OperationSettings,
                        ZXO extends WkSzNumberReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,?>,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        VXS extends VariableLengthSettings>
    implements DynamicCollectionFieldDeserializer<
                        T, XS,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<T>,
                        DynamicCollectionField<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                        ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>
{

  final SimplifiedDynamicCollectionFieldDeserializer<
                        T, XS,
                        DynamicCollectionFieldDeserializer<
                          T, XS,
                          DeserializingRuntime<InputBytestream>,
                          DeserializingResult<T>,
                          DynamicCollectionField<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                          ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                        DynamicCollectionField<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                        ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS> operationCore;

  DynamicCollectionFieldDeserializerImpl(
    int index,
    XS settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      T,?,DynamicCollectionField<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
      ?,?,?> packetfieldCore,
    SimplifiedDynamicCollectionDefinitionCore<
      T,XS,
      DynamicCollectionFieldDeserializer<
        T,XS,DeserializingRuntime<InputBytestream>,DeserializingResult<T>,
        DynamicCollectionField<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
        ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
      DynamicCollectionField<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
      ?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?,?> definitionCore) {
    this.operationCore = new SimplifiedDynamicCollectionFieldDeserializer<
                                T, XS,
                                DynamicCollectionFieldDeserializer<
                                T, XS,
                                DeserializingRuntime<InputBytestream>,
                                DeserializingResult<T>,
                                DynamicCollectionField<
                                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                                DynamicCollectionField<
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
  DynamicCollectionField<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>
  definition() {
    return this.operationCore.definition();
  }

  @Override
  public XS settings() {
    return this.operationCore.settings();
  }

  @Override
  public DeserializingRuntime<InputBytestream> dashboard() {
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
  public
  WkSzPacketReaderField<T, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ?>
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
