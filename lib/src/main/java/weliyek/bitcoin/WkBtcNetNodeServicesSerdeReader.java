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

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReader;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLong;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongReader;

public class WkBtcNetNodeServicesSerdeReader
    implements WkSerdeDtreeAggregatorMsgReader<
                        WkBtcNetNodeServices,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkBtcNetNodeServices>,
                        WkBtcNetNodeServicesSerdeDef>
{

  final WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
                        WkBtcNetNodeServices,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetNodeServicesSerdeDef,
                        WkBtcNetNodeServicesSerdeReader> inputCore;

  WkBtcNetNodeServicesSerdeReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcNetNodeServices, WkSerdeDtreeOperationSettings, WkBtcNetNodeServicesSerdeDef, WkBtcNetNodeServicesSerdeReader, ?, ?, ?, ? extends WkBtcNetNodeServicesSerdeDef> definitionCore) {
    this.inputCore = new WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
          WkBtcNetNodeServices,
          WkSerdeDtreeOperationSettings,
          WkBtcNetNodeServicesSerdeDef,
          WkBtcNetNodeServicesSerdeReader>(
        index, settings, parentBytestream, readerFieldCore, definitionCore, this);
  }

  public Optional<WkSerdeDtreeMsgInputField<Long, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongReader>>
  int64le() {
    return Optional.ofNullable(this.inputCore.getSubfieldpacketFor(this.definition().int64le).asPacket());
  }

  @Override
  public WkBtcNetNodeServicesSerdeDef definition() {
    return this.inputCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.inputCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
    return this.inputCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkBtcNetNodeServices>> result() {
    return this.inputCore.result();
  }

  @Override
  public int index() {
    return this.inputCore.index();
  }

  @Override
  public
      WkSerdeDtreeMsgInputField<?,?,?> parentField() {
    return this.inputCore.parentField();
  }

  @Override
  public String name() {
    return this.inputCore.name();
  }

  @Override
  public List<WkSerdeDtreeMsgInputField<?, ?, ?>> subfields() {
    return this.inputCore.subfields();
  }

}
