/*
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
package weliyek.bitcoin;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriter;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLong;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongWriter;

public class WkBtcNetNodeServicesSerdeFieldWriter
    implements WkSerdeDtreeAggregatorMsgWriter<
                        WkBtcNetNodeServices,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkBtcNetNodeServices>,
                        WkBtcNetNodeServicesSerdeField>
{

  final WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
                        WkBtcNetNodeServices,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetNodeServicesSerdeField,
                        WkBtcNetNodeServicesSerdeFieldWriter> outputCore;

  WkBtcNetNodeServicesSerdeFieldWriter(
    int index,
    WkBtcNetNodeServices serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeMsgOutputFieldCore<?,?,?,?,?,?,?,?> writerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcNetNodeServices, ?, ?, ?, WkSerdeDtreeOperationSettings, WkBtcNetNodeServicesSerdeField, WkBtcNetNodeServicesSerdeFieldWriter, ? extends WkBtcNetNodeServicesSerdeField> definitionCore) {
    this.outputCore = new WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
                            WkBtcNetNodeServices,
                            WkSerdeDtreeOperationSettings,
                            WkBtcNetNodeServicesSerdeField,
                            WkBtcNetNodeServicesSerdeFieldWriter>(
                                index, serializable, settings, parentBytestream, writerFieldCore, definitionCore, this);
  }

  public Optional<WkSerdeDtreeMsgOutputField<Long, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongWriter>>
  int64le() {
    return Optional.ofNullable(this.outputCore.getSubfieldpacketFor(this.definition().int64le).asPacket());
  }

  @Override
  public WkBtcNetNodeServicesSerdeField definition() {
    return this.outputCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.outputCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.outputCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkBtcNetNodeServices>> result() {
    return this.outputCore.result();
  }

  @Override
  public int index() {
    return this.outputCore.index();
  }

  @Override
  public WkSerdeDtreeMsgOutputField<?,?,?> parentField() {
    return this.outputCore.parentField();
  }

  @Override
  public String name() {
    return this.outputCore.name();
  }

  @Override
  public WkBtcNetNodeServices serializable() {
    return this.outputCore.serializable();
  }

  @Override
  public List<WkSerdeDtreeMsgOutputField<?, ?, ?>> subfields() {
    return this.outputCore.subfields();
  }

}